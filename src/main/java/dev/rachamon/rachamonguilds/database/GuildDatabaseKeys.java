package dev.rachamon.rachamonguilds.database;

import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.UUID;

/**
 * The type Guild database keys.
 */
public class GuildDatabaseKeys {

    /**
     * The Guild.
     */
    public static Key<Value<UUID>> GUILD;
    /**
     * The Guild data registration.
     */
    public static DataRegistration<GuildDatabase, GuildDatabase.Immutable> GUILD_DATA_REGISTRATION;
}
