package dev.rachamon.rachamonguilds.utils;

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

public class RachamonGuildsUtil {
    public static Text toText(String string) {
        return TextSerializers.FORMATTING_CODE.deserialize(string);
    }

    public static void broadcast(String string) {
        for (Player player : Sponge.getServer().getOnlinePlayers()) {
            RachamonGuilds.getInstance().getGuildMessagingManager().response(player, RachamonGuildsUtil.toText(string));
        }
    }

    public static Optional<Player> getPlayerFromUuid(UUID uuid) {
        return Sponge.getServer().getPlayer(uuid);
    }

    public static Optional<User> getUserFromUuid(UUID uuid) {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        return userStorage.flatMap(storage -> storage.get(uuid));
    }

    public static Player getPlayerFromUuidOrThrow(UUID uuid) throws GuildCommandException {
        return Sponge.getServer().getPlayer(uuid).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    public static User getUserFromNameOrThrow(String name) throws GuildCommandException {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        return userStorage.flatMap(storage -> storage.get(name)).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    public static Player getPlayerFromUsernameOrThrow(String string) throws GuildCommandException {
        return Sponge.getServer().getPlayer(string).orElseThrow(GuildCommandException::notOnlineOrExists);
    }

    public static Optional<Player> getPlayerFromUsername(String string) {
        return Sponge.getServer().getPlayer(string);
    }

    public static void guildDisplayNameCheck(String displayName, LanguageConfig language, boolean isGuildDisplayNameIncludeColor, int minDisplayNameLength, int maxDisplayNameLength) throws GuildCommandException {
        int displayNameLength;
        if (!isGuildDisplayNameIncludeColor) {
            displayNameLength = displayName.length() - (displayName.replace("&", "").length() * 2);
        } else {
            displayNameLength = displayName.length();
        }

        if (displayNameLength < minDisplayNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedDisplayNameTooShort());
        }

        if (displayNameLength > maxDisplayNameLength) {
            throw new GuildCommandException(language.getCommandCategory().getCommandCreatedDisplayNameTooLong());
        }
    }
}
