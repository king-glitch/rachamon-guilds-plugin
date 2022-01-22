package dev.rachamon.rachamonguilds.api.entities;

import dev.rachamon.rachamonguilds.api.identifiable.ISpongeIdentifiable;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.UUID;

/**
 * The type Guild member.
 */
@ConfigSerializable
public class GuildMember implements ISpongeIdentifiable {

    @Setting(value = "uuid")
    private UUID uuid;

    @Setting(value = "first-join")
    private Date firstJoin;

    @Setting(value = "last-join")
    private Date lastJoin;

    /**
     * Instantiates a new Guild member.
     *
     * @param uuid      the uuid
     * @param firstJoin the first join
     * @param lastJoin  the last join
     */
    public GuildMember(UUID uuid, Date firstJoin, Date lastJoin) {
        this.uuid = uuid;
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
    }

    /**
     * Instantiates a new Guild member.
     *
     * @param uuid the uuid
     */
    public GuildMember(UUID uuid) {
        this.uuid = uuid;
        this.firstJoin = new Date();
        this.lastJoin = new Date();
    }

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets uuid.
     *
     * @param uuid the uuid
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets first join.
     *
     * @return the first join
     */
    public Date getFirstJoin() {
        return firstJoin;
    }

    /**
     * Sets first join.
     *
     * @param first_join the first join
     */
    public void setFirstJoin(Date first_join) {
        this.firstJoin = first_join;
    }

    /**
     * Gets last join.
     *
     * @return the last join
     */
    public Date getLastJoin() {
        return lastJoin;
    }

    /**
     * Sets last join.
     *
     * @param last_join the last join
     */
    public void setLastJoin(Date last_join) {
        this.lastJoin = last_join;
    }

    @Nonnull
    @Override
    public UUID getId() {
        return this.getUuid();
    }
}
