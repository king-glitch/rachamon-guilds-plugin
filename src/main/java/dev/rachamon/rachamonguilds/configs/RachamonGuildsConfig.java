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

/**
 * The type Rachamon guilds config.
 */
public class RachamonGuildsConfig {
    private final RachamonGuilds plugin = RachamonGuilds.getInstance();
    private CommentedConfigurationNode configRoot;
    private CommentedConfigurationNode languageRoot;
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    private ConfigurationLoader<CommentedConfigurationNode> languageLoader;
    private MainConfig root;
    private LanguageConfig language;

    /**
     * Instantiates a new Rachamon guilds config.
     *
     * @param factory the factory
     * @throws IOException the io exception
     */
    public RachamonGuildsConfig(GuiceObjectMapperFactory factory) throws IOException {
        this.plugin.getLogger().info("Loading configuration -> config module");
        try {
            Files.createDirectories(plugin.getDirectory().toFile().toPath());
            File mainConfig = new File(this.plugin.getDirectory().toFile(), "main.conf");
            File languageConfig = new File(this.plugin.getDirectory().toFile(), "language.conf");
            if (!mainConfig.exists()) {
                this.plugin.getLogger().info("Creating Main Configuration...");
                mainConfig.createNewFile();
            }

            if (!languageConfig.exists()) {
                this.plugin.getLogger().info("Creating Language Configuration...");
                languageConfig.createNewFile();
            }

            String header = ""
                    + "Rachamon Guild Plugin Made by ITSAMEWILLIAM#6507 "
                    + "";

            configLoader = HoconConfigurationLoader.builder().setFile(mainConfig).build();
            configRoot = configLoader.load(ConfigurationOptions.defaults().setHeader(header));
            root = configRoot.getValue(TypeToken.of(MainConfig.class), new MainConfig());

            languageLoader = HoconConfigurationLoader.builder().setFile(languageConfig).build();
            languageRoot = languageLoader.load(ConfigurationOptions.defaults().setHeader(header));
            language = languageRoot.getValue(TypeToken.of(LanguageConfig.class), new LanguageConfig());

            this.save();
            plugin.getLogger().success("All configurations loaded!");

        } catch (ObjectMappingException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public MainConfig getRoot() {
        return this.root;
    }

    /**
     * Save.
     */
    public void save() {
        try {
            configRoot.setValue(TypeToken.of(MainConfig.class), root);
            languageRoot.setValue(TypeToken.of(LanguageConfig.class), language);
            this.configLoader.save(configRoot);
            this.languageLoader.save(languageRoot);
        } catch (IOException | ObjectMappingException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public LanguageConfig getLanguage() {
        return language;
    }
}
