package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {

    @Setting(value = "general", comment = "General Settings")
    private final GeneralCategorySetting mainCategorySetting = new GeneralCategorySetting();

    @Setting(value = "guild", comment = "Guild Settings")
    private final GuildCategorySetting guildCategorySetting = new GuildCategorySetting();

    @ConfigSerializable
    public static class GeneralCategorySetting {
        @Setting(comment = "Send message to all online players on guild created? [default: true]", value = "is-send-global-message-on-guild-created")
        private final boolean isSendGlobalMessageOnGuildCreated = true;

        @Setting(comment = "show debug messages [default: false]", value = "debug")
        private final boolean isDebug = false;

        public boolean isSendGlobalMessageOnGuildCreated() {
            return isSendGlobalMessageOnGuildCreated;
        }

        public boolean isDebug() {
            return isDebug;
        }
    }

    @ConfigSerializable
    public static class GuildCategorySetting {

        @Setting(comment = "A regex valid guild name", value = "valid-name-regex")
        private final String validNameRegex = "[a-zA-Z]";

        @Setting(comment = "Min guild name length", value = "min-guild-name-length")
        private final int minGuildNameLength = 4;

        @Setting(comment = "Max guild name length", value = "max-guild-name-length")
        private final int maxGuildNameLength = 16;

        @Setting(comment = "Min guild displayname length", value = "min-guild-displayname-length")
        private final int minGuildDisplayNameLength = 4;

        @Setting(comment = "Max guild displayname length", value = "max-guild-displayname-length")
        private final int maxGuildDisplayNameLength = 16;

        @Setting(comment = "Max Guild displayname includes color? [default: true]", value = "guild-displayname-include-color")
        private final boolean guildDisplayNameIncludeColor = true;

        public int getMinGuildDisplayNameLength() {
            return minGuildDisplayNameLength;
        }

        public int getMaxGuildDisplayNameLength() {
            return maxGuildDisplayNameLength;
        }

        public boolean isGuildDisplayNameIncludeColor() {
            return guildDisplayNameIncludeColor;
        }

        public String getValidNameRegex() {
            return validNameRegex;
        }

        public int getMinGuildNameLength() {
            return minGuildNameLength;
        }

        public int getMaxGuildNameLength() {
            return maxGuildNameLength;
        }

    }

    @Setting(value = "config-version")
    public Double configVersion = 1.0;

    public GuildCategorySetting getGuildCategorySetting() {
        return guildCategorySetting;
    }

    public GeneralCategorySetting getGeneralCategorySetting() {
        return mainCategorySetting;
    }

    public Double getConfigVersion() {
        return configVersion;
    }

}
