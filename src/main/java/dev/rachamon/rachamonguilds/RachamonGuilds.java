package dev.rachamon.rachamonguilds;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.rachamonguilds.configs.RachamonGuildsConfig;
import dev.rachamon.rachamonguilds.facades.GuildFacade;
import dev.rachamon.rachamonguilds.facades.GuildMessagingFacade;
import dev.rachamon.rachamonguilds.managers.RachamonGuildsPluginManager;
import dev.rachamon.rachamonguilds.services.GuildService;
import dev.rachamon.rachamonguilds.utils.LoggerUtil;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(
        id = "rachamonguilds",
        name = "RachamonGuilds",
        description = "A simple guild plugin",
        authors = {"Rachamon"},
        dependencies = {@Dependency(id = "placeholderapi", optional = true)}
)
public class RachamonGuilds {

    private static RachamonGuilds instance;
    private static boolean isInitialized = false;

    private RachamonGuildsPluginManager rachamonGuildsPluginManager;
    private Components components;
    private RachamonGuildsConfig config;

    private LoggerUtil logger;
    @Inject
    private PluginContainer container;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    public GuiceObjectMapperFactory factory;

    @Inject
    private Injector guildInjector;

    @Inject
    Injector spongeInjector;

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        instance = this;
        this.logger = new LoggerUtil(Sponge.getServer());
        this.rachamonGuildsPluginManager = new RachamonGuildsPluginManager();

        this.getPluginManager().preInitialize();
        this.getLogger().info("On Pre Initialize RachamonGuilds...");
    }

    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        getInstance().getLogger().info("On Initialize RachamonGuilds...");
        getInstance().getPluginManager().initialize();
    }

    @Listener
    public void onStart(GameInitializationEvent event) {
        if (!getInstance().getIsInitialized()) return;
        getInstance().getLogger().info("On Start RachamonGuilds...");
        getInstance().getPluginManager().start();
    }

    @Listener
    public void onPostInitialize(GamePostInitializationEvent event) throws IOException {
        getInstance().getLogger().info("On Post Initialize RachamonGuilds");
        getInstance().getPluginManager().postInitialize();
    }

    public void reload() throws IOException {
        this.config = new RachamonGuildsConfig(this.factory);
    }

    public static RachamonGuilds getInstance() {
        return instance;
    }

    public Path getDirectory() {
        return this.directory;
    }

    public LoggerUtil getLogger() {
        return this.logger;
    }

    public boolean getIsInitialized() {
        return isInitialized;
    }

    public RachamonGuildsPluginManager getPluginManager() {
        return this.rachamonGuildsPluginManager;
    }

    public GuiceObjectMapperFactory getFactory() {
        return this.factory;
    }

    public void setIsInitialized(boolean isInit) {
        isInitialized = isInit;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public void setConfig(RachamonGuildsConfig config) {
        this.config = config;
    }

    public void setLogger(LoggerUtil logger) {
        this.logger = logger;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    public GuildFacade getGuildFacade() {
        return this.getComponents().guildFacade;
    }

    public Injector getGuildInjector() {
        return this.guildInjector;
    }

    public Injector getSpongeInjector() {
        return this.spongeInjector;
    }

    public void setGuildInjector(Injector injector) {
        this.guildInjector = injector;
    }

    public void setSpongeInjector(Injector injector) {
        this.spongeInjector = injector;
    }

    public RachamonGuildsConfig getConfig() {
        return this.config;
    }

    public GuildService getGuildService() {
        return this.getComponents().guildService;
    }

    public GuildMessagingFacade getGuildMessagingFacade() {
        return this.getComponents().guildMessagingFacade;
    }

    public Components getComponents() {
        return this.components;
    }

    public static class Components {
        @Inject
        private GuildFacade guildFacade;

        @Inject
        private GuildMessagingFacade guildMessagingFacade;

        @Inject
        private GuildService guildService;
    }

}
