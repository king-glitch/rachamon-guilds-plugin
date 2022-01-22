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
 * The type Guild list command.
 */
@ICommandAliases({"list", "ls"})
@ICommandPermission("rachamonguilds.command.guild.list")
@ICommandDescription("Guild Lists")
public class GuildListCommand implements IPlayerCommand, IParameterizedCommand {

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {

        // create guild
        RachamonGuilds.getInstance().getGuildManager().printGuildList(source);

        return CommandResult.success();
    }
}
