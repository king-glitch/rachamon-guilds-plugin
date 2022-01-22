package dev.rachamon.rachamonguilds.commands.subcommands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.elements.GuildMemberCommandElement;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Guild kick command.
 */
@ICommandAliases({"kick"})
@ICommandPermission("rachamonguilds.command.guild.kick")
@ICommandDescription("Kick member from guild.")
public class GuildKickCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new GuildMemberCommandElement(Text.of("member"))};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> member = args.getOne("member");
        if (!member.isPresent()) return CommandResult.empty();
        RachamonGuilds.getInstance().getGuildManager().kick(source, member.get());
        return CommandResult.success();
    }
}
