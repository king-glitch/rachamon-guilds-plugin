package dev.rachamon.rachamonguilds.commands.subcommands.admin;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.elements.GuildListCommandElement;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Guild admin disband command.
 */
@ICommandAliases({"create"})
@ICommandPermission("rachamonguilds.command.admin.create")
@ICommandDescription("Create a guild.")
public class GuildAdminCreateCommand implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws GuildCommandException {
        Optional<String> name = args.getOne("name");
        Optional<Player> master = args.getOne("master");

        if (!name.isPresent() || !master.isPresent()) return CommandResult.empty();

        RachamonGuilds.getInstance().getGuildManager().create(master.get(), name.get(), name.get());

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                new GuildListCommandElement(Text.of("name")),
                GenericArguments.player(Text.of("master"))
        };
    }
}
