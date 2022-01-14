package dev.rachamon.rachamonguilds.managers;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.RachamonGuildsModule;
import dev.rachamon.rachamonguilds.configs.RachamonGuildsConfig;
import dev.rachamon.rachamonguilds.services.hooks.PlaceholderAPIHookService;
import org.spongepowered.api.Sponge;

import java.io.IOException;

public class RachamonGuildsPluginManager {

    private final RachamonGuilds plugin = RachamonGuilds.getInstance();

    public void initialize() {
        this.plugin.setComponents(new RachamonGuilds.Components());
        this.plugin.setGuildInjector(this.plugin.getSpongeInjector().createChildInjector(new RachamonGuildsModule()));
        this.plugin.getGuildInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    public void preInitialize() {
    }

    public void start() {

    }

    public void postInitialize() throws IOException {

        this.plugin.setConfig(new RachamonGuildsConfig(this.plugin.getFactory()));

        if (Sponge.getPluginManager().getPlugin("placeholderapi").isPresent()) {
            new PlaceholderAPIHookService(this.plugin);
        }
    }


}
