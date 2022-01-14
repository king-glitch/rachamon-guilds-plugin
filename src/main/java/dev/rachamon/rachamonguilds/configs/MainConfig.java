package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {

    @Setting(value = "config-version")
    public Double config_version = 1.0;

}
