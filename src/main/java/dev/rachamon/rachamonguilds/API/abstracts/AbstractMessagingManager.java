package dev.rachamon.rachamonguilds.api.abstracts;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;

/**
 * The type Abstract messaging manager.
 */
public class AbstractMessagingManager {

    private static Text prefix;

    /**
     * Format info text.
     *
     * @param messages the messages
     * @return the text
     */
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

    /**
     * Format error text.
     *
     * @param messages the messages
     * @return the text
     */
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

    /**
     * Info.
     *
     * @param receiver the receiver
     * @param message  the message
     */
    public void info(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(this.formatInfo(message));
    }

    /**
     * Error.
     *
     * @param receiver the receiver
     * @param message  the message
     */
    public void error(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(this.formatError(message));
    }

}
