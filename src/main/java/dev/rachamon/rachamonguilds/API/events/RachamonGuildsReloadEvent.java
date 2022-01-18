package dev.rachamon.rachamonguilds.api.events;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

import javax.annotation.Nonnull;

public class RachamonGuildsReloadEvent extends AbstractEvent implements Event {
    @Nonnull
    @Override
    public Cause getCause() {
        return RachamonGuilds.getInstance().getHelperUtil().getCause(RachamonGuilds.getInstance().getContainer());
    }
}
