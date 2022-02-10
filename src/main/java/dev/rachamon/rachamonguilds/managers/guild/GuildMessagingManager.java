package dev.rachamon.rachamonguilds.managers.guild;

import com.google.inject.Inject;
import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.abstracts.AbstractMessagingManager;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;

/**
 * The type Guild messaging manager.
 */
public class GuildMessagingManager extends AbstractMessagingManager {

    @Inject
    private GuildManager guildManager;

    /**
     * Send guild info.
     *
     * @param guild    the guild
     * @param messages the messages
     */
    public void sendGuildInfo(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.info(member, messages);
        }
    }

    /**
     * Response.
     *
     * @param source   the source
     * @param messages the messages
     */
    public void response(CommandSource source, Object... messages) {
        this.info(source, messages);
    }

    /**
     * Send guild error.
     *
     * @param guild    the guild
     * @param messages the messages
     */
    public void sendGuildError(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.error(member, messages);
        }
    }

    /**
     * Send leave guild message.
     *
     * @param guild the guild
     * @param name  the name
     */
    public void sendLeaveGuildMessage(Guild guild, String name) {
        RachamonGuilds.getInstance().getGuildMessagingManager().sendGuildError(guild, TextUtil.toText(RachamonGuilds.getInstance().getLanguage().getGeneralCategory().getMemberLeaveGuild().replaceAll("\\{target}", name)));
    }

    /**
     * Send join guild message.
     *
     * @param guild the guild
     * @param name  the name
     */
    public void sendJoinGuildMessage(Guild guild, String name) {
        RachamonGuilds.getInstance().getGuildMessagingManager().sendGuildError(guild, TextUtil.toText(RachamonGuilds.getInstance().getLanguage().getGeneralCategory().getMemberJoinGuild().replaceAll("\\{target}", name)));
    }
}
