package dev.rachamon.rachamonguilds;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.rachamon.rachamonguilds.api.services.GuildService;
import dev.rachamon.rachamonguilds.managers.guild.GuildManager;
import dev.rachamon.rachamonguilds.managers.guild.GuildMessagingManager;

public class RachamonGuildsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GuildManager.class).in(Scopes.SINGLETON);
        bind(GuildService.class).in(Scopes.SINGLETON);
        bind(GuildMessagingManager.class).in(Scopes.SINGLETON);
    }
}
