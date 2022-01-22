package dev.rachamon.rachamonguilds.api.services;

import dev.rachamon.rachamonguilds.api.entities.Command;
import dev.rachamon.rachamonguilds.api.exceptions.AnnotatedCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextStyles;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

import static org.spongepowered.api.text.format.TextColors.*;

/**
 * The type Command service.
 */
public final class CommandService {

    private static final CommandService instance = new CommandService();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandService getInstance() {
        return instance;
    }

    /**
     * Register.
     *
     * @param <T>      the type parameter
     * @param executor the executor
     * @param plugin   the plugin
     * @throws AnnotatedCommandException the annotated command exception
     */
    public <T extends CommandExecutor> void register(T executor, Object plugin) throws AnnotatedCommandException {
        Command command = buildCommandSpec(executor);
        Sponge.getCommandManager().register(plugin, command.getSpec(), command.getAliases());
    }

    /**
     * Build command spec command.
     *
     * @param <T>     the type parameter
     * @param command the command
     * @return the command
     * @throws AnnotatedCommandException the annotated command exception
     */
    public <T extends CommandExecutor> Command buildCommandSpec(T command) throws AnnotatedCommandException {

        Class<? extends CommandExecutor> commandClass = command.getClass();

        String[] aliases = getAliases(commandClass);

        CommandSpec.Builder spec = CommandSpec.builder();

        // set description
        getAnnotation(commandClass, ICommandDescription.class).ifPresent(description -> spec.description(Text.of(description.value())));

        // set permission
        getAnnotation(commandClass, ICommandPermission.class).ifPresent(permission -> spec.permission(permission.value()));

        List<Command> children = new ArrayList<>();
        // set children
        if (commandClass.isAnnotationPresent(ICommandChildren.class)) {
            // if a parent command is also parameterized, throw exception
            if (command instanceof IParameterizedCommand) {
                throw AnnotatedCommandException.parentIParameterizedCommand(commandClass);
            }

            Class<? extends CommandExecutor>[] childCommandClasses = commandClass
                    .getAnnotation(ICommandChildren.class).value();

            for (Class<? extends CommandExecutor> child : childCommandClasses) {
                Command childSpec;
                // instantiate the child command
                try {
                    childSpec = buildCommandSpec(child.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw AnnotatedCommandException.childInstantiation(child);
                }
                spec.child(childSpec.getSpec(), childSpec.getAliases());
                children.add(childSpec);
            }
        }

        // if the command has parameters, cast it to a ParameterizedClass, get the arguments, and apply them to the spec
        if (command instanceof IParameterizedCommand) {
            IParameterizedCommand parameterizedCommand = (IParameterizedCommand) command;
            spec.arguments(parameterizedCommand.getArguments());
        }

        spec.executor(command);
        CommandSpec commandSpec = spec.build();

        Command com = new Command(commandSpec, children, aliases);

        getAnnotation(commandClass, ICommandHelpText.class).ifPresent(help -> {
            CommandExecutor helpCommand = createHelpCommand(com, help);
            if (help.command().isEmpty()) {
                spec.executor(helpCommand);
            } else {
                String permission = getAnnotation(commandClass, ICommandPermission.class)
                        .map(ICommandPermission::value)
                        .orElse(null);
                assert permission != null;
                CommandSpec helpSpec = CommandSpec.builder()
                        .executor(helpCommand)
                        .permission(permission)
                        .build();
                spec.child(helpSpec, help.command());
            }
            com.setSpec(spec.build());
        });

        return com;
    }

    private static <A extends Annotation> Optional<A> getAnnotation(Class clazz, Class<A> annotation) {
        return Optional.ofNullable((A) clazz.getAnnotation(annotation));
    }

    private CommandExecutor createHelpCommand(Command command, ICommandHelpText annotation) {
        // If command has no children, just return the command's spec
        if (command.getChildren().size() == 0) return command.getSpec().getExecutor();

        PaginationList.Builder helpList = PaginationList.builder()
                .title(Text.of(DARK_RED, TextStyles.BOLD, annotation.title()))
                .padding(Text.of(DARK_GRAY, "="));

        Map<Command, Text> helpText = new LinkedHashMap<>();
        helpText.put(command, getHelpForBase(command, annotation));

        for (Command child : command.getChildren()) {
            helpText.put(child, getHelpForSub(child, annotation, command.getAliases()[0]));
        }

        return (src, args) -> {
            List<Text> filtered = helpText.entrySet().stream()
                    .filter(entry -> entry.getKey().getSpec().testPermission(src))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            helpList.contents(filtered).sendTo(src);
            return CommandResult.success();
        };
    }

    private Text getHelpForBase(Command command, ICommandHelpText annotation) {
        return getHelpFor(command, annotation, command.getAliases()[0], "");
    }

    private Text getHelpForSub(Command command, ICommandHelpText annotation, String base) {
        return getHelpFor(command, annotation, base + " ", command.getAliases()[0]);
    }

    private Text getHelpFor(Command command, ICommandHelpText annotation, String base, String alias) {
        Text.Builder help = Text.builder();

        // If there's a provided prefix, use that + a space
        String prefix = "";
        if (!annotation.prefix().isEmpty()) {
            prefix = annotation.prefix() + " ";
        }

        Text commandText = Text.of(DARK_GRAY, "/", DARK_RED, prefix, RED, base + alias);
        help.append(commandText)
                .onClick(TextActions.suggestCommand(commandText.toPlain()))
                .onHover(TextActions.showText(Text.of(commandText)));

        // Don't want to spam the message with a bunch of sub-commands
        if (command.getChildren().size() == 0) {
            help.append(Text.of(" ", command.getSpec().getUsage(console())));
        }

        command.getSpec().getShortDescription(console()).ifPresent(desc -> help.append(Text.of(GRAY, " : ", WHITE, desc)));
        return help.build();
    }

    private ConsoleSource console() {
        return Sponge.getServer().getConsole();
    }

    private String[] getAliases(Class<? extends CommandExecutor> commandClass) throws AnnotatedCommandException {
        // All command must have at least 1 non-empty alias
        if (!commandClass.isAnnotationPresent(ICommandAliases.class)) {
            throw AnnotatedCommandException.noAliases(commandClass);
        }

        String[] aliases = commandClass.getAnnotation(ICommandAliases.class).value();

        // check for empty aliases, they're not allowed
        for (String alias : aliases) {
            if (alias.isEmpty()) {
                throw AnnotatedCommandException.emptyAlias(commandClass);
            }
        }

        return aliases;
    }
}