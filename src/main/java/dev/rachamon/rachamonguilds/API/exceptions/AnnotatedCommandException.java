package dev.rachamon.rachamonguilds.api.exceptions;

/**
 * The type Annotated command exception.
 */
public class AnnotatedCommandException extends Exception {

    private AnnotatedCommandException(String error) {
        super(error);
    }

    /**
     * No aliases annotated command exception.
     *
     * @param commandClass the command class
     * @return the annotated command exception
     */
    public static AnnotatedCommandException noAliases(Class<?> commandClass) {
        return new AnnotatedCommandException("The " + commandClass.getName() + " class is not annotated with Aliases.");
    }

    /**
     * Empty alias annotated command exception.
     *
     * @param commandClass the command class
     * @return the annotated command exception
     */
    public static AnnotatedCommandException emptyAlias(Class<?> commandClass) {
        return new AnnotatedCommandException("The " + commandClass.getName() + " class is annotated with an empty alias.");
    }

    /**
     * Child instantiation annotated command exception.
     *
     * @param commandClass the command class
     * @return the annotated command exception
     */
    public static AnnotatedCommandException childInstantiation(Class<?> commandClass) {
        return new AnnotatedCommandException("Failed to instantiate the " + commandClass.getName() + " class. Ensure this class has an accessible no-args constructor available.");
    }

    /**
     * Parent i parameterized command annotated command exception.
     *
     * @param commandClass the command class
     * @return the annotated command exception
     */
    public static AnnotatedCommandException parentIParameterizedCommand(Class<?> commandClass) {
        return new AnnotatedCommandException("The " + commandClass.getName() + " command class is both a parent ( Annotated with @Children ) and a IParameterizedCommand. Parent command ought not to have parameters.");
    }
}