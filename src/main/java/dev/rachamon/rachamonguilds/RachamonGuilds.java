package dev.rachamon.rachamonguilds;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.rachamonguilds.facades.GuildFacade;
import dev.rachamon.rachamonguilds.facades.GuildMessagingFacade;
import dev.rachamon.rachamonguilds.listeners.PluginListener;
import dev.rachamon.rachamonguilds.services.GuildService;
import dev.rachamon.rachamonguilds.utils.LoggerUtil;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

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

    private Components components;

    private LoggerUtil logger;
    @Inject
    private PluginContainer container;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    Injector spongeInjector;

    public RachamonGuilds() {
        new PluginListener(this);
    }

    public void initialize() {
        instance = this;
        this.components = new Components();
        Injector rachamonGuildsInjector = spongeInjector.createChildInjector(new RachamonGuildsModule());
        rachamonGuildsInjector.injectMembers(components);
        this.setIsInitialized(true);
    }

    public void preInitialize() {

    }

    public void start() {

    }

    public void postInitialize() {
        if (Sponge.getPluginManager().getPlugin("placeholderapi").isPresent()) {
            // get placeholderapi
        }
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

    public void setIsInitialized(boolean isInit) {
        isInitialized = isInit;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    public GuildFacade getGuildFacade() {
        return this.getComponents().guildFacade;
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

    private static class Components {
        @Inject
        private GuildFacade guildFacade;

        @Inject
        private GuildMessagingFacade guildMessagingFacade;

        @Inject
        private GuildService guildService;
    }

}
