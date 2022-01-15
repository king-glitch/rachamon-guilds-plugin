package dev.rachamon.rachamonguilds.api.interfaces;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.plugin.PluginContainer;

public interface IRachamonGuildsHelper {
    Cause getCause(CommandSource src);
    Cause getCause(PluginContainer instance);
}
