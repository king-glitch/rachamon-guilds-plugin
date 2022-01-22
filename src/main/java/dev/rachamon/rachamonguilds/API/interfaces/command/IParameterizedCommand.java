package dev.rachamon.rachamonguilds.api.interfaces.command;

import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;

/**
 * The interface Parameterized command.
 */
public interface IParameterizedCommand extends CommandExecutor {
    /**
     * Get arguments command element [ ].
     *
     * @return the command element [ ]
     */
    CommandElement[] getArguments();
}
