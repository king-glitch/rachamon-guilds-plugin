package dev.rachamon.rachamonguilds.api.interfaces.command;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Command description.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICommandDescription {

    /**
     * Value string.
     *
     * @return the string
     */
    String value();
}