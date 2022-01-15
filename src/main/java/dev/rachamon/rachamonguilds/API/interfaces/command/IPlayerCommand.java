package dev.rachamon.rachamonguilds.api.interfaces.command;

import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

public interface IPlayerCommand extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            throw new CommandException(Text.of("Must be in-game to execute this command."));
        }

        return execute((Player) src, args);
    }

    @Nonnull
    CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException, GuildCommandException;
}
