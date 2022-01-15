package dev.rachamon.rachamonguilds;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.rachamonguilds.api.services.CommandService;
import dev.rachamon.rachamonguilds.api.services.GuildService;
import dev.rachamon.rachamonguilds.configs.RachamonGuildsConfig;
import dev.rachamon.rachamonguilds.managers.guild.GuildManager;
import dev.rachamon.rachamonguilds.managers.guild.GuildMessagingManager;
import dev.rachamon.rachamonguilds.managers.plugin.GuildPluginManager;
import dev.rachamon.rachamonguilds.utils.LoggerUtil;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsHelperUtil;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.GameReloadEvent;
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

    private GuildPluginManager rachamonGuildsPluginManager;
    private Components components;
    private RachamonGuildsConfig config;
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

    @Inject
    Injector spongeInjector;

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        instance = this;
        this.setLogger(new LoggerUtil(Sponge.getServer()));
        this.rachamonGuildsPluginManager = new GuildPluginManager();

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

    @Listener
    public void onReload(GameReloadEvent event) throws IOException {
        getInstance().getLogger().info("On Plugin Reload");
        getInstance().getPluginManager().reload();
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

    public GuildPluginManager getPluginManager() {
        return this.rachamonGuildsPluginManager;
    }

    public GuiceObjectMapperFactory getFactory() {
        return this.factory;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    public GuildManager getGuildManager() {
        return this.getComponents().guildManager;
    }

    public Injector getGuildInjector() {
        return this.guildInjector;
    }

    public Injector getSpongeInjector() {
        return this.spongeInjector;
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

    public void setGuildInjector(Injector injector) {
        this.guildInjector = injector;
    }

    public RachamonGuildsConfig getConfig() {
        return this.config;
    }

    public GuildService getGuildService() {
        return this.getComponents().guildService;
    }

    public GuildMessagingManager getGuildMessagingManager() {
        return this.getComponents().guildMessagingFacade;
    }

    public Components getComponents() {
        return this.components;
    }

    public CommandService getCommandService() {
        return CommandService.getInstance();
    }

    public RachamonGuildsHelperUtil getHelperUtil() {
        return helperUtil;
    }

    public void setHelperUtil(RachamonGuildsHelperUtil helperUtil) {
        this.helperUtil = helperUtil;
    }

    public static class Components {
        @Inject
        private GuildManager guildManager;

        @Inject
        private GuildMessagingManager guildMessagingFacade;

        @Inject
        private GuildService guildService;
    }

}
