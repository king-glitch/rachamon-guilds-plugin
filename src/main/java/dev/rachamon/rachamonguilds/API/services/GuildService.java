package dev.rachamon.rachamonguilds.api.services;

import com.flowpowered.math.vector.Vector3d;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.entities.GuildMember;
import dev.rachamon.rachamonguilds.managers.guild.GuildDatabaseManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Guild service.
 */
public final class GuildService {
    private Map<UUID, Guild> guilds = new HashMap<>();

    /**
     * Add guild.
     *
     * @param master       the master
     * @param name         the name
     * @param displayName  the display name
     * @param creationDate the creation date
     * @param members      the members
     * @return the guild
     */
    public Guild addGuild(User master, String name, String displayName, Date creationDate, User... members) {
        Guild guild = new Guild(UUID.randomUUID(), name, displayName, creationDate, new HashSet<>());
        this.addMember(guild, new GuildMember(master.getUniqueId(), new Date(), new Date()));
        guild.setCreationDate(new Date());
        guild.setMaster(master.getUniqueId());
        this.guilds.put(guild.getId(), guild);
        RachamonGuilds.getInstance().getLogger().debug("Creating Guild " + guild.getName());
        this.save();
        return guild;
    }

    /**
     * Remove guild boolean.
     *
     * @param guild the guild
     * @return the boolean
     */
    public boolean removeGuild(Guild guild) {
        if (this.delete(guild.getId())) {
            this.guilds.remove(guild.getId());
            return true;
        }

        return false;
    }


    /**
     * Gets guild.
     *
     * @param uuid the uuid
     * @return the guild
     */
    public Optional<Guild> getGuild(UUID uuid) {
        return Optional.ofNullable(guilds.get(uuid));
    }

    /**
     * Gets guild.
     *
     * @param name the name
     * @return the guild
     */
    public Optional<Guild> getGuild(String name) {
        return this.guilds.values().stream().filter(guild -> guild.getName().equalsIgnoreCase(name)).findFirst();
    }


    /**
     * Gets guild list.
     *
     * @return the guild list
     */
    public Collection<Guild> getGuildList() {
        return this.guilds.values();
    }

    /**
     * Gets guilds.
     *
     * @return the guilds
     */
    public Map<UUID, Guild> getGuilds() {
        return this.guilds;
    }

    /**
     * Add member.
     *
     * @param guild  the guild
     * @param member the member
     */
    public void addMember(Guild guild, GuildMember member) {
        guild.addMember(member);
        this.save();
    }

    /**
     * Sets guilds.
     *
     * @param guilds the guilds
     */
    public void setGuilds(Map<UUID, Guild> guilds) {
        this.guilds = guilds;
    }

    /**
     * Sets home.
     *
     * @param guild    the guild
     * @param world    the world
     * @param location the location
     * @param vector   the vector
     */
    public void setHome(Guild guild, World world, Location<World> location, Vector3d vector) {
        guild.setHome(world.getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + vector.getY());
        this.save();
    }

    /**
     * Sets name.
     *
     * @param guild the guild
     * @param name  the name
     */
    public void setName(Guild guild, String name) {
        guild.setName(name);
        this.save();
    }

    /**
     * Sets display name.
     *
     * @param guild the guild
     * @param name  the name
     */
    public void setDisplayName(Guild guild, String name) {
        guild.setDisplayName(name);
        this.save();
    }

    /**
     * Gets guild members.
     *
     * @param guild the guild
     * @return the guild members
     */
    public Collection<GuildMember> getGuildMembers(Guild guild) {
        return guild.getMembers().stream().filter(member -> Sponge.getServer().getPlayer(member.getUuid()).isPresent()).collect(Collectors.toSet());
    }

    /**
     * Remove member.
     *
     * @param guild the guild
     * @param uuid  the uuid
     */
    public void removeMember(Guild guild, UUID uuid) {
        guild.removeMember(uuid);
        this.save();
    }

    /**
     * Sets guild master.
     *
     * @param guild the guild
     * @param uuid  the uuid
     */
    public void setGuildMaster(Guild guild, UUID uuid) {
        guild.setMaster(uuid);
        GuildDatabaseManager.save(guild);
    }

    /**
     * Gets guild member.
     *
     * @param guild the guild
     * @param uuid  the uuid
     * @return the guild member
     */
    public Optional<GuildMember> getGuildMember(Guild guild, UUID uuid) {
        return guild.getMembers().stream().filter(member -> member.getUuid().equals(uuid)).findFirst();
    }

    /**
     * Save.
     */
    public void save() {
        GuildDatabaseManager.setGuilds(this.getGuilds());
        GuildDatabaseManager.save();
    }

    /**
     * Delete boolean.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    public boolean delete(UUID uuid) {
        return GuildDatabaseManager.delete(uuid);
    }

    /**
     * Sets motd.
     *
     * @param guild   the guild
     * @param message the message
     */
    public void setMotd(Guild guild, String message) {
        guild.setMotd(message);
        this.save();
    }

    /**
     * Sets member last join.
     *
     * @param guild    the guild
     * @param uuid     the uuid
     * @param lastJoin the last join
     */
    public void setMemberLastJoin(Guild guild, UUID uuid, Date lastJoin) {
        Optional<GuildMember> member = this.getGuildMember(guild, uuid);
        if (!member.isPresent()) return;
        member.get().setLastJoin(lastJoin);
        this.save();
    }
}
