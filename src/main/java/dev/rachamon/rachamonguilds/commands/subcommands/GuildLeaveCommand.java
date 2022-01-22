package dev.rachamon.rachamonguilds.commands.subcommands;


import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

/**
 * The type Guild leave command.
 */
@ICommandAliases({"leave", "exit"})
@ICommandPermission("rachamonguilds.command.guild.leave")
@ICommandDescription("Leave a guild")
public class GuildLeaveCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        RachamonGuilds.getInstance().getGuildManager().leaveGuild(source);
        return CommandResult.success();
    }
}
