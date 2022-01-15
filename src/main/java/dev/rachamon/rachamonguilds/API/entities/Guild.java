package dev.rachamon.rachamonguilds.api.entities;

import dev.rachamon.rachamonguilds.api.identifiable.ISpongeIdentifiable;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.Sponge;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

@ConfigSerializable
public class Guild implements ISpongeIdentifiable {

    @Setting(value = "uuid")
    private UUID uuid;

    @Setting(value = "name")
    private String name;

    @Setting(value = "display-name")
    private String displayName;

    @Setting(value = "members")
    private Set<GuildMember> members;

    @Setting(value = "master")
    private UUID master;

    @Setting(value = "creation-date")
    private Date creationDate;

    public Guild(UUID leader, String name, String displayName, Date creationDate, Set<GuildMember> members) {
        this.uuid = leader;
        this.name = name;
        this.displayName = displayName;
        this.members = members;
        this.creationDate = creationDate;
    }

    @Nonnull
    @Override
    public UUID getId() {
        return this.uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Set<GuildMember> getOnlineMembers(Guild guild) {
        return guild
                .getMembers()
                .stream()
                .map(member -> Sponge.getServer().getPlayer(member.getUuid()))
                .filter(Optional::isPresent)
                .map(player -> new GuildMember(player.get().getUniqueId()))
                .collect(Collectors.toSet());
    }

    public void setDisplayName(String display_name) {
        this.displayName = display_name;
    }

    public Set<GuildMember> getMembers() {
        return members;
    }

    public void addMember(GuildMember member) {
        this.members.add(member);
    }

    public void removeMember(GuildMember member) {
        this.members.remove(member);
    }

    public boolean hasMember(UUID uuid) {
        Optional<GuildMember> member = this.members.stream().filter(_member -> _member.getUuid().equals(uuid)).findFirst();
        return member.isPresent();
    }

    public void setMembers(Set<GuildMember> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return Objects.equals(uuid, guild.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public UUID getMaster() {
        return master;
    }

    public void setMaster(UUID master) {
        this.master = master;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
