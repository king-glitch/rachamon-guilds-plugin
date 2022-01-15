package dev.rachamon.rachamonguilds.api.exceptions;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.entity.living.player.User;

public class GuildCommandException extends CommandException {

    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    public GuildCommandException(Object... message) {
        super(RachamonGuilds.getInstance().getGuildMessagingManager().formatError(message));
    }

    public static GuildCommandException notInGuild() {
        return new GuildCommandException("You are not in a club.");
    }

    public static GuildCommandException notGuildMaster() {
        return new GuildCommandException("You are not the club leader.");
    }

    public static GuildCommandException targetNotInGuild(User player) {
        return new GuildCommandException(player.getName(), " is not in your club.");
    }


}
