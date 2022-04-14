package dev.rachamon.rachamonguilds.managers.plugin;

import dev.rachamon.api.sponge.config.SpongeAPIConfigFactory;
import dev.rachamon.api.sponge.exception.AnnotatedCommandException;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPluginManager;
import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.RachamonGuildsModule;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.events.RachamonGuildsReloadEvent;
import dev.rachamon.rachamonguilds.commands.GuildCommand;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import dev.rachamon.rachamonguilds.configs.MainConfig;
import dev.rachamon.rachamonguilds.database.GuildDatabase;
import dev.rachamon.rachamonguilds.database.GuildDatabaseKeys;
import dev.rachamon.rachamonguilds.hooks.PlaceholderAPIHookService;
import dev.rachamon.rachamonguilds.managers.guild.GuildDatabaseManager;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsHelperUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The type Guild plugin manager.
 */
public class GuildPluginManager implements IRachamonPluginManager {

    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    /**
     * Initialize.
     */
    public void initialize() {
        this.plugin.setComponents(new RachamonGuilds.Components());
        this.plugin.setSpongeInjector(this.plugin.getSpongeInjector().createChildInjector(new RachamonGuildsModule()));
        this.plugin.getSpongeInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    /**
     * Pre initialize.
     */
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

    /**
     * Start.
     */
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

    /**
     * Reload.
     *
     */
    public void reload() {

        this.plugin.getLogger().debug("Reloading Rachamon Guilds...");

        try {
            this.configureConfigs();
            this.plugin.getCommandService().register(new GuildCommand(), this.plugin);
        } catch (Exception ignored) {
        }

        Sponge.getEventManager().post(new RachamonGuildsReloadEvent());

        this.plugin.getLogger().debug("Rachamon Guilds reloaded");

    }

    private void configureConfigs() {
        SpongeAPIConfigFactory<RachamonGuilds, MainConfig> config = new SpongeAPIConfigFactory<>(this.plugin, "main.conf");
        SpongeAPIConfigFactory<RachamonGuilds, LanguageConfig> language = new SpongeAPIConfigFactory<>(this.plugin, "language.conf");
        this.plugin.setConfigManager(config);
        this.plugin.setLanguageManager(language);
        this.plugin.setMainConfig(config
                .setHeader("Main Config")
                .setClazz(new MainConfig())
                .setClazzType(MainConfig.class)
                .build());
        this.plugin.setLanguageConfig(language
                .setHeader("Language Config")
                .setClazz(new LanguageConfig())
                .setClazzType(LanguageConfig.class)
                .build());
    }

    /**
     * Send guild join motd.
     *
     * @param source the source
     */
    public void sendGuildJoinMotd(Player source) {
        try {
            Sponge.getScheduler().createTaskBuilder().execute(() -> {
                Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(source);
                if (!guild.isPresent()) return;
                if (guild.get().getMotd() == null) return;
                if (guild.get().getMotd().isEmpty()) return;
                plugin.getGuildMessagingManager().sendGuildInfo(guild.get(), TextUtil.toText(guild.get().getMotd()));
            }).delay(2, TimeUnit.SECONDS).submit(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save player last join.
     *
     * @param source the source
     */
    public void savePlayerLastJoin(Player source) {
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild(source);
        if (!guild.isPresent()) return;
        try {
            plugin.getGuildManager().setLastJoin(source, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Post initialize.
     */
    public void postInitialize() {

        this.configureConfigs();
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
