package dev.rachamon.rachamonguilds.listeners;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;

public class PluginListener {

    private final RachamonGuilds plugin;

    public PluginListener(RachamonGuilds plugin) {
        this.plugin = plugin.getInstance();
    }

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        this.plugin.getLogger().info("On Pre Initialize RachamonGuilds...");
        this.plugin.preInitialize();
    }

    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        this.plugin.getLogger().info("On Initialize RachamonGuilds...");
        this.plugin.initialize();
    }

    @Listener
    public void onStart(GameInitializationEvent event) {
        if (!this.plugin.getIsInitialized()) return;
        this.plugin.getLogger().info("On Start RachamonGuilds...");
        this.plugin.start();
    }

    @Listener
    public void onPostInitialize(GamePreInitializationEvent event) {
        this.plugin.getLogger().info("On Post Initialize RachamonGuilds");
        this.plugin.postInitialize();
    }

}
