package dev.rachamon.rachamonguilds.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;
import java.util.UUID;

/**
 * The type User util.
 */
public final class UserUtil {

    private static final UserStorageService userStorage;

    static {
        userStorage = Sponge.getServiceManager().provide(UserStorageService.class).get();
    }

    /**
     * Gets user.
     *
     * @param uuid the UUID of the player
     * @return An offline User object, or an online Player object. If neither is available, returns empty Optional.
     */
    public static Optional<? extends User> getUser(UUID uuid) {
        Optional<Player> onlinePlayer = Sponge.getServer().getPlayer(uuid);

        return onlinePlayer.isPresent() ? onlinePlayer : userStorage.get(uuid);
    }

    /**
     * Gets user.
     *
     * @param name the name of the player
     * @return the user
     */
    public static Optional<? extends User> getUser(String name) {
        Optional<? extends User> onlinePlayer = Sponge.getServer().getPlayer(name);

        return onlinePlayer.isPresent() ? onlinePlayer : userStorage.get(name);
    }
}