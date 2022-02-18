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

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Guild database.
 */
public class GuildDatabase extends AbstractData<GuildDatabase, GuildDatabase.Immutable> {

    private UUID guildUuid;

    /**
     * Instantiates a new Guild database.
     */
    GuildDatabase() {
        this.guildUuid = null;
        this.registerGettersAndSetters();
    }

    /**
     * Instantiates a new Guild database.
     *
     * @param guildUuid the guild uuid
     */
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

    @Nonnull
    @Override
    public Optional<GuildDatabase> fill(DataHolder dataHolder, @Nonnull MergeFunction overlap) {
        dataHolder.get(GuildDatabase.class).ifPresent(that -> {
            GuildDatabase data = overlap.merge(this, that);
            this.guildUuid = data.guildUuid;
        });
        return Optional.of(this);
    }

    @Nonnull
    @Override
    public Optional<GuildDatabase> from(@Nonnull DataContainer container) {
        return this.from((DataView) container);
    }

    /**
     * From optional.
     *
     * @param container the container
     * @return the optional
     */
    public Optional<GuildDatabase> from(DataView container) {
        container
                .getString(GuildDatabaseKeys.GUILD.getQuery())
                .ifPresent(v -> guildUuid = UUID.fromString(v));
        return Optional.of(this);
    }

    @Nonnull
    @Override
    public GuildDatabase copy() {
        return new GuildDatabase(this.guildUuid);
    }

    @Nonnull
    @Override
    public GuildDatabase.Immutable asImmutable() {
        return new Immutable(this.guildUuid);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer() {
        return super.toContainer().set(GuildDatabaseKeys.GUILD.getQuery(), this.guildUuid.toString());
    }


    /**
     * Gets guild uuid.
     *
     * @return the guild uuid
     */
    public UUID getGuildUuid() {
        return guildUuid;
    }

    /**
     * Sets guild uuid.
     *
     * @param guildUuid the guild uuid
     */
    public void setGuildUuid(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }

    /**
     * Gets guild.
     *
     * @return the guild
     */
    public Optional<Guild> getGuild() {
        return RachamonGuilds.getInstance().getGuildService().getGuild(this.guildUuid);
    }

    /**
     * Gets guild uuid value.
     *
     * @return the guild uuid value
     */
    public Value<UUID> getGuildUuidValue() {
        return Sponge.getRegistry().getValueFactory().createValue(GuildDatabaseKeys.GUILD, this.getGuildUuid());
    }

    /**
     * The type Immutable.
     */
    public static class Immutable extends AbstractImmutableData<Immutable, GuildDatabase> {

        private final UUID guildUuid;

        {
            registerGetters();
        }

        /**
         * Instantiates a new Immutable.
         */
        Immutable() {
            this.guildUuid = null;
        }

        /**
         * Instantiates a new Immutable.
         *
         * @param guildUuid the guild uuid
         */
        Immutable(UUID guildUuid) {
            this.guildUuid = guildUuid;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(GuildDatabaseKeys.GUILD, this::getGuildUuid);
            registerKeyValue(GuildDatabaseKeys.GUILD, this::getGuildUuid);
        }

        @Nonnull
        @Override
        public GuildDatabase asMutable() {
            return new GuildDatabase(this.guildUuid);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Nonnull
        @Override
        public DataContainer toContainer() {
            assert this.guildUuid != null;
            return super.toContainer().set(GuildDatabaseKeys.GUILD.getQuery(), this.guildUuid);
        }

        /**
         * Gets guild uuid.
         *
         * @return the guild uuid
         */
        public ImmutableValue<UUID> getGuildUuid() {
            assert this.guildUuid != null;
            return Sponge
                    .getRegistry()
                    .getValueFactory()
                    .createValue(GuildDatabaseKeys.GUILD, this.guildUuid)
                    .asImmutable();
        }

    }

    /**
     * The type Builder.
     */
    public static class Builder extends AbstractDataBuilder<GuildDatabase> implements DataManipulatorBuilder<GuildDatabase, Immutable> {
        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            super(GuildDatabase.class, 1);
        }

        @Nonnull
        @Override
        public GuildDatabase create() {
            return new GuildDatabase();
        }

        @Nonnull
        @Override
        public Optional<GuildDatabase> createFrom(@Nonnull DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Nonnull
        @Override
        protected Optional<GuildDatabase> buildContent(@Nonnull DataView container) throws InvalidDataException {
            return create().from((DataContainer) container);
        }
    }

}
