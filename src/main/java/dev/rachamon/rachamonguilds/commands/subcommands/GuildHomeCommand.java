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
 * The type Guild home command.
 */
@ICommandAliases({"home", "house"})
@ICommandPermission("rachamonguilds.command.guild.home")
@ICommandDescription("Teleport to guild house")
public class GuildHomeCommand implements IPlayerCommand, IParameterizedCommand {

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {

        RachamonGuilds.getInstance().getGuildManager().home(source);

        return CommandResult.success();
    }
}
