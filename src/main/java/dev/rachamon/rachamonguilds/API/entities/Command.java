package dev.rachamon.rachamonguilds.api.entities;

import org.spongepowered.api.command.spec.CommandSpec;

import java.util.List;

public class Command {

    private final String[] aliases;
    private CommandSpec spec;
    private final List<Command> children;

    public Command(CommandSpec spec, List<Command> children, String... aliases) {
        this.children = children;
        this.aliases = aliases;
        this.spec = spec;
    }

    public String[] getAliases() {
        return aliases;
    }

    public CommandSpec getSpec() {
        return spec;
    }

    public List<Command> getChildren() {
        return children;
    }

    public void setSpec(CommandSpec spec) {
        this.spec = spec;
    }

}