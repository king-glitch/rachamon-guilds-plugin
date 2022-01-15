package dev.rachamon.rachamonguilds.api.services;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.entities.GuildMember;
import dev.rachamon.rachamonguilds.database.GuildDatabase;
import dev.rachamon.rachamonguilds.managers.guild.GuildDatabaseManager;
import dev.rachamon.rachamonguilds.utils.UserUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;

import java.util.*;
import java.util.stream.Collectors;

public final class GuildService {
    private Map<UUID, Guild> guilds = new HashMap<>();
    private Map<UUID, GuildMember> members = new HashMap<>();

    public Guild createGuild(User master, String name, String displayName, Date creationDate, User... members) {
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
//        member.offer(new GuildDatabase(guild.getUniqueId()));
        this.save();
    }

    public void setGuilds(Map<UUID, Guild> guilds) {
        this.guilds = guilds;
    }

    public void setMembers(Map<UUID, GuildMember> members) {
        this.members = members;
    }

    public Collection<GuildMember> getGuildMembers(Guild guild) {
        return guild.getMembers()
                .stream()
                .filter(member -> Sponge.getServer().getPlayer(member.getUuid()).isPresent())
                .collect(Collectors.toSet());
    }

    public void removeGuild(Guild guild) {
        this.guilds.remove(guild.getUuid());
    }

    public void removeMember(Guild guild, GuildMember member) {
        guild.removeMember(member);
        this.save();
    }

    public void save() {
        GuildDatabaseManager.setGuilds(this.getGuilds());
        GuildDatabaseManager.save();
    }

}
