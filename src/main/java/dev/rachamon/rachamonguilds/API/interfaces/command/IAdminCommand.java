package dev.rachamon.rachamonguilds.api.interfaces.command;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

public interface IAdminCommand extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {
        if (!source.hasPermission("rachamonguilds.command.admin.base")) {
            throw new GuildCommandException(RachamonGuilds.getInstance().getConfig().getLanguage().getGeneralCategory().getNoPermission());
        }

        return execute(source, args);
    }

    @Nonnull
    CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException, GuildCommandException;
}
