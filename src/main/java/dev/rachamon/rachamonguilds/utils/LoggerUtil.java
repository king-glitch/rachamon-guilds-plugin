package dev.rachamon.rachamonguilds.utils;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;

import java.util.Arrays;


public class LoggerUtil {
    private final ConsoleSource console;
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    public LoggerUtil(Server server) {
        this.console = server.getConsole();
    }

    public void info(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&bINFO&8]&7: &a" + message));
    }

    public void success(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&aSUCCESS&8]&7: &a" + message));
    }

    public void error(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&cERROR&8]&7: &a" + message));

    }

    public void warning(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&eWARNING&8]&7: &a" + message));
    }

    public void warning(java.lang.StackTraceElement[] message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&eWARNING&8]&7: &a" + Arrays.toString(message)));
    }

    public void raw(String message) {
        console.sendMessage(RachamonGuildsUtil.toText(message));
    }

    public void debug(String message) {
        if (plugin == null) return;
        if (plugin.getConfig() == null) return;
        if (!plugin.getConfig().getRoot().getGeneralCategorySetting().isDebug()) return;
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8] [&dDEBUG&8]&7: &a" + message));

    }
}
