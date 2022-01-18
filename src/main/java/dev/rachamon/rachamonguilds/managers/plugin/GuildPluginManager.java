package dev.rachamon.rachamonguilds.managers.plugin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.RachamonGuildsModule;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.events.RachamonGuildsReloadEvent;
import dev.rachamon.rachamonguilds.api.exceptions.AnnotatedCommandException;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.commands.GuildCommand;
import dev.rachamon.rachamonguilds.configs.RachamonGuildsConfig;
import dev.rachamon.rachamonguilds.database.GuildDatabase;
import dev.rachamon.rachamonguilds.database.GuildDatabaseKeys;
import dev.rachamon.rachamonguilds.hooks.PlaceholderAPIHookService;
import dev.rachamon.rachamonguilds.managers.guild.GuildDatabaseManager;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsHelperUtil;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.entity.living.player.Player;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class GuildPluginManager {

    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    public void initialize() {
        this.plugin.setComponents(new RachamonGuilds.Components());
        this.plugin.setGuildInjector(this.plugin.getSpongeInjector().createChildInjector(new RachamonGuildsModule()));
        this.plugin.getGuildInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    public void preInitialize() {
        GuildDatabaseKeys.GUILD_DATA_REGISTRATION = DataRegistration
                .builder()
                .dataClass(GuildDatabase.class)
                .immutableClass(GuildDatabase.Immutable.class)
                .builder(new GuildDatabase.Builder())
                .name("Guild")
                .id("guild")
                .build();
    }

    public void start() {

        this.plugin.getLogger().debug("Initializing Databases...");

        try {
            GuildDatabaseManager.initialize();
            this.plugin.getGuildService().setGuilds(GuildDatabaseManager.getGuilds());
            this.plugin.getLogger().debug("Initialized Databases");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() throws IOException {

        this.plugin.getLogger().debug("Reloading Rachamon Guilds...");
        this.plugin.setConfig(new RachamonGuildsConfig(this.plugin.getFactory()));

        try {
            this.plugin.getCommandService().register(new GuildCommand(), this.plugin);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Sponge.getEventManager().post(new RachamonGuildsReloadEvent());

        this.plugin.getLogger().debug("Rachamon Guilds reloaded");

    }

    public void sendGuildJoinMotd(Player source) {
        try {
            Sponge.getScheduler().createTaskBuilder()
                    .execute(() -> {
                        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(source);
                        if (!guild.isPresent()) return;
                        if (guild.get().getMotd().isEmpty()) return;
                        plugin.getGuildMessagingManager().sendGuildInfo(guild.get(), RachamonGuildsUtil.toText(guild.get().getMotd()));
                    })
                    .delay(2, TimeUnit.SECONDS)
                    .submit(plugin);
        } catch (Exception ignored) {
        }
    }

    public void savePlayerLastJoin(Player source) {
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(source);
        if (!guild.isPresent()) return;
        try {
            plugin.getGuildManager().setLastJoin(source, new Date());
        } catch (Exception ignored) {
        }
    }

    public void postInitialize() throws IOException {

        this.plugin.setConfig(new RachamonGuildsConfig(this.plugin.getFactory()));
        this.plugin.setHelperUtil(new RachamonGuildsHelperUtil());
        this.plugin.getLogger().debug("Initializing Commands...");

        try {
            this.plugin.getCommandService().register(new GuildCommand(), this.plugin);
            this.plugin.getLogger().debug("Initialized Commands");
        } catch (AnnotatedCommandException e) {
            e.printStackTrace();
        }

        if (Sponge.getPluginManager().getPlugin("placeholderapi").isPresent()) {
            this.plugin.getLogger().info("Hooking PlaceholderAPI");
            new PlaceholderAPIHookService(this.plugin);
        }
    }
}
