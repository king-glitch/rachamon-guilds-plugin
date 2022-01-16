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

public final class GuildService {
    private Map<UUID, Guild> guilds = new HashMap<>();
    private Map<UUID, GuildMember> members = new HashMap<>();

    public Guild addGuild(User master, String name, String displayName, Date creationDate, User... members) {
        Guild guild = new Guild(UUID.randomUUID(), name, displayName, creationDate, new HashSet<>());
        this.addMember(guild, new GuildMember(master.getUniqueId(), new Date(), new Date()));
        guild.setCreationDate(new Date());
        guild.setMaster(master.getUniqueId());

        for (User member : members) {
            this.addMember(guild, new GuildMember(member.getUniqueId(), new Date(), new Date()));
        }

        this.guilds.put(guild.getId(), guild);
        RachamonGuilds.getInstance().getLogger().debug("Creating Guild " + guild.getName());
        this.save();
        return guild;
    }

    public boolean removeGuild(Guild guild) {
        if (this.delete(guild.getId())) {
            this.guilds.remove(guild.getId());
            return true;
        }

        return false;
    }

    public Collection<Guild> getGuildCollection() {
        return this.guilds.values();
    }

    public Optional<Guild> getGuild(UUID uuid) {
        return Optional.ofNullable(guilds.get(uuid));
    }

    public Optional<Guild> getGuild(String name) {
        return this.guilds.values().stream().filter(guild -> guild.getName().equalsIgnoreCase(name)).findFirst();
    }


    public Collection<Guild> getGuildList() {
        return this.guilds.values();
    }

    public Map<UUID, Guild> getGuilds() {
        return this.guilds;
    }

    public Map<UUID, GuildMember> getMembers() {
        return this.members;
    }

    public void addMember(Guild guild, GuildMember member) {
        guild.addMember(member);
        this.save();
    }

    public void setGuilds(Map<UUID, Guild> guilds) {
        this.guilds = guilds;
    }

    public void setHome(Guild guild, World world, Location<World> location, Vector3d vector) {
        guild.setHome(world.getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + vector.getY());
        this.save();
    }

    public void setName(Guild guild, String name) {
        guild.setName(name);
        this.save();
    }

    public void setDisplayName(Guild guild, String name) {
        guild.setDisplayName(name);
        this.save();
    }

    public Collection<GuildMember> getGuildMembers(Guild guild) {
        return guild.getMembers()
                .stream()
                .filter(member -> Sponge.getServer().getPlayer(member.getUuid()).isPresent())
                .collect(Collectors.toSet());
    }

    public void removeMember(Guild guild, GuildMember member) {
        guild.removeMember(member.getUniqueId());
        this.save();
    }

    public void setGuildMaster(Guild guild, UUID uuid) {
        guild.setMaster(uuid);
        GuildDatabaseManager.save(guild);
    }

    public Optional<GuildMember> getGuildMember(Guild guild, UUID uuid) {
        guild.setMaster(uuid);
        return this.getMembers().values().stream().filter(member -> member.getUuid().equals(uuid)).findFirst();
    }

    public void save() {
        GuildDatabaseManager.setGuilds(this.getGuilds());
        GuildDatabaseManager.save();
    }

    public boolean delete(UUID uuid) {
        return GuildDatabaseManager.delete(uuid);
    }

}
