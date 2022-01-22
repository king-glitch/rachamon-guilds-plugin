package dev.rachamon.rachamonguilds.api.interfaces.command;


import org.spongepowered.api.command.spec.CommandExecutor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Command children.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICommandChildren {
    /**
     * Value class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends CommandExecutor>[] value();
}