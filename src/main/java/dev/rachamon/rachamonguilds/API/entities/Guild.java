package dev.rachamon.rachamonguilds.api.entities;

import dev.rachamon.rachamonguilds.api.identifiable.ISpongeIdentifiable;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.Sponge;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Guild.
 */
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

    @Setting(value = "home")
    private String home;

    @Setting(value = "motd")
    private String motd;

    /**
     * Instantiates a new Guild.
     *
     * @param leader       the leader
     * @param name         the name
     * @param displayName  the display name
     * @param creationDate the creation date
     * @param members      the members
     */
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets online members.
     *
     * @param guild the guild
     * @return the online members
     */
    public Set<GuildMember> getOnlineMembers(Guild guild) {
        return guild
                .getMembers()
                .stream()
                .map(member -> Sponge.getServer().getPlayer(member.getUuid()))
                .filter(Optional::isPresent)
                .map(player -> new GuildMember(player.get().getUniqueId()))
                .collect(Collectors.toSet());
    }

    /**
     * Sets display name.
     *
     * @param display_name the display name
     */
    public void setDisplayName(String display_name) {
        this.displayName = display_name;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public Set<GuildMember> getMembers() {
        return members;
    }

    /**
     * Gets members uuid.
     *
     * @return the members uuid
     */
    public List<UUID> getMembersUuid() {
        return members.stream().map(GuildMember::getUuid).collect(Collectors.toList());
    }

    /**
     * Add member.
     *
     * @param member the member
     */
    public void addMember(GuildMember member) {
        this.members.add(member);
    }

    /**
     * Remove member.
     *
     * @param uuid the uuid
     */
    public void removeMember(UUID uuid) {
        this.setMembers(this.members.stream()
                .filter(member -> !member.getUuid().equals(uuid))
                .collect(Collectors.toSet()));
    }

    /**
     * Has member boolean.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    public boolean hasMember(UUID uuid) {
        Optional<GuildMember> member = this.members.stream().filter(m -> m.getUuid().equals(uuid)).findFirst();
        return !member.isPresent();
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
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

    /**
     * Gets master.
     *
     * @return the master
     */
    public UUID getMaster() {
        return master;
    }

    /**
     * Sets master.
     *
     * @param master the master
     */
    public void setMaster(UUID master) {
        this.master = master;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets home.
     *
     * @return the home
     */
    public String getHome() {
        return home;
    }

    /**
     * Sets home.
     *
     * @param home the home
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * Gets motd.
     *
     * @return the motd
     */
    public String getMotd() {
        return motd;
    }

    /**
     * Sets motd.
     *
     * @param motd the motd
     */
    public void setMotd(String motd) {
        this.motd = motd;
    }
}
