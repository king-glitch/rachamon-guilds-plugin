package dev.rachamon.rachamonguilds.api.interfaces.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Command help text.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICommandHelpText {

    /**
     * The title for the help command.
     *
     * @return the string
     */
    String title();

    /**
     * For use with child commands.
     *
     * @return the string
     */
    String prefix() default "";

    /**
     * The child command alias to use instead. For instance, "help". If left blank, no child command will be generated.
     *
     * @return the string
     */
    String command() default "";
}