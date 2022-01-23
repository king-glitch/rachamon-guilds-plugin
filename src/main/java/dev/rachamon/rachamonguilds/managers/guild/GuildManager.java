package dev.rachamon.rachamonguilds.managers.guild;

import com.flowpowered.math.vector.Vector3d;
import com.google.inject.Inject;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.entities.GuildMember;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.services.GuildService;
import dev.rachamon.rachamonguilds.api.utils.ChatQuestion;
import dev.rachamon.rachamonguilds.api.utils.ChatQuestionAnswer;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import dev.rachamon.rachamonguilds.configs.MainConfig;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The type Guild manager.
 */
public class GuildManager {

    private static final RachamonGuilds plugin = RachamonGuilds.getInstance();

    @Inject
    private GuildService guildService;

    @Inject
    private GuildMessagingManager guildMessagingManager;

    /**
     * Is player guild master boolean.
     *
     * @param player the player
     * @param guild  the guild
     * @return the boolean
     */
    public boolean isPlayerGuildMaster(Player player, Guild guild) {
        return player.getUniqueId().equals(guild.getMaster());
    }

    /**
     * Is player guild master boolean.
     *
     * @param uuid  the uuid
     * @param guild the guild
     * @return the boolean
     */
    public boolean isPlayerGuildMaster(UUID uuid, Guild guild) {
        return uuid.equals(guild.getMaster());
    }

    /**
     * Is player in guild boolean.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean isPlayerInGuild(Player player) {
        Optional<Guild> guild = this.getPlayerGuild(player);
        return guild.isPresent();
    }

    /**
     * Is player in guild boolean.
     *
     * @param guild  the guild
     * @param player the player
     * @return the boolean
     */
    public boolean isPlayerInGuild(Guild guild, Player player) {
        return this.getGuildMembers(guild).stream().anyMatch(member -> member.getId().equals(player.getUniqueId()));
    }

