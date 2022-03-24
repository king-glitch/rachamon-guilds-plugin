package dev.rachamon.rachamonguilds;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.api.sponge.command.SpongeCommandService;
import dev.rachamon.api.sponge.config.SpongeAPIConfigFactory;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPlugin;
import dev.rachamon.api.sponge.util.LoggerUtil;
import dev.rachamon.rachamonguilds.api.services.GuildService;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import dev.rachamon.rachamonguilds.configs.MainConfig;
import dev.rachamon.rachamonguilds.managers.guild.GuildManager;
import dev.rachamon.rachamonguilds.managers.guild.GuildMessagingManager;
import dev.rachamon.rachamonguilds.managers.plugin.GuildPluginManager;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsHelperUtil;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The type Rachamon guilds.
 */
@Plugin(id = "rachamonguilds", name = "RachamonGuilds", description = "A simple guild plugin", authors = {"Rachamon"}, dependencies = {@Dependency(id = "placeholderapi", optional = true)})
public class RachamonGuilds implements IRachamonPlugin {

    private static RachamonGuilds instance;
    private static boolean isInitialized = false;

    private GuildPluginManager rachamonGuildsPluginManager;
    private Components components;
    private SpongeAPIConfigFactory<RachamonGuilds, MainConfig> config;
    private SpongeAPIConfigFactory<RachamonGuilds, LanguageConfig> language;
    private LoggerUtil logger;
    private RachamonGuildsHelperUtil helperUtil;

    @Inject
    private PluginContainer container;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    private GuiceObjectMapperFactory factory;

    @Inject
    private Injector guildInjector;


    /**
     * The Sponge injector.
     */
    @Inject
    Injector spongeInjector;

    /**
     * On pre initialize.
     *
     * @param event the event
     */
    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        instance = this;
        this.setLogger(new LoggerUtil(Sponge.getServer()));
        this.rachamonGuildsPluginManager = new GuildPluginManager();

        this.getPluginManager().preInitialize();
        this.getLogger().info("On Pre Initialize RachamonGuilds...");
    }

    /**
     * On initialize.
     *
     * @param event the event
     */
    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        getInstance().getLogger().info("On Initialize RachamonGuilds...");
        getInstance().getPluginManager().initialize();
    }

    /**
     * On start.
     *
     * @param event the event
     */
    @Listener
    public void onStart(GameInitializationEvent event) {
        if (!getInstance().getIsInitialized()) return;
        getInstance().getLogger().info("On Start RachamonGuilds...");
        getInstance().getPluginManager().start();
    }

    /**
     * On post initialize.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @Listener(order = Order.LAST)
    public void onPostInitialize(GamePostInitializationEvent event) throws IOException {
        getInstance().getLogger().info("On Post Initialize RachamonGuilds");
        getInstance().getPluginManager().postInitialize();
    }

    /**
     * On reload.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @Listener
    public void onReload(GameReloadEvent event) throws IOException {
        getInstance().getLogger().info("On Plugin Reload");
        getInstance().getPluginManager().reload();
    }

    /**
     * On player join.
     *
     * @param event the event
     */
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        getInstance().getPluginManager().sendGuildJoinMotd((Player) event.getSource());
    }

    /**
     * On player quit.
     *
     * @param event the event
     */
    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        getInstance().getPluginManager().savePlayerLastJoin((Player) event.getSource());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RachamonGuilds getInstance() {
        return instance;
    }

    /**
     * Gets directory.
     *
     * @return the directory
     */
    public Path getDirectory() {
        return this.directory;
    }

    /**
     * Gets logger.
     *
     * @return the logger
     */
    public LoggerUtil getLogger() {
        return this.logger;
    }

    @Override
    public void setLogger(LoggerUtil logger) {
        this.logger = logger;
    }

    /**
     * Gets is initialized.
     *
     * @return the is initialized
     */
    public boolean getIsInitialized() {
        return isInitialized;
    }

    /**
     * Gets plugin manager.
     *
     * @return the plugin manager
     */
    public GuildPluginManager getPluginManager() {
        return this.rachamonGuildsPluginManager;
    }

    /**
     * Gets factory.
     *
     * @return the factory
     */
    public GuiceObjectMapperFactory getFactory() {
        return this.factory;
    }

    /**
     * Gets container.
     *
     * @return the container
     */
    public PluginContainer getContainer() {
        return this.container;
    }

    /**
     * Gets guild manager.
     *
     * @return the guild manager
     */
    public GuildManager getGuildManager() {
        return this.getComponents().guildManager;
    }

    /**
     * Gets guild injector.
     *
     * @return the guild injector
     */
    public Injector getGuildInjector() {
        return this.guildInjector;
    }

    /**
     * Gets sponge injector.
     *
     * @return the sponge injector
     */
    public Injector getSpongeInjector() {
        return this.spongeInjector;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public Injector getBotInjector() {
        return null;
    }

    /**
     * Sets is initialized.
     *
     * @param isInit the is init
     */
    public void setIsInitialized(boolean isInit) {
        isInitialized = isInit;
    }

    /**
     * Sets components.
     *
     * @param components the components
     */
    public void setComponents(Components components) {
        this.components = components;
    }

    /**
     * Sets guild injector.
     *
     * @param injector the injector
     */
    public void setGuildInjector(Injector injector) {
        this.guildInjector = injector;
    }

    /**
     * Sets language manager.
     *
     * @param language the language
     */
    public void setLanguageManager(SpongeAPIConfigFactory<RachamonGuilds, LanguageConfig> language) {
        this.language = language;
    }

    /**
     * Sets config manager.
     *
     * @param config the config
     */
    public void setConfigManager(SpongeAPIConfigFactory<RachamonGuilds, MainConfig> config) {
        this.config = config;
    }

    /**
     * Sets main config.
     *
     * @param config the config
     */
    public void setMainConfig(MainConfig config) {
        this.config.setClazz(config);
    }

    /**
     * Sets language config.
     *
     * @param config the config
     */
    public void setLanguageConfig(LanguageConfig config) {
        this.language.setClazz(config);
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public LanguageConfig getLanguage() {
        return language.getRoot();
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public MainConfig getConfig() {
        return config.getRoot();
    }

    /**
     * Gets guild service.
     *
     * @return the guild service
     */
    public GuildService getGuildService() {
        return this.getComponents().guildService;
    }

    /**
     * Gets guild messaging manager.
     *
     * @return the guild messaging manager
     */
    public GuildMessagingManager getGuildMessagingManager() {
        return this.getComponents().guildMessagingFacade;
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public Components getComponents() {
        return this.components;
    }

    /**
     * Gets command service.
     *
     * @return the command service
     */
    public SpongeCommandService getCommandService() {
        return SpongeCommandService.getInstance();
    }

    /**
     * Gets helper util.
     *
     * @return the helper util
     */
    public RachamonGuildsHelperUtil getHelperUtil() {
        return helperUtil;
    }

    /**
     * Sets helper util.
     *
     * @param helperUtil the helper util
     */
    public void setHelperUtil(RachamonGuildsHelperUtil helperUtil) {
        this.helperUtil = helperUtil;
    }

    /**
     * The type Components.
     */
    public static class Components {
        @Inject
        private GuildManager guildManager;

        @Inject
        private GuildMessagingManager guildMessagingFacade;

        @Inject
        private GuildService guildService;
    }

}
