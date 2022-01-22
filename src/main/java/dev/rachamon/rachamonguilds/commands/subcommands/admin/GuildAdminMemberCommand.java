package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.elements.GuildListCommandElement;
import dev.rachamon.rachamonguilds.commands.elements.GuildManagementCommandElement;
import dev.rachamon.rachamonguilds.commands.elements.GuildMemberFromNameCommandElement;
import dev.rachamon.rachamonguilds.utils.RachamonGuildsUtil;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandAliases({"member"})
@ICommandPermission("rachamonguilds.command.admin.member")
@ICommandDescription("manage a guild member.")
public class GuildAdminMemberCommand implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> _guild = args.getOne("guild");
        Optional<String> type = args.getOne("type");
        Optional<String> member = args.getOne("member");

        if (!_guild.isPresent() || !type.isPresent() || !member.isPresent()) return CommandResult.empty();

        User player = RachamonGuildsUtil.getUserFromNameOrThrow(member.get());
        Guild guild = RachamonGuilds.getInstance().getGuildManager().getGuild(_guild.get());

        if (type.get().equalsIgnoreCase("add")) {
            RachamonGuilds.getInstance().getGuildManager().addMemberToGuild(guild, player);
        } else if (type.get().equalsIgnoreCase("remove")) {
            RachamonGuilds.getInstance().getGuildManager().removeMemberFromGuild(guild, player);
        } else {
            throw new GuildCommandException("wrong argument type");
        }

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new GuildListCommandElement(Text.of("guild")), new GuildManagementCommandElement(Text.of("type")), new GuildMemberFromNameCommandElement(Text.of("member")),};
    }
}
