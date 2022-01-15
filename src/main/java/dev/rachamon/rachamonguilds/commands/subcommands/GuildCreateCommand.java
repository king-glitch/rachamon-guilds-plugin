package dev.rachamon.rachamonguilds.commands.subcommands;


import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import dev.rachamon.rachamonguilds.configs.MainConfig;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

@ICommandAliases({"create"})
@ICommandPermission("rachamonguilds.command.guild.create")
@ICommandDescription("Creates a guild")
public class GuildCreateCommand implements IPlayerCommand, IParameterizedCommand {

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("name")),
                GenericArguments.string(Text.of("displayName")),
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {

        String name = args.<String>getOne("name").get();
        String displayName = args.<String>getOne("displayName").get();
        MainConfig config = RachamonGuilds.getInstance().getConfig().getRoot();
        LanguageConfig language = RachamonGuilds.getInstance().getConfig().getLanguage();

        int minNameLength = config.getGuildCategorySetting().getMinGuildNameLength();
        int maxNameLength = config.getGuildCategorySetting().getMaxGuildNameLength();

        boolean isGuildDisplayNameIncludeColor = config.getGuildCategorySetting().isGuildDisplayNameIncludeColor();
        int minDisplayNameLength = config.getGuildCategorySetting().getMinGuildDisplayNameLength();
        int maxDisplayNameLength = config.getGuildCategorySetting().getMaxGuildDisplayNameLength();
        int displayNameLength = 0;

        if (name.length() < minNameLength) {
            throw new GuildCommandException(language.getCommandCategorySetting().getCommandCreatedNameTooLong());
        }

        if (name.length() > maxNameLength) {
            throw new GuildCommandException(language.getCommandCategorySetting().getCommandCreatedNameTooLong());
        }

        if (!isGuildDisplayNameIncludeColor) {
            displayNameLength = displayName.length() - (displayName.replace("&", "").length() * 2);
        } else {
            displayNameLength = displayName.length();
        }

        if (displayNameLength < minDisplayNameLength) {
            throw new GuildCommandException(language.getCommandCategorySetting().getCommandCreatedDisplayNameTooShort());
        }

        if (displayNameLength > maxDisplayNameLength) {
            throw new GuildCommandException(language.getCommandCategorySetting().getCommandCreatedDisplayNameTooLong());
        }

        // create guild
        RachamonGuilds
                .getInstance()
                .getGuildManager().createGuild(
                        source,
                        name,
                        displayName
                );

        return CommandResult.success();
    }
}
