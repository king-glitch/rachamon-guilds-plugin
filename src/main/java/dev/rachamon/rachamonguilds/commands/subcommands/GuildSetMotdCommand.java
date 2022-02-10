package dev.rachamon.rachamonguilds.commands.subcommands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.api.sponge.implement.command.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Guild set motd command.
 */
@ICommandAliases({"setmotd"})
@ICommandPermission("rachamonguilds.command.guild.set-motd")
@ICommandDescription("Set guild message of the day")
public class GuildSetMotdCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("message"))
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> message = args.getOne("message");
        if (!message.isPresent()) return CommandResult.empty();
        RachamonGuilds.getInstance().getGuildManager().setMotd(source, message.get());
        return CommandResult.success();
    }
}
