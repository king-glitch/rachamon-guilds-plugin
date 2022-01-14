package dev.rachamon.rachamonguilds.configs;

import com.google.common.reflect.TypeToken;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class RachamonGuildsConfig {
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();
    private final File mainConfig = new File(this.plugin.getDirectory().toFile(), "main.conf");
    private CommentedConfigurationNode configRoot;
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    private MainConfig root;

    public RachamonGuildsConfig(GuiceObjectMapperFactory factory) throws IOException {
        this.plugin.getLogger().info("Loading configuration -> config module");
        try {
            Files.createDirectories(plugin.getDirectory().toFile().toPath());
            if (!mainConfig.exists()) {
                this.plugin.getLogger().info("Creating Main Configuration...");
                mainConfig.createNewFile();
            }

            String header = ""
                    + "Test Header"
                    + "";

            configLoader = HoconConfigurationLoader.builder().setFile(mainConfig).build();
            configRoot = configLoader.load(ConfigurationOptions.defaults().setHeader(header));
            root = configRoot.getValue(TypeToken.of(MainConfig.class), new MainConfig());

            double update = 0;

            if (configRoot.getNode("config-version").getDouble() < 1.2D) {
                configRoot.getNode("config-version").setValue(1.2D);
                update = 1.2D;
            }

            if (update > 0) {
                this.plugin.getLogger().success("Configuration updated to " + update);
            }

        } catch (ObjectMappingException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
    }

    public MainConfig getRoot() {
        return this.root;
    }

    public void save() {
        try {
            configRoot.setValue(TypeToken.of(MainConfig.class), root);
            this.configLoader.save(configRoot);
        } catch (IOException | ObjectMappingException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
    }
}
