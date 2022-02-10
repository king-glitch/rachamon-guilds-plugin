package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonguilds.commands.elements.GuildListCommandElement;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Guild admin disband command.
 */
@ICommandAliases({"disband"})
@ICommandPermission("rachamonguilds.command.admin.disband")
@ICommandDescription("Disband a guild.")
public class GuildAdminDisbandCommand implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> _guild = args.getOne("guild");

        if (!_guild.isPresent()) return CommandResult.empty();

        Guild guild = RachamonGuilds.getInstance().getGuildManager().getGuild(_guild.get());
        RachamonGuilds.getInstance().getGuildManager().disband(guild, source);

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                new GuildListCommandElement(Text.of("guild"))
        };
    }
}
