package dev.rachamon.rachamonguilds.utils;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.configs.LanguageConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Optional;
import java.util.UUID;

/**
 * The type Rachamon guilds util.
 */
public class RachamonGuildsUtil {

    /**
     * Broadcast.
     *
     * @param string the string
     */
    public static void broadcast(String string) {
        for (Player player : Sponge.getServer().getOnlinePlayers()) {
            RachamonGuilds.getInstance().getGuildMessagingManager().response(player, TextUtil.toText(string));
        }
    }

    /**
     * Gets player from uuid.
     *
     * @param uuid the uuid
     * @return the player from uuid
     */
    public static Optional<Player> getPlayerFromUuid(UUID uuid) {
        return Sponge.getServer().getPlayer(uuid);
    }

    /**
     * Gets user from uuid.
     *
     * @param uuid the uuid
     * @return the user from uuid
     */
    public static Optional<User> getUserFromUuid(UUID uuid) {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        return userStorage.flatMap(storage -> storage.get(uuid));
    }

    /**
     * Gets player from uuid or throw.
     *
     * @param uuid the uuid
     * @return the player from uuid or throw
     * @throws GuildCommandException the guild command exception
     */
    public static Player getPlayerFromUuidOrThrow(UUID uuid) throws GuildCommandException {
        return Sponge.getServer().getPlayer(uuid).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    /**
     * Gets user from name or throw.
     *
     * @param name the name
     * @return the user from name or throw
     * @throws GuildCommandException the guild command exception
     */
    public static User getUserFromNameOrThrow(String name) throws GuildCommandException {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        return userStorage.flatMap(storage -> storage.get(name)).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    /**
     * Gets player from username or throw.
     *
     * @param string the string
     * @return the player from username or throw
     * @throws GuildCommandException the guild command exception
     */
    public static Player getPlayerFromUsernameOrThrow(String string) throws GuildCommandException {
        return Sponge.getServer().getPlayer(string).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    /**
     * Gets player from username.
     *
     * @param string the string
     * @return the player from username
     */
    public static Optional<Player> getPlayerFromUsername(String string) {
        return Sponge.getServer().getPlayer(string);
    }

    /**
     * Guild display name check.
     *
     * @param displayName                    the display name
     * @param language                       the language
     * @param isGuildDisplayNameIncludeColor the is guild display name include color
     * @param minDisplayNameLength           the min display name length
     * @param maxDisplayNameLength           the max display name length
     * @throws GuildCommandException the guild command exception
     */
    public static void guildDisplayNameCheck(String displayName, LanguageConfig language, boolean isGuildDisplayNameIncludeColor, int minDisplayNameLength, int maxDisplayNameLength) throws GuildCommandException {
        int displayNameLength;
        if (!isGuildDisplayNameIncludeColor) {
            displayNameLength = (displayName.length() - ((displayName.length() - displayName.replace("&", "").length()) * 2));
        } else {
            displayNameLength = displayName.length();
        }

        if (minDisplayNameLength > displayNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedDisplayNameTooShort());
        }

        if (maxDisplayNameLength < displayNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedDisplayNameTooLong());
        }
    }
}
