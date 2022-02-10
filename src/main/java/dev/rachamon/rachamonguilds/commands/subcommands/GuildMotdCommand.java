package dev.rachamon.rachamonguilds.commands.subcommands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.api.sponge.implement.command.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;


/**
 * The type Guild motd command.
 */
@ICommandAliases({"motd"})
@ICommandPermission("rachamonguilds.command.guild.motd")
@ICommandDescription("Guild message of the day")
public class GuildMotdCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        RachamonGuilds.getInstance().getGuildManager().showMotd(source);
        return CommandResult.success();
    }
}
