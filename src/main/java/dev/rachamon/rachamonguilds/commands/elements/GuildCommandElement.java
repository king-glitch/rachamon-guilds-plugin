package dev.rachamon.rachamonguilds.commands.elements;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;
import dev.rachamon.rachamonguilds.api.exceptions.GuildCommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Guild command element.
 */
public class GuildCommandElement extends CommandElement {
    /**
     * Instantiates a new Guild command element.
     *
     * @param key the key
     */
    public GuildCommandElement(Text key) {
        super(key);
    }

    @Override
    protected Object parseValue(@Nonnull CommandSource source, @Nonnull CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource source, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        return RachamonGuilds.getInstance().getGuildManager().getGuilds().values().stream().map(Guild::getName).collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public Text getUsage(@Nonnull CommandSource source) {
        return Text.of("<guild>");
    }
}
