package dev.rachamon.rachamonguilds.hooks;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import me.rojo8399.placeholderapi.Placeholder;
import me.rojo8399.placeholderapi.PlaceholderService;
import me.rojo8399.placeholderapi.Source;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type Placeholder api hook service.
 */
public class PlaceholderAPIHookService {

    /**
     * Instantiates a new Placeholder api hook service.
     *
     * @param plugin the plugin
     */
    public PlaceholderAPIHookService(RachamonGuilds plugin) {
        PlaceholderService service = Sponge.getServiceManager().provideUnchecked(PlaceholderService.class);

        service.loadAll(this, plugin).forEach(builder -> {
            if (builder.getId().startsWith("rachamonguilds-")) {
                builder.author(plugin.getContainer().getAuthors().get(0));
                builder.version(plugin.getContainer().getVersion().get());
                try {
                    builder.buildAndRegister();
                } catch (Exception e) {
                    plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
                }
            }
        });
    }

    /**
     * Guild name string.
     *
     * @param commandSource the command source
     * @return the string
     */
    @Placeholder(id = "rachamonguilds-name")
    public String guildName(@Source CommandSource commandSource) {
        Player player = (Player) commandSource;
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(player);
        return guild.isPresent() ? guild.get().getName() : "";
    }

    /**
     * Guild display name string.
     *
     * @param commandSource the command source
     * @return the string
     */
    @Placeholder(id = "rachamonguilds-displayname")
    public String guildDisplayName(@Source CommandSource commandSource) {
        Player player = (Player) commandSource;
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(player);
        return guild.isPresent() ? guild.get().getDisplayName() : "";
    }

    /**
     * Guild id string.
     *
     * @param commandSource the command source
     * @return the string
     */
    @Placeholder(id = "rachamonguilds-id")
    public String guildId(@Source CommandSource commandSource) {
        Player player = (Player) commandSource;
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(player);
        return guild.map(value -> value.getId().toString()).orElse("");
    }

    /**
     * Guild role string.
     *
     * @param commandSource the command source
     * @return the string
     */
    @Placeholder(id = "rachamonguilds-role")
    public String guildRole(@Source CommandSource commandSource) {
        Player player = (Player) commandSource;
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(player);
        return guild.map(value -> value.getMaster().equals(player.getUniqueId()) ? "Master" : "Member").orElse("");
    }

    /**
     * Guild master string.
     *
     * @param commandSource the command source
     * @return the string
     */
    @Placeholder(id = "rachamonguilds-master")
    public String guildMaster(@Source CommandSource commandSource) {
        Player player = (Player) commandSource;
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(player);
        if (!guild.isPresent()) return "";
        Optional<Player> master = Sponge.getServer().getPlayer(guild.get().getMaster());
        return master.map(User::getName).orElse("");
    }

}
