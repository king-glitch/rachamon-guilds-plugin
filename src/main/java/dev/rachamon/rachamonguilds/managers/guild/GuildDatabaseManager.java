package dev.rachamon.rachamonguilds.managers.guild;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.rachamon.rachamonguilds.RachamonGuilds;
import dev.rachamon.rachamonguilds.api.entities.Guild;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The type Guild database manager.
 */
public class GuildDatabaseManager {

    private static final Path storageDirectory = RachamonGuilds.getInstance().getDirectory().resolve("storage");
    private static final Path logsDirectory = storageDirectory.resolve("logs");
    private static final Path guildsDirectory = storageDirectory.resolve("guilds");

    private static Map<UUID, Guild> guilds = new HashMap<>();

    /**
     * Gets guilds.
     *
     * @return the guilds
     */
    public static Map<UUID, Guild> getGuilds() {
        return guilds;
    }

    /**
     * Sets guilds.
     *
     * @param guilds the guilds
     */
    public static void setGuilds(Map<UUID, Guild> guilds) {
        GuildDatabaseManager.guilds = guilds;
    }

    /**
     * Initialize.
     */
    public static void initialize() {
        Gson gson = new Gson();

        try {
            Files.createDirectories(logsDirectory);
            Files.createDirectories(guildsDirectory);

            for (String _fileName : Objects.requireNonNull(guildsDirectory.toFile().list())) {
                String fileName = String.valueOf(guildsDirectory.resolve(_fileName));

                Type guildType = new TypeToken<Guild>() {
                }.getType();

                Guild guild = gson.fromJson(new FileReader(fileName), guildType);
                guilds.put(guild.getId(), guild);
                RachamonGuilds.getInstance().getLogger().debug("adding " + guild.getName() + " to caching...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save.
     */
    public static void save() {
        Gson gson = new Gson();

        String[] files = Objects.requireNonNull(guildsDirectory.toFile().list());

        for (Guild guild : guilds.values()) {
            try {
                String fileName = String.valueOf(guildsDirectory.resolve(guild.getId() + ".json"));
                RachamonGuilds.getInstance().getLogger().debug("saving guild " + guild.getId() + " to the database.");
                FileWriter writer = new FileWriter(fileName);
                gson.toJson(guild, writer);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete boolean.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    public static boolean delete(UUID uuid) {
        Path path = guildsDirectory.resolve(uuid + ".json");
        RachamonGuilds.getInstance().getLogger().debug("&cDeleting guild " + uuid + " from the database.");
        return path.toFile().delete();
    }

    /**
     * Save.
     *
     * @param guild the guild
     */
    public static void save(Guild guild) {
        Gson gson = new Gson();
        Path path = storageDirectory.resolve(guild.getId() + ".json");
        try {

            if (!path.toFile().exists()) {
                path.toFile().createNewFile();
            }

            FileWriter writer = new FileWriter(String.valueOf(path));
            gson.toJson(guild, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
