package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;

import javax.annotation.Nonnull;

/**
 * The type Guild admin reload command.
 */
@ICommandAliases({"reload"})
@ICommandPermission("rachamonguilds.command.admin.reload")
@ICommandDescription("reload guild plugin.")
public class GuildAdminReloadCommand implements ICommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {
        try {
            RachamonGuilds.getInstance().getPluginManager().reload();
            RachamonGuilds
                    .getInstance()
                    .getGuildMessagingManager()
                    .response(source, TextUtil.toText(RachamonGuilds.getInstance()
                            .getLanguage()
                            .getCommandCategory()
                            .getCommandReloadSuccess())
                    );
        } catch (Exception e) {
            return CommandResult.empty();
        }
        return CommandResult.success();
    }
}
