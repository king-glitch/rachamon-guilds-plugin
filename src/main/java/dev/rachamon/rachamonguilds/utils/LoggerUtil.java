package dev.rachamon.rachamonguilds.utils;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;


public class LoggerUtil {
    private final ConsoleSource console;
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();
    private final long start = 0;

    public LoggerUtil(Server server) {
        this.console = server.getConsole();
    }

    public void info(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8][&bINFO&8]&7: &a" + message));
    }

    public void success(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8][&aSUCCESS&8]&7: &a" + message));
    }

    public void error(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8][&cERROR&8]&7: &a" + message));

    }

    public void warning(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8][&eWARNING&8]&7: &a" + message));

    }

    public void raw(String message) {
        console.sendMessage(RachamonGuildsUtil.toText(message));

    }

    public void debug(String message) {
        console.sendMessage(RachamonGuildsUtil.toText("&8[&4&lRachamon&8][&dDEBUG&8]&7: &a" + message));

    }

    public void timings(TimingType type, String message) {
//        if (plugin.getContainer() != null && plugin.getConfig().root().debug.timings) {
//            switch (type) {
//                case START:
//                    long diff = 0;
//                    if (System.currentTimeMillis() - start > 5000) start = 0;
//                    if (start != 0) {
//                        diff = System.currentTimeMillis() - start;
//                    }
//                    start = System.currentTimeMillis();
//                    console.sendMessage(RachamonGuildsUtil.toText("&3RachamonGuilds Timings - " + type + ": " + diff + "ms (" + message + "&3)&r"));
//                    break;
//                case END:
//                    console.sendMessage(RachamonGuildsUtil.toText("&3RachamonGuilds Timings - " + type + ": " + (System.currentTimeMillis() - start) + "ms (" + message + "&3)&r"));
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    public enum TimingType {
        START,
        END
    }
}
