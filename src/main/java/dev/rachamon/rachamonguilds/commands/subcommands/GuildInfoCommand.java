package dev.rachamon.rachamonguilds.commands.subcommands;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import dev.rachamon.rachamonguilds.api.interfaces.command.*;
import dev.rachamon.rachamonguilds.commands.elements.GuildCommandElement;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandAliases({"info", "if", "data"})
@ICommandPermission("rachamonguilds.command.guild.info")
@ICommandDescription("Guild Info")
public class GuildInfoCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new GuildCommandElement(Text.of("guild"))};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws GuildCommandException {

        Optional<String> guildArg = args.<String>getOne("guild");
        if (!guildArg.isPresent()) {
            RachamonGuilds.getInstance().getGuildManager().printGuildMemberList(source);
        } else {
            int minGuildNameLength = RachamonGuilds.getInstance().getConfig().getRoot().getGuildCategorySetting().getMinGuildNameLength();
            String guildName = guildArg.get();

            if (guildName.isEmpty() || guildName.length() < minGuildNameLength) return CommandResult.empty();

            Guild guild = RachamonGuilds.getInstance().getGuildManager().getGuild(guildName);
            RachamonGuilds.getInstance().getGuildManager().printGuildMemberList(source, guild);
        }

        return CommandResult.success();
    }
}
