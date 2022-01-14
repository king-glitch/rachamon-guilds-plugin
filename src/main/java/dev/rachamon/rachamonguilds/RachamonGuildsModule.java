package dev.rachamon.rachamonguilds;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.rachamon.rachamonguilds.facades.GuildFacade;
import dev.rachamon.rachamonguilds.facades.GuildMessagingFacade;
import dev.rachamon.rachamonguilds.services.GuildService;

public class RachamonGuildsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GuildFacade.class).in(Scopes.SINGLETON);
        bind(GuildService.class).in(Scopes.SINGLETON);
        bind(GuildMessagingFacade.class).in(Scopes.SINGLETON);
    }
}
