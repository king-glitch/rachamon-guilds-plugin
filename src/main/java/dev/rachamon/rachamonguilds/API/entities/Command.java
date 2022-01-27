package dev.rachamon.yanamibot.api.entities;

import org.spongepowered.api.command.spec.CommandSpec;

import java.util.List;

/**
 * The type Command.
 */
public class Command {

    private final String[] aliases;
    private CommandSpec spec;
    private final List<Command> children;

    /**
     * Instantiates a new Command.
     *
     * @param spec     the spec
     * @param children the children
     * @param aliases  the aliases
     */
    public Command(CommandSpec spec, List<Command> children, String... aliases) {
        this.children = children;
        this.aliases = aliases;
        this.spec = spec;
    }

    /**
     * Get aliases string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Gets spec.
     *
     * @return the spec
     */
    public CommandSpec getSpec() {
        return spec;
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public List<Command> getChildren() {
        return children;
    }

    /**
     * Sets spec.
     *
     * @param spec the spec
     */
    public void setSpec(CommandSpec spec) {
        this.spec = spec;
    }

}