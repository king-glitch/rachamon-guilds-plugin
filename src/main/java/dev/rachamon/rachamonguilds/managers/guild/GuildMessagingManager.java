package dev.rachamon.rachamonguilds.managers.guild;

import com.google.inject.Inject;
import dev.rachamon.rachamonguilds.api.abstracts.AbstractMessagingManager;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import org.spongepowered.api.entity.living.player.Player;

public class GuildMessagingManager extends AbstractMessagingManager {

    @Inject
    private GuildManager guildManager;

    public void sendGuildInfo(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.info(member, messages);
        }
    }

    public void sendGuildError(Guild guild, Object... messages) {
        for (Player member : this.guildManager.getOnlineGuildMembersAsPlayer(guild)) {
            this.error(member, messages);
        }
    }
}
