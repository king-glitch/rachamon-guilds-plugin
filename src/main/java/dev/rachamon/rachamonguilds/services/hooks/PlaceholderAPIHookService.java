package dev.rachamon.rachamonguilds.services.hooks;

import dev.rachamon.rachamonguilds.RachamonGuilds;
import me.rojo8399.placeholderapi.Placeholder;
import me.rojo8399.placeholderapi.PlaceholderService;
import me.rojo8399.placeholderapi.Source;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;

import java.util.Arrays;

public class PlaceholderAPIHookService {

    public PlaceholderAPIHookService(RachamonGuilds plugin) {
        PlaceholderService service = Sponge.getServiceManager().provideUnchecked(PlaceholderService.class);

        service.loadAll(this, plugin).forEach(builder -> {
            if (builder.getId().startsWith("rachamonguilds-")) {
                builder.author(plugin.getContainer().getAuthors().get(0));
                builder.version(plugin.getContainer().getVersion().get());
                try {
                    builder.buildAndRegister();
                } catch (Exception e) {
                    plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
                }
            }
        });
    }

    @Placeholder(id = "rachamonguilds-name")
    public String guildName(@Source CommandSource commandSource) {
        return "";
    }

    @Placeholder(id = "rachamonguilds-id")
    public String guildId(@Source CommandSource commandSource) {
        return "";
    }

    @Placeholder(id = "rachamonguilds-role")
    public String guildRole(@Source CommandSource commandSource) {
        return "";
    }

    @Placeholder(id = "rachamonguilds-master")
    public String guildMaster(@Source CommandSource commandSource) {
        return "";
    }

}