    /**
     * Create.
     *
     * @param source      the source
     * @param name        the name
     * @param displayName the display name
     * @throws GuildCommandException the guild command exception
     */
    public void create(Player source, String name, String displayName) throws GuildCommandException {
        if (this.isPlayerInGuild(source)) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getUserAlreadyInGuild());
        }

        if (this.getGuilds().values().stream().anyMatch(g -> g.getName().equalsIgnoreCase(name))) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getGuildNameAlreadyExists());
        }

        Guild guild = this.guildService.addGuild(source, name, displayName, new Date());
        this.guildMessagingManager.sendGuildInfo(guild, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandCreatedSuccess()));

        if (!plugin.getConfig().getRoot().getGeneralCategorySetting().isSendGlobalMessageOnGuildCreated()) return;
        RachamonGuildsUtil.broadcast(plugin.getConfig().getLanguage().getCommandCategory().getCommandCreatedBroadcast().replaceAll("\\{guild-name}", guild.getName()));
    }

    /**
     * Disband.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void disband(Player source) throws GuildCommandException {

        Guild guild = this.getPlayerGuildOrThrow(source);
        LanguageConfig language = plugin.getConfig().getLanguage();

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw GuildCommandException.notGuildMaster();
        }

        this.disbandGuild(guild, source, language);

    }

    /**
     * Disband.
     *
     * @param guild  the guild
     * @param source the source
     */
    public void disband(Guild guild, CommandSource source) {

        LanguageConfig language = plugin.getConfig().getLanguage();
        this.disbandGuild(guild, source, language);

    }

    private void disbandGuild(Guild guild, CommandSource source, LanguageConfig language) {
        boolean isDelete = this.guildService.removeGuild(guild);

        if (!isDelete) {
            plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(language.getGeneralCategory().getDisbandNotSuccess()));
        } else {
            plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(language.getGeneralCategory().getDisbandIsSuccess()));
            if (!plugin.getConfig().getRoot().getGeneralCategorySetting().isSendGlobalMessageOnGuildDisbanded()) return;
            RachamonGuildsUtil.broadcast(language.getCommandCategory().getCommandDisbandBroadcast().replaceAll("\\{guild-name}", guild.getName()));
        }
    }

    /**
     * Gets player guild.
     *
     * @param source the source
     * @return the player guild
     */
    public Optional<Guild> getPlayerGuild(Player source) {

        for (Guild guild : guildService.getGuildList()) {
            if (guild.hasMember(source.getUniqueId())) continue;
            return Optional.of(guild);
        }

        return Optional.empty();
    }

    /**
     * Gets online guild members as player.
     *
     * @param guild the guild
     * @return the online guild members as player
     */
    public Set<Player> getOnlineGuildMembersAsPlayer(Guild guild) {
        return guild.getMembers().stream().map(member -> Sponge.getServer().getPlayer(member.getUniqueId())).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
    }

    /**
     * Print guild list.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void printGuildList(Player source) throws GuildCommandException {
        PaginationList.Builder builder = PaginationList.builder().title(RachamonGuildsUtil.toText("&6&lGuilds")).padding(RachamonGuildsUtil.toText("&8="));

        List<Text> contents = new ArrayList<>();
        int i = 1;
        for (Guild guild : guildService.getGuildList()) {
            int size = guild.getMembers().size();
            String text = plugin.getConfig().getLanguage().getCommandCategory().getCommandListGuildPrint().replaceAll("\\{index}", String.valueOf(i)).replaceAll("\\{guild-master}", GuildManager.getUser(guild.getMaster()).getName()).replaceAll("\\{guild-size}", String.valueOf(size)).replaceAll("\\{guild-name}", guild.getName());
            contents.add(RachamonGuildsUtil.toText(text));
            i++;
        }

        builder.contents(contents).sendTo(source);
    }

    /**
     * Print guild member list.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void printGuildMemberList(Player source) throws GuildCommandException {
        Guild guild = getPlayerGuildOrThrow(source);

        this.printGuildMemberList(source, guild);
    }

    /**
     * Print guild member list.
     *
     * @param source the source
     * @param guild  the guild
     */
    public void printGuildMemberList(Player source, Guild guild) {
        PaginationList.Builder builder = PaginationList.builder().title(RachamonGuildsUtil.toText("&6&l" + guild.getName())).padding(RachamonGuildsUtil.toText("&8="));

        List<Text> contents = new ArrayList<>();

        Optional<User> master = RachamonGuildsUtil.getUserFromUuid(guild.getMaster());
        if (!master.isPresent()) return;

        contents.add(RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandGuildInfoPrint().replaceAll("\\{guild-name}", guild.getName()).replaceAll("\\{guild-displayname}", guild.getDisplayName()).replaceAll("\\{guild-size}", String.valueOf(guild.getMembers().size())).replaceAll("\\{guild-creation-date}", guild.getCreationDate().toString()).replaceAll("\\{guild-master-status}", master.get().isOnline() ? "&a&lOnline&r" : "&c&lOffline&r").replaceAll("\\{guild-master}", master.get().getName())));

        AtomicInteger i = new AtomicInteger(1);
        this.getGuildMembers(guild).forEach((member) -> {
            Optional<User> user = RachamonGuildsUtil.getUserFromUuid(member.getUuid());
            if (!user.isPresent()) return;
            String name = user.get().isOnline() ? this.isPlayerGuildMaster(user.get().getUniqueId(), guild) ? "&6&l" + user.get().getName() + "&r" : "&a&l" + user.get().getName() + "&r" : "&c&l" + user.get().getName() + "&r";
            String text = plugin.getConfig().getLanguage().getCommandCategory().getCommandInfoMemberListPrint().replaceAll("\\{index}", String.valueOf(i.get())).replaceAll("\\{first-join}", member.getFirstJoin().toString()).replaceAll("\\{name}", name);

            contents.add(RachamonGuildsUtil.toText(text));
            i.addAndGet(1);
        });

        builder.contents(contents).sendTo(source);
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     * @throws GuildCommandException the guild command exception
     */
    public static User getUser(UUID id) throws GuildCommandException {
        UserStorageService userStorageService = Sponge.getServiceManager().provideUnchecked(UserStorageService.class);
        Optional<User> user = userStorageService.get(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getUserNotFound());
        }
    }

    /**
     * Gets player guild or throw.
     *
     * @param source the source
     * @return the player guild or throw
     * @throws GuildCommandException the guild command exception
     */
    public Guild getPlayerGuildOrThrow(Player source) throws GuildCommandException {
        return this.getPlayerGuild(source).orElseThrow(GuildCommandException::notInGuild);
    }

    /**
     * Gets guild.
     *
     * @param name the name
     * @return the guild
     * @throws GuildCommandException the guild command exception
     */
    public Guild getGuild(String name) throws GuildCommandException {
        Optional<Guild> guild = guildService.getGuild(name);
        if (guild.isPresent()) {
            return guild.get();
        } else {
            throw new GuildCommandException((plugin.getConfig().getLanguage().getGeneralCategory().getGuildNotFound()));
        }
    }

    /**
     * Gets guilds.
     *
     * @return the guilds
     */
    public Map<UUID, Guild> getGuilds() {
        return guildService.getGuilds();
    }

    /**
     * Gets guild members.
     *
     * @param guild the guild
     * @return the guild members
     */
    public Set<GuildMember> getGuildMembers(Guild guild) {
        return guild.getMembers();
    }

    /**
     * Transfer guild master.
     *
     * @param source the source
     * @param name   the name
     * @throws GuildCommandException the guild command exception
     */
    public void transferGuildMaster(Player source, String name) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        Player target = RachamonGuildsUtil.getPlayerFromUsernameOrThrow(name);
        boolean isInGuild = this.isPlayerInGuild(guild, target);

        if (!isInGuild) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getTargetIsNotInGuild().replaceAll("\\{target}", target.getName()));
        }

        if (!this.isPlayerGuildMaster(source, guild)) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        this.guildService.setGuildMaster(guild, target.getUniqueId());
        plugin.getGuildMessagingManager().sendGuildInfo(guild, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getGeneralCategory().getNewGuildMaster().replaceAll("\\{new}", target.getName()).replaceAll("\\{old}", source.getName())));
        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandTransferSuccess().replaceAll("\\{target}", target.getName())));

    }

    /**
     * Leave guild.
     *
     * @param player the player
     * @throws GuildCommandException the guild command exception
     */
    public void leaveGuild(Player player) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(player);

        if (guild.getMaster().equals(player.getUniqueId())) {
            plugin.getGuildMessagingManager().response(player, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandLeaveGuildMaster()));
            return;
        }

        this.removeGuildMember(guild, player);

        plugin.getGuildMessagingManager().response(player, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandLeaveGuildSuccess()));
        plugin.getGuildMessagingManager().sendLeaveGuildMessage(guild, player.getName());

    }

    /**
     * Kick.
     *
     * @param source the source
     * @param name   the name
     * @throws GuildCommandException the guild command exception
     */
    public void kick(Player source, String name) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        Player target = RachamonGuildsUtil.getPlayerFromUsernameOrThrow(name);

        boolean isInGuild = this.isPlayerInGuild(guild, target);

        if (!isInGuild) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getTargetIsNotInGuild().replaceAll("\\{target}", target.getName()));
        }

        if (target.getUniqueId().equals(source.getUniqueId())) {
            plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandKickSelf()));
            return;
        }

        this.removeGuildMember(guild, target);

        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandKickSuccess().replaceAll("\\{target}", target.getName())));
        plugin.getGuildMessagingManager().sendLeaveGuildMessage(guild, target.getName());
    }

    /**
     * Remove guild member.
     *
     * @param guild  the guild
     * @param target the target
     */
    public void removeGuildMember(Guild guild, Player target) {
        Optional<GuildMember> member = this.guildService.getGuildMember(guild, target.getUniqueId());
        if (!member.isPresent()) {
            RachamonGuilds.getInstance().getLogger().debug("No Guild Member Data" + member);
            return;
        }

        this.guildService.removeMember(guild, member.get().getUuid());
    }

    /**
     * Invite.
     *
     * @param source the source
     * @param name   the name
     * @throws GuildCommandException the guild command exception
     */
    public void invite(Player source, String name) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        Player player = RachamonGuildsUtil.getPlayerFromUsernameOrThrow(name);

        if (guild.getMembersUuid().contains(player.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getMemberAlreadyInGuild());
        }

        if (this.getPlayerGuild(player).isPresent()) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getMemberAlreadyInAnotherGuild());
        }

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        this.invite(guild, source, player);
    }

    /**
     * Invite.
     *
     * @param guild  the guild
     * @param master the master
     * @param source the source
     */
    public void invite(Guild guild, Player master, Player source) {
        LanguageConfig language = plugin.getConfig().getLanguage();
        ChatQuestion question = ChatQuestion.of(RachamonGuildsUtil.toText(language.getGeneralCategory().getInvitePlayerToGuild().replaceAll("\\{guild-master}", master.getName()).replaceAll("\\{guild-name}", guild.getName()))).addAnswer(ChatQuestionAnswer.of(RachamonGuildsUtil.toText(language.getQuestionCategory().getAcceptButton()), target -> {
            plugin.getGuildMessagingManager().response(target, RachamonGuildsUtil.toText(language.getGeneralCategory().getInviteAccepted().replaceAll("\\{target}", source.getName())));
            plugin.getGuildMessagingManager().response(target, RachamonGuildsUtil.toText(language.getGeneralCategory().getInviteAcceptedTarget().replaceAll("\\{guild-name}", guild.getName())));
            this.guildService.addMember(guild, new GuildMember(source.getUniqueId(), new Date(), new Date()));
            plugin.getGuildMessagingManager().sendJoinGuildMessage(guild, source.getName());
        })).addAnswer(ChatQuestionAnswer.of(RachamonGuildsUtil.toText(language.getQuestionCategory().getDeclinedButton()), target -> {
            plugin.getGuildMessagingManager().response(target, RachamonGuildsUtil.toText(language.getGeneralCategory().getInviteDeclined().replaceAll("\\{target}", source.getName())));
            plugin.getGuildMessagingManager().response(target, RachamonGuildsUtil.toText(language.getGeneralCategory().getInviteDeclinedTarget().replaceAll("\\{guild-name}", guild.getName())));
        })).build();

        question.setAlreadyResponse(RachamonGuildsUtil.toText(language.getQuestionCategory().getAlreadyResponded()));
        question.setClickToAnswer(RachamonGuildsUtil.toText(language.getQuestionCategory().getClickToAnswer()));
        question.setClickToView(RachamonGuildsUtil.toText(language.getQuestionCategory().getClickToView()));
        question.setMustBePlayer(RachamonGuildsUtil.toText(language.getQuestionCategory().getMustBePlayer()));

        question.pollChat(source);

        plugin.getGuildMessagingManager().sendGuildInfo(guild, RachamonGuildsUtil.toText(language.getGeneralCategory().getInvitedPlayerToGuild().replaceAll("\\{target}", source.getName())));
    }

    /**
     * Add member to guild.
     *
     * @param guild  the guild
     * @param source the source
     */
    public void addMemberToGuild(Guild guild, User source) {
        this.guildService.addMember(guild, new GuildMember(source.getUniqueId(), new Date(), new Date()));
        plugin.getGuildMessagingManager().sendJoinGuildMessage(guild, source.getName());
    }

    /**
     * Remove member from guild.
     *
     * @param guild  the guild
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void removeMemberFromGuild(Guild guild, User source) throws GuildCommandException {

        if (this.isPlayerGuildMaster(source.getUniqueId(), guild)) {
            throw new GuildCommandException("You can't remove guild master, please disband a guild");
        }

        this.guildService.removeMember(guild, source.getUniqueId());
        plugin.getGuildMessagingManager().sendLeaveGuildMessage(guild, source.getName());
    }

    /**
     * Sets home.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void setHome(Player source) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        this.guildService.setHome(guild, source.getWorld(), source.getLocation(), source.getHeadRotation());
        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandSetHomeSuccess()));

    }

    /**
     * Sets name.
     *
     * @param source the source
     * @param name   the name
     * @throws GuildCommandException the guild command exception
     */
    public void setName(Player source, String name) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        MainConfig config = plugin.getConfig().getRoot();
        LanguageConfig language = plugin.getConfig().getLanguage();

        if (!name.matches(config.getGuildCategorySetting().getValidNameRegex())) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildName());
        }

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        int minNameLength = config.getGuildCategorySetting().getMinGuildNameLength();
        int maxNameLength = config.getGuildCategorySetting().getMaxGuildNameLength();

        if (name.length() < minNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooShort());
        }

        if (name.length() > maxNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedNameTooLong());
        }

        this.guildService.setName(guild, name);
        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandNameSuccess().replaceAll("\\{name}", name)));
    }

    /**
     * Sets prefix.
     *
     * @param source the source
     * @param name   the name
     * @throws GuildCommandException the guild command exception
     */
    public void setPrefix(Player source, String name) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);

        MainConfig config = plugin.getConfig().getRoot();
        LanguageConfig language = plugin.getConfig().getLanguage();

        if (!name.matches("[A-Za-z&0-9]*")) {
            throw new GuildCommandException(language.getCommandCategory().getCommandInvalidGuildDisplayName());
        }

        boolean isGuildDisplayNameIncludeColor = config.getGuildCategorySetting().isGuildDisplayNameIncludeColor();
        int minDisplayNameLength = config.getGuildCategorySetting().getMinGuildDisplayNameLength();
        int maxDisplayNameLength = config.getGuildCategorySetting().getMaxGuildDisplayNameLength();

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        RachamonGuildsUtil.guildDisplayNameCheck(name, language, isGuildDisplayNameIncludeColor, minDisplayNameLength, maxDisplayNameLength);

        this.guildService.setDisplayName(guild, name);

        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandPrefixSuccess().replaceAll("\\{prefix}", name)));
    }

    /**
     * Home.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void home(Player source) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        String[] location = guild.getHome().split(":");

        if (location.length != 5) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getCommandCategory().getCommandHomeNotFound());
        }

        Optional<World> world = Sponge.getServer().getWorld(location[0]);
        if (!world.isPresent()) {
            throw GuildCommandException.somethingWentWrong();
        }
        source.setLocation(new Location<>(world.get(), new Vector3d(Double.parseDouble(location[1]), Double.parseDouble(location[2]), Double.parseDouble(location[3]))));
        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandHomeSuccess()));

    }

    /**
     * Guild chat.
     *
     * @param source  the source
     * @param message the message
     * @throws GuildCommandException the guild command exception
     */
    public void guildChat(Player source, String message) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);

        RachamonGuilds.getInstance().getGuildMessagingManager().sendGuildInfo(guild, RachamonGuildsUtil.toText(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategory().getGuildChatFormat().replaceAll("\\{member}", source.getName()) + message));

        Sponge.getServer().getOnlinePlayers().stream().filter(player -> player.hasPermission("rachamonguilds.chat.spy") && guild.hasMember(player.getUniqueId())).forEach(player -> player.sendMessage(RachamonGuildsUtil.toText(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategory().getGuildChatSpyFormat().replaceAll("\\{member}", source.getName()).replaceAll("\\{guild-name}", guild.getName()) + message)));
    }

    /**
     * Sets motd.
     *
     * @param source  the source
     * @param message the message
     * @throws GuildCommandException the guild command exception
     */
    public void setMotd(Player source, String message) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);

        if (!guild.getMaster().equals(source.getUniqueId())) {
            throw new GuildCommandException(plugin.getConfig().getLanguage().getGeneralCategory().getNotGuildMaster());
        }

        this.guildService.setMotd(guild, message);
        plugin.getGuildMessagingManager().response(source, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandSetMotdSuccess()));
        plugin.getGuildMessagingManager().sendGuildInfo(guild, RachamonGuildsUtil.toText(plugin.getConfig().getLanguage().getCommandCategory().getCommandSetMotdSuccessOther()));

    }

    /**
     * Show motd.
     *
     * @param source the source
     * @throws GuildCommandException the guild command exception
     */
    public void showMotd(Player source) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);

        plugin.getGuildMessagingManager().sendGuildInfo(guild, RachamonGuildsUtil.toText(guild.getMotd()));

    }

    /**
     * Sets last join.
     *
     * @param source   the source
     * @param lastJoin the last join
     * @throws GuildCommandException the guild command exception
     */
    public void setLastJoin(Player source, Date lastJoin) throws GuildCommandException {
        Guild guild = this.getPlayerGuildOrThrow(source);
        this.guildService.setMemberLastJoin(guild, source.getUniqueId(), lastJoin);
    }

}
