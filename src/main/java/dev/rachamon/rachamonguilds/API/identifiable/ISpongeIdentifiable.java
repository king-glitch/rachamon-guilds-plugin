package dev.rachamon.rachamonguilds.api.identifiable;

import org.spongepowered.api.util.Identifiable;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface ISpongeIdentifiable extends IIdentifiable<UUID>, Identifiable {
    @Override
    @Nonnull
    default UUID getUniqueId() {
        return getId();
    }
}
