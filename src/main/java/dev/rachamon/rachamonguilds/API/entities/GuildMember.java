package dev.rachamon.rachamonguilds.api.entities;

import dev.rachamon.rachamonguilds.api.identifiable.ISpongeIdentifiable;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.UUID;

@ConfigSerializable
public class GuildMember implements ISpongeIdentifiable {

    @Setting(value = "uuid")
    private UUID uuid;

    @Setting(value = "first-join")
    private Date firstJoin;

    @Setting(value = "last-join")
    private Date lastJoin;

    public GuildMember(UUID uuid, Date firstJoin, Date lastJoin) {
        this.uuid = uuid;
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
    }

    public GuildMember(UUID uuid) {
        this.uuid = uuid;
        this.firstJoin = new Date();
        this.lastJoin = new Date();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getFirstJoin() {
        return firstJoin;
    }

    public void setFirstJoin(Date first_join) {
        this.firstJoin = first_join;
    }

    public Date getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(Date last_join) {
        this.lastJoin = last_join;
    }

    @Nonnull
    @Override
    public UUID getId() {
        return this.getUuid();
    }
}
