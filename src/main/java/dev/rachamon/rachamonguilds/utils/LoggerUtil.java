package dev.rachamon.rachamonguilds.utils;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;

import java.util.Arrays;


/**
 * The type Logger util.
 */
public class LoggerUtil {
    private final ConsoleSource console;
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    /**
     * Instantiates a new Logger util.
     *
     * @param server the server
     */
    public LoggerUtil(Server server) {
        this.console = server.getConsole();
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public void info(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&bINFO&8]&7: &a" + message));
    }

    /**
     * Success.
     *
     * @param message the message
     */
    public void success(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&aSUCCESS&8]&7: &a" + message));
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public void error(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&cERROR&8]&7: &a" + message));

    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public void warning(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&eWARNING&8]&7: &a" + message));
    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public void warning(java.lang.StackTraceElement[] message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&eWARNING&8]&7: &a" + Arrays.toString(message)));
    }

    /**
     * Raw.
     *
     * @param message the message
     */
    public void raw(String message) {
        console.sendMessage(RachamonGuildsUtil.toText(message));
    }

    /**
     * Debug.
     *
     * @param message the message
     */
    public void debug(String message) {
        if (plugin == null) return;
        if (plugin.getConfig() == null) return;
        if (!plugin.getConfig().getRoot().getGeneralCategorySetting().isDebug()) return;
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&dDEBUG&8]&7: &a" + message));

    }
}
