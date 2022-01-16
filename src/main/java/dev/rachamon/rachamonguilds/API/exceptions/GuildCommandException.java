package dev.rachamon.rachamonguilds.api.exceptions;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.entity.living.player.User;

public class GuildCommandException extends CommandException {

    private static final RachamonGuilds plugin = RachamonGuilds.getInstance();

    public GuildCommandException(String message) {
        super(plugin.getGuildMessagingManager().formatError(RachamonGuildsUtil.toText(message)));
    }

    public static GuildCommandException notInGuild() {
        return new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getUserNotInGuild());
    }

    public static GuildCommandException notGuildMaster() {
        return new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
    }

    public static GuildCommandException targetNotInGuild(User player) {
        return new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getTargetIsNotInGuild().replaceAll("\\{target}", player.getName()));
    }

    public static GuildCommandException notOnlineOrExists() {
        return new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getUserNotFound());
    }

    public static GuildCommandException somethingWentWrong() {
        return new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getSomethingWentWrong());
    }


}
