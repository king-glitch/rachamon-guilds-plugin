package dev.rachamon.rachamonguilds.managers.guild;

import com.google.inject.Inject;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.entities.GuildMember;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.services.GuildService;
import dev.rachamon.rachamonguilds.database.GuildDatabase;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GuildManager {

    private static final RachamonGuilds plugin = RachamonGuilds.getInstance();

    @Inject
    private GuildService guildService;

    @Inject
    private GuildMessagingManager guildMessagingFacade;

    public boolean isPlayerGuildMaster(Player player, Guild guild) {
        return player.getUniqueId().equals(guild.getMaster());
    }

    public boolean isPlayerInGuild(Player player) {
        Optional<Guild> guild = this.getPlayerGuild(player);
        return guild.isPresent();
    }

    public void createGuild(Player source, String name, String displayName) throws GuildCommandException {
        if (this.isPlayerInGuild(source)) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategorySetting().getUserAlreadyInGuild());
        }

        Guild guild = this.guildService.createGuild(source, name, displayName, new Date());
        this.guildMessagingFacade.sendGuildInfo(guild, guild.getName() + " has been created.");

        if (!RachamonGuilds.getInstance().getConfig().getRoot().getGeneralCategorySetting().isSendGlobalMessageOnGuildCreated())
            return;
        RachamonGuildsUtil.broadcast(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategorySetting().getPrefix() + plugin.getConfig().getLanguage().getCommandCategorySetting().getCommandCreatedBroadcast().replaceAll("\\{guild-name}", guild.getName()));

    }

    public Optional<Guild> getPlayerGuild(Player source) {

        for (Guild guild : guildService.getGuildList()) {
            if (!guild.hasMember(source.getUniqueId())) continue;
            return Optional.of(guild);
        }

        return Optional.empty();
    }

    public Set<GuildMember> getOnlineGuildMembers(Guild guild) {
        return guild.getMembers().stream().filter(member -> Sponge.getServer().getPlayer(member.getUuid()).isPresent()).collect(Collectors.toSet());
    }

    public Set<Player> getOnlineGuildMembersAsPlayer(Guild guild) {
        return guild.getMembers().stream().map(member -> Sponge.getServer().getPlayer(member.getUniqueId())).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
    }

    public void printGuildList(Player source) throws GuildCommandException {
        PaginationList.Builder builder = PaginationList.builder().title(RachamonGuildsUtil.toText("&6&lGuilds")).padding(RachamonGuildsUtil.toText("&8="));

        List<Text> contents = new ArrayList<>();
        int i = 1;
        for (Guild guild : guildService.getGuildList()) {
            int size = guild.getMembers().size();
            String text = plugin
                    .getConfig()
                    .getLanguage()
                    .getCommandCategorySetting()
                    .getCommandListGuildPrint().replaceAll("\\{index}", String.valueOf(i)).replaceAll("\\{guild-master}", GuildManager
                            .getUser(guild.getMaster())
                            .getName())
                    .replaceAll("\\{guild-size}", String.valueOf(size))
                    .replaceAll("\\{guild-name}", guild.getName());
            contents.add(RachamonGuildsUtil.toText(text));
            i++;
        }

        builder.contents(contents).sendTo(source);
    }

    public void printGuildMemberList(Player source) throws GuildCommandException {
        Guild guild = getPlayerGuildOrThrow(source);

        this.printGuildMemberList(source, guild);
    }

    public void printGuildMemberList(Player source, Guild guild) throws GuildCommandException {
        PaginationList.Builder builder = PaginationList.builder().title(RachamonGuildsUtil.toText("&6&l" + guild.getName())).padding(RachamonGuildsUtil.toText("&8="));

        List<Text> contents = new ArrayList<>();

        contents.add(RachamonGuildsUtil
                .toText(plugin
                        .getConfig()
                        .getLanguage()
                        .getCommandCategorySetting()
                        .getCommandGuildInfoPrint().replaceAll("\\{guild-name}", guild.getName())
                        .replaceAll("\\{guild-displayname}", guild.getDisplayName())
                        .replaceAll("\\{guild-size}", String.valueOf(guild.getMembers().size()))
                        .replaceAll("\\{guild-creation-date}", guild.getCreationDate().toString())
                        .replaceAll("\\{guild-master-status}", RachamonGuildsUtil.getPlayerFromUuid(guild.getMaster()).isPresent() ? "&a&lOnline&r" : "&c&lOffline&r")
                        .replaceAll("\\{guild-master}", RachamonGuildsUtil.getPlayerFromUuid(guild.getMaster()).get().getName())));

        AtomicInteger i = new AtomicInteger(1);
        this.getOnlineGuildMembers(guild).forEach((member) -> {
            Optional<Player> player = RachamonGuildsUtil.getPlayerFromUuid(member.getUuid());
            String text = plugin
                    .getConfig()
                    .getLanguage()
                    .getCommandCategorySetting()
                    .getCommandInfoMemberListPrint()
                    .replaceAll("\\{index}", String.valueOf(i.get()))
                    .replaceAll("\\{first-join}", member.getFirstJoin().toString())
                    .replaceAll("\\{name}", player.map(value -> this.isPlayerGuildMaster(value, guild) ? "&6&l" + value.getName() + "&r" : "&a" + value.getName()).orElseGet(() -> "&c" + player.get().getName()));
            ;
            contents.add(RachamonGuildsUtil.toText(text));
            i.addAndGet(1);
        });

        builder.contents(contents).sendTo(source);
    }

    public static User getUser(UUID id) throws GuildCommandException {
        UserStorageService userStorageService = Sponge.getServiceManager().provideUnchecked(UserStorageService.class);
        Optional<User> user = userStorageService.get(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new GuildCommandException(RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getGeneralCategorySetting().getUserNotFound()));
        }
    }

    public Guild getPlayerGuildOrThrow(Player source) throws GuildCommandException {
        GuildDatabase guildData = source.get(GuildDatabase.class).orElseThrow(GuildCommandException::notInGuild);
        return guildData.getGuild().orElseThrow(GuildCommandException::notInGuild);
    }

    public Guild getGuild(String name) throws GuildCommandException {
        Optional<Guild> guild = guildService.getGuild(name);
        if (guild.isPresent()) {
            return guild.get();
        } else {
            throw new GuildCommandException(RachamonGuildsUtil.toText((RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategorySetting().getGuildNotFound())));
        }
    }

    public Map<UUID, Guild> getGuilds() throws GuildCommandException {
        return guildService.getGuilds();
    }

}
