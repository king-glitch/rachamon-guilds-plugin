package dev.rachamon.rachamonguilds.database;

import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.UUID;

public class GuildDatabaseKeys {

    public static Key<Value<UUID>> GUILD;
    public static DataRegistration<GuildDatabase, GuildDatabase.Immutable> GUILD_DATA_REGISTRATION;
}
