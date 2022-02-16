package dev.rachamon.rachamonguilds.commands.subcommands;


import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import dev.rachamon.rachamonguilds.configs.MainConfig;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;


/**
 * The type Guild create command.
 */
@ICommandAliases({"create"})
@ICommandPermission("rachamonguilds.command.guild.create")
@ICommandDescription("Creates a guild")
public class GuildCreateCommand implements IPlayerCommand, IParameterizedCommand {

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("name")),
                GenericArguments.string(Text.of("displayName"))
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {

        Optional<String> name = args.<String>getOne("name");
        Optional<String> displayName = args.<String>getOne("displayName");

        if (!name.isPresent() || !displayName.isPresent()) return CommandResult.empty();
        RachamonGuilds.getInstance().getLogger().info(displayName.get());
        RachamonGuilds.getInstance().getLogger().info(String.valueOf(displayName.get().matches("[A-Za-z&0-9]*")));
        MainConfig config = RachamonGuilds.getInstance().getConfig();
        LanguageConfig language = RachamonGuilds.getInstance().getLanguage();

        int minNameLength = config.getGuildCategorySetting().getMinGuildNameLength();
        int maxNameLength = config.getGuildCategorySetting().getMaxGuildNameLength();

        if (name.get().length() < minNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooShort());
        }

        if (name.get().length() > maxNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooLong());
        }

        if (!name.get().matches(config.getGuildCategorySetting().getValidNameRegex())) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildName());
        }

        boolean isGuildDisplayNameIncludeColor = config.getGuildCategorySetting().isGuildDisplayNameIncludeColor();
        int minDisplayNameLength = config.getGuildCategorySetting().getMinGuildDisplayNameLength();
        int maxDisplayNameLength = config.getGuildCategorySetting().getMaxGuildDisplayNameLength();

        RachamonGuildsUtil.guildDisplayNameCheck(displayName.get(), language, isGuildDisplayNameIncludeColor, minDisplayNameLength, maxDisplayNameLength);

        if (!displayName.get().matches("[A-Za-z&0-9]*")) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildDisplayName());
        }
        // create guild
        RachamonGuilds.getInstance().getGuildManager().create(source, name.get(), displayName.get());

        return CommandResult.success();
    }


}
