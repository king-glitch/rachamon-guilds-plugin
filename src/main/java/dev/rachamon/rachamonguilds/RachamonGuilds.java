package dev.rachamon.rachamonguilds;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "rachamonguilds",
        name = "RachamonGuilds",
        description = "A simple guild plugin",
        authors = {"Rachamon"}
)
public class RachamonGuilds {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }
}
