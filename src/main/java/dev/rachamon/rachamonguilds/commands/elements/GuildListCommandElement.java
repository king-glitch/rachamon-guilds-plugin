package dev.rachamon.rachamonguilds.commands.elements;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Guild list command element.
 */
public class GuildListCommandElement extends CommandElement {
    /**
     * Instantiates a new Guild list command element.
     *
     * @param key the key
     */
    public GuildListCommandElement(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource source, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        return RachamonGuilds.getInstance().getGuildManager().getGuilds().values().stream().map(Guild::getName).collect(Collectors.toList());

    }
}