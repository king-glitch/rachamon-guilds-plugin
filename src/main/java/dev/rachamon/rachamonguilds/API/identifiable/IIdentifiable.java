package dev.rachamon.rachamonguilds.api.identifiable;

import javax.annotation.Nonnull;
import java.io.Serializable;

public interface IIdentifiable<ID extends Serializable> {
    @Nonnull
    ID getId();
}
