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
 * The type Guild prefix command.
 */
@ICommandAliases({"prefix", "displayname"})
@ICommandPermission("rachamonguilds.command.guild.prefix")
@ICommandDescription("Set guild prefix")
public class GuildPrefixCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{GenericArguments.string(Text.of("prefix")),};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> prefix = args.getOne("prefix");
        if (!prefix.isPresent()) return CommandResult.empty();

        RachamonGuilds.getInstance().getGuildManager().setPrefix(source, prefix.get());

        return CommandResult.success();
    }
}
