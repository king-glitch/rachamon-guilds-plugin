package dev.rachamon.rachamonguilds.commands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.subcommands.*;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandChildren({GuildCreateCommand.class, GuildListCommand.class, GuildInfoCommand.class, GuildDisbandCommand.class, GuildTransferMasterCommand.class, GuildLeaveCommand.class, GuildKickCommand.class, GuildHomeCommand.class, GuildSetHomeCommand.class, GuildInviteCommand.class, GuildPrefixCommand.class, GuildNameCommand.class})
@ICommandAliases("guilds")
@ICommandHelpText(title = "Main Guild Help", command = "help")
@ICommandPermission("rachamonguilds.command.base")
public class GuildCommand implements IPlayerCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException {
        Guild guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuildOrThrow(source);
        RachamonGuilds.getInstance().getGuildManager().printGuildMemberList(source, guild);
        return CommandResult.success();
    }
}
