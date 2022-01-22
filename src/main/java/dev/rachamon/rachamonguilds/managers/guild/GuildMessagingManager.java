package dev.rachamon.rachamonguilds.managers.guild;

import com.google.inject.Inject;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.abstracts.AbstractMessagingManager;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;

public class GuildMessagingManager extends AbstractMessagingManager {

    @Inject
    private GuildManager guildManager;

    public void sendGuildInfo(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.info(member, messages);
        }
    }

    public void response(CommandSource source, Object... messages) {
        this.info(source, messages);
    }

    public void sendGuildError(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.error(member, messages);
        }
    }

    public void sendLeaveGuildMessage(Guild guild, String name) {
        RachamonGuilds.getInstance().getGuildMessagingManager().sendGuildError(guild, RachamonGuildsUtil.toText(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategory().getMemberLeaveGuild().replaceAll("\\{target}", name)));
    }

    public void sendJoinGuildMessage(Guild guild, String name) {
        RachamonGuilds.getInstance().getGuildMessagingManager().sendGuildError(guild, RachamonGuildsUtil.toText(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategory().getMemberJoinGuild().replaceAll("\\{target}", name)));
    }
}
