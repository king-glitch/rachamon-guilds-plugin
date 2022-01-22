package dev.rachamon.rachamonguilds.api.interfaces.command;

import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import javax.annotation.Nonnull;

public interface ICommand extends CommandExecutor {
    @Nonnull
    CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException;
}
