package dev.rachamon.rachamonguilds.commands.subcommands;


import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
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
import java.util.regex.Pattern;

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

        if (name.length() < minNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooShort());
        }

        if (name.length() > maxNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooLong());
        }

        if (!name.matches(config.getGuildCategorySetting().getValidNameRegex())) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildName());
        }


        boolean isGuildDisplayNameIncludeColor = config.getGuildCategorySetting().isGuildDisplayNameIncludeColor();
        int minDisplayNameLength = config.getGuildCategorySetting().getMinGuildDisplayNameLength();
        int maxDisplayNameLength = config.getGuildCategorySetting().getMaxGuildDisplayNameLength();


        RachamonGuildsUtil.guildDisplayNameCheck(displayName, language, isGuildDisplayNameIncludeColor, minDisplayNameLength, maxDisplayNameLength);
        if (!displayName.matches("[A-Za-z&0-9]*")) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildDisplayName());
        }
        // create guild
        RachamonGuilds
                .getInstance()
                .getGuildManager().create(
                        source,
                        name,
                        displayName
                );

        return CommandResult.success();
    }


}
