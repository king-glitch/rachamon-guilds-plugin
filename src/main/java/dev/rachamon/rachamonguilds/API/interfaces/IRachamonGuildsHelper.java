package dev.rachamon.rachamonguilds.api.interfaces;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.plugin.PluginContainer;

/**
 * The interface Rachamon guilds helper.
 */
public interface IRachamonGuildsHelper {
    /**
     * Gets cause.
     *
     * @param src the src
     * @return the cause
     */
    Cause getCause(CommandSource src);

    /**
     * Gets cause.
     *
     * @param instance the instance
     * @return the cause
     */
    Cause getCause(PluginContainer instance);
}
