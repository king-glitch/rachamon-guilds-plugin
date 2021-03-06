package dev.rachamon.rachamonguilds.api.exceptions;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.entity.living.player.User;

/**
 * The type Guild command exception.
 */
public class GuildCommandException extends CommandException {

    private static final RachamonGuilds plugin = RachamonGuilds.getInstance();

    /**
     * Instantiates a new Guild command exception.
     *
     * @param message the message
     */
    public GuildCommandException(String message) {
        super(plugin.getGuildMessagingManager().formatError(TextUtil.toText(message)));
    }

    /**
     * Not in guild command exception.
     *
     * @return the guild command exception
     */
    public static GuildCommandException notInGuild() {
        return new GuildCommandException(plugin.getLanguage().getGeneralCategory().getUserNotInGuild());
    }

    /**
     * Not guild master guild command exception.
     *
     * @return the guild command exception
     */
    public static GuildCommandException notGuildMaster() {
        return new GuildCommandException(plugin.getLanguage().getGeneralCategory().getNotGuildMaster());
    }

    /**
     * Target not in guild command exception.
     *
     * @param player the player
     * @return the guild command exception
     */
    public static GuildCommandException targetNotInGuild(User player) {
        return new GuildCommandException(plugin.getLanguage().getGeneralCategory().getTargetIsNotInGuild().replaceAll("\\{target}", player.getName()));
    }

    /**
     * Not online or exists guild command exception.
     *
     * @return the guild command exception
     */
    public static GuildCommandException notOnlineOrExists() {
        return new GuildCommandException(plugin.getLanguage().getGeneralCategory().getUserNotFound());
    }

    /**
     * Something went wrong guild command exception.
     *
     * @return the guild command exception
     */
    public static GuildCommandException somethingWentWrong() {
        return new GuildCommandException(plugin.getLanguage().getGeneralCategory().getSomethingWentWrong());
    }


}
