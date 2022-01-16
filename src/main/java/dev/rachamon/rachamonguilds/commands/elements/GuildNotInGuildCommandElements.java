package dev.rachamon.rachamonguilds.commands.elements;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class GuildNotInGuildCommandElements extends CommandElement {
    public GuildNotInGuildCommandElements(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource source, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        if (source instanceof ConsoleSource) return new ArrayList<>();
        Optional<Guild> guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuild((Player) source);
        Set<UUID> onlinePlayers = Sponge.getServer().getOnlinePlayers().stream().map(Player::getUniqueId).collect(Collectors.toSet());
        return guild.map(value -> RachamonGuilds.getInstance().getGuildManager().getGuildMembers(value).stream().map(member -> RachamonGuildsUtil.getPlayerFromUuid(member.getUniqueId())).filter(Optional::isPresent).map(Optional::get).filter(m -> !onlinePlayers.contains(m.getUniqueId())).map(User::getName).collect(Collectors.toList())).orElse(new ArrayList<>());
    }
}
