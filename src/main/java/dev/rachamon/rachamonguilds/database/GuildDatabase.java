package dev.rachamon.rachamonguilds.database;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;
import java.util.UUID;

public class GuildDatabase extends AbstractData<GuildDatabase, GuildDatabase.Immutable> {

    private UUID guildUuid;

    GuildDatabase() {
        this.guildUuid = null;
        this.registerGettersAndSetters();
    }

    public GuildDatabase(UUID guildUuid) {
        this.guildUuid = guildUuid;
        this.registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        if (GuildDatabaseKeys.GUILD == null) return;
        if (this.guildUuid == null) return;

        registerFieldGetter(GuildDatabaseKeys.GUILD, this::getGuildUuid);
        registerFieldSetter(GuildDatabaseKeys.GUILD, this::setGuildUuid);
        registerKeyValue(GuildDatabaseKeys.GUILD, this::getGuildUuidValue);
    }

    @Override
    public Optional<GuildDatabase> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(GuildDatabase.class).ifPresent(that -> {
            GuildDatabase data = overlap.merge(this, that);
            this.guildUuid = data.guildUuid;
        });
        return Optional.of(this);
    }

    @Override
    public Optional<GuildDatabase> from(DataContainer container) {
        return this.from((DataView) container);
    }

    public Optional<GuildDatabase> from(DataView container) {
        container
                .getString(GuildDatabaseKeys.GUILD.getQuery())
                .ifPresent(v -> guildUuid = UUID.fromString(v));
        return Optional.of(this);
    }

    @Override
    public GuildDatabase copy() {
        return new GuildDatabase(this.guildUuid);
    }

    @Override
    public GuildDatabase.Immutable asImmutable() {
        return new Immutable(this.guildUuid);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer().set(GuildDatabaseKeys.GUILD.getQuery(), this.guildUuid.toString());
    }


    public UUID getGuildUuid() {
        return guildUuid;
    }

    public void setGuildUuid(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }

    public Optional<Guild> getGuild() {
        return RachamonGuilds.getInstance().getGuildService().getGuild(this.guildUuid);
    }

    public Value<UUID> getGuildUuidValue() {
        return Sponge.getRegistry().getValueFactory().createValue(GuildDatabaseKeys.GUILD, this.getGuildUuid());
    }

    public static class Immutable extends AbstractImmutableData<Immutable, GuildDatabase> {

        private UUID guildUuid;

        {
            registerGetters();
        }

        Immutable() {
            this.guildUuid = null;
        }

        Immutable(UUID guildUuid) {
            this.guildUuid = guildUuid;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(GuildDatabaseKeys.GUILD, this::getGuildUuid);
            registerKeyValue(GuildDatabaseKeys.GUILD, this::getGuildUuid);
        }

        @Override
        public GuildDatabase asMutable() {
            return new GuildDatabase(this.guildUuid);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer().set(GuildDatabaseKeys.GUILD.getQuery(), this.guildUuid);
        }

        public ImmutableValue<UUID> getGuildUuid() {
            return Sponge
                    .getRegistry()
                    .getValueFactory()
                    .createValue(GuildDatabaseKeys.GUILD, this.guildUuid)
                    .asImmutable();
        }

    }

    public static class Builder extends AbstractDataBuilder<GuildDatabase> implements DataManipulatorBuilder<GuildDatabase, Immutable> {
        public Builder() {
            super(GuildDatabase.class, 1);
        }

        @Override
        public GuildDatabase create() {
            return new GuildDatabase();
        }

        @Override
        public Optional<GuildDatabase> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<GuildDatabase> buildContent(DataView container) throws InvalidDataException {
            return create().from((DataContainer) container);
        }
    }

}
