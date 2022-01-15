package dev.rachamon.rachamonguilds.configs;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class GuildConfigHolder {
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();
    private HoconConfigurationLoader loader;
    private CommentedConfigurationNode node;

    public GuildConfigHolder(Path path, boolean asset) throws IOException {
        try {
            if (Files.notExists(path)) {
                if (asset) {
                    this.plugin.getContainer().getAsset(path.getFileName().toString()).get().copyToFile(path);
                } else {
                    Files.createFile(path);
                }
            }
        } catch (IOException e) {
            this.plugin.getLogger().error("Error loading config file! File:[" + path.getFileName().toString() + "]");
            this.plugin.getLogger().warning(Arrays.toString(e.getStackTrace()));
        }
    }

    public CommentedConfigurationNode getNode(Object... path) {
        return this.node.getNode(path);
    }

    public boolean save() {
        try {
            loader.save(this.node);
            return true;
        } catch (IOException e) {
            this.plugin.getLogger().error("Unable to save config file! Error:");
            this.plugin.getLogger().warning(e.getStackTrace());
        }

        return false;
    }
}
