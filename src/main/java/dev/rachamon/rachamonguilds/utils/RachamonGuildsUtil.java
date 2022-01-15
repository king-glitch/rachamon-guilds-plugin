package dev.rachamon.rachamonguilds.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
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
            player.sendMessage(RachamonGuildsUtil.toText(string));
        }
    }

    public static Optional<Player> getPlayerFromUuid(UUID uuid) {
        return Sponge.getServer().getPlayer(uuid);
    }
}
