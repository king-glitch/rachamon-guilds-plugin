package dev.rachamon.rachamonguilds.api.interfaces.command;

import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

public interface IPlayerCommand extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws GuildCommandException {
        if (!(src instanceof Player)) {
            throw new GuildCommandException("Must be in-game to execute this command.");
        }

        return execute((Player) src, args);
    }

    @Nonnull
    CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException;
}
