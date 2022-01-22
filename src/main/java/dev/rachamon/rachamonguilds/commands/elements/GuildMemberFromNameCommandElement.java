package dev.rachamon.rachamonguilds.commands.elements;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GuildMemberFromNameCommandElement extends CommandElement {
    public GuildMemberFromNameCommandElement(@Nullable Text key) {
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

        if (args.size() < 4) return new ArrayList<String>();
        String name = args.get(2);
        String type = args.get(3);

        RachamonGuilds.getInstance().getLogger().info(name + " " + type);

        if (type.equalsIgnoreCase("add")) {
            try {
                Guild guild = RachamonGuilds.getInstance().getGuildManager().getGuild(name);
                List<UUID> onlinePlayers = Sponge.getServer().getOnlinePlayers().stream().map(Player::getUniqueId).collect(Collectors.toList());
                return onlinePlayers.stream().filter(o -> !guild.getMembersUuid().contains(o)).map(RachamonGuildsUtil::getPlayerFromUuid).filter(Optional::isPresent).map(Optional::get).map(User::getName).collect(Collectors.toList());
            } catch (Exception ignored) {
            }
        } else if (type.equalsIgnoreCase("remove")) {

            try {
                Optional<Guild> guild = Optional.ofNullable(RachamonGuilds.getInstance().getGuildManager().getGuild(name));
                if (!guild.isPresent()) return new ArrayList<String>();
                return guild.map(value -> RachamonGuilds.getInstance().getGuildManager().getGuildMembers(value).stream().map(member -> RachamonGuildsUtil.getPlayerFromUuid(member.getUniqueId())).filter(Optional::isPresent).map(Optional::get).map(User::getName).collect(Collectors.toList())).orElse(new ArrayList<>());

            } catch (GuildCommandException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<String>();
    }
}
