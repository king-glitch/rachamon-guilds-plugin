package dev.rachamon.rachamonguilds.api.identifiable;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * The interface Identifiable.
 *
 * @param <ID> the type parameter
 */
public interface IIdentifiable<ID extends Serializable> {
    /**
     * Gets id.
     *
     * @return the id
     */
    @Nonnull
    ID getId();
}
