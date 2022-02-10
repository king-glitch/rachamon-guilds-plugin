package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.api.sponge.implement.command.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;

import javax.annotation.Nonnull;

/**
 * The type Guild admin command.
 */
@ICommandAliases({"admin"})
@ICommandPermission("rachamonguilds.command.admin.base")
@ICommandDescription("guild admin base")
@ICommandHelpText(title = "Main Guild Help", command = "help")
@ICommandChildren({GuildAdminMemberCommand.class, GuildAdminCreateCommand.class, GuildAdminReloadCommand.class, GuildAdminDisbandCommand.class})
public class GuildAdminCommand implements ICommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {


        RachamonGuilds.getInstance().getLogger().success("Guild admin command");

        return CommandResult.success();
    }
}
