package dev.rachamon.rachamonguilds.commands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.subcommands.*;
import dev.rachamon.rachamonguilds.commands.subcommands.admin.GuildAdminCommand;
import dev.rachamon.rachamonguilds.commands.subcommands.admin.GuildAdminReloadCommand;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandChildren({
        GuildCreateCommand.class,
        GuildAdminReloadCommand.class,
        GuildListCommand.class,
        GuildChatCommand.class,
        GuildInfoCommand.class,
        GuildDisbandCommand.class,
        GuildTransferMasterCommand.class,
        GuildLeaveCommand.class,
        GuildKickCommand.class,
        GuildHomeCommand.class,
        GuildSetHomeCommand.class,
        GuildInviteCommand.class,
        GuildPrefixCommand.class,
        GuildNameCommand.class,
        GuildMotdCommand.class,
        GuildSetMotdCommand.class,
        GuildAdminCommand.class
})
@ICommandAliases({"guild", "guilds", "g"})
@ICommandHelpText(title = "Main Guild Help", command = "help")
@ICommandPermission("rachamonguilds.command.base")
public class GuildCommand implements IPlayerCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        Guild guild = RachamonGuilds.getInstance().getGuildManager().getPlayerGuildOrThrow(source);
        RachamonGuilds.getInstance().getGuildManager().printGuildMemberList(source, guild);
        return CommandResult.success();
    }
}
