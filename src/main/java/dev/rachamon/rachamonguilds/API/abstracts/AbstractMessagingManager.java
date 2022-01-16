package dev.rachamon.rachamonguilds.api.abstracts;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;

public class AbstractMessagingManager {

    private static Text prefix;

    public Text formatInfo(Object... messages) {
        if (prefix == null) {
            prefix = RachamonGuildsUtil.toText(RachamonGuilds
                    .getInstance()
                    .getConfig()
                    .getLanguage()
                    .getGeneralCategory()
                    .getPrefix() + "&a");
        }

        return Text.of(prefix, Text.of(messages));
    }

    public Text formatError(Object... messages) {
        if (prefix == null) {
            prefix = RachamonGuildsUtil.toText(RachamonGuilds
                    .getInstance()
                    .getConfig()
                    .getLanguage()
                    .getGeneralCategory()
                    .getPrefix() + "&c");
        }
        return Text.of(prefix, Text.of(messages));
    }

    public void info(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(this.formatInfo(message));
    }

    public void error(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(this.formatError(message));
    }

}
