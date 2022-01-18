package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandAliases({"admin reload"})
@ICommandPermission("rachamonguilds.command.admin.reload")
@ICommandDescription("Disband a guild.")
public class GuildAdminReloadCommand implements IPlayerCommand, IAdminCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        try {
            RachamonGuilds.getInstance().getPluginManager().reload();
        } catch (Exception e) {
            return CommandResult.empty();
        }
        return CommandResult.success();
    }
}
