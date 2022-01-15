package dev.rachamon.rachamonguilds.api.interfaces.command;

import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;

public interface IParameterizedCommand extends CommandExecutor {
    CommandElement[] getArguments();
}
