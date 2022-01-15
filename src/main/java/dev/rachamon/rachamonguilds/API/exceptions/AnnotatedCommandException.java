package dev.rachamon.rachamonguilds.api.exceptions;

public class AnnotatedCommandException extends Exception {

    private AnnotatedCommandException(String error) {
        super(error);
    }

    public static AnnotatedCommandException noAliases(Class<?> commandClass) {
        return new AnnotatedCommandException(
                "The " + commandClass.getName() + " class is not annotated with Aliases.");
    }

    public static AnnotatedCommandException emptyAlias(Class<?> commandClass) {
        return new AnnotatedCommandException(
                "The " + commandClass.getName() + " class is annotated with an empty alias.");
    }

    public static AnnotatedCommandException childInstantiation(Class<?> commandClass) {
        return new AnnotatedCommandException(
                "Failed to instantiate the " + commandClass.getName()
                        + " class. Ensure this class has an accessible no-args constructor available.");
    }

    public static AnnotatedCommandException parentIParameterizedCommand(Class<?> commandClass) {
        return new AnnotatedCommandException("The " + commandClass.getName()
                + " command class is both a parent ( Annotated with @Children ) and a IParameterizedCommand. Parent command ought not to have parameters.");
    }
}