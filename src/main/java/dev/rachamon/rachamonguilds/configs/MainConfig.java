package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * The type Main config.
 */
@ConfigSerializable
public class MainConfig {

    @Setting(value = "general", comment = "General Settings")
    protected GeneralCategorySetting mainCategorySetting = new GeneralCategorySetting();

    @Setting(value = "guild", comment = "Guild Settings")
    protected GuildCategorySetting guildCategorySetting = new GuildCategorySetting();

    /**
     * The type General category setting.
     */
    @ConfigSerializable
    public static class GeneralCategorySetting {
        @Setting(comment = "Send message to all online players on guild created? [default: true]", value = "is-send-global-message-on-guild-created")
        protected boolean isSendGlobalMessageOnGuildCreated = true;

        @Setting(comment = "Send message to all online players when guild disbanded? [default: true]", value = "is-send-global-message-on-guild-created")
        protected boolean isSendGlobalMessageOnGuildDisbanded = true;

        @Setting(comment = "show debug messages [default: false]", value = "debug")
        protected boolean isDebug = false;

        /**
         * Is send global message on guild created boolean.
         *
         * @return the boolean
         */
        public boolean isSendGlobalMessageOnGuildCreated() {
            return isSendGlobalMessageOnGuildCreated;
        }

        /**
         * Is debug boolean.
         *
         * @return the boolean
         */
        public boolean isDebug() {
            return isDebug;
        }

        /**
         * Is send global message on guild disbanded boolean.
         *
         * @return the boolean
         */
        public boolean isSendGlobalMessageOnGuildDisbanded() {
            return isSendGlobalMessageOnGuildDisbanded;
        }
    }

    /**
     * The type Guild category setting.
     */
    @ConfigSerializable
    public static class GuildCategorySetting {

        @Setting(comment = "A regex valid guild name [default: [a-zA-Z]]", value = "valid-name-regex")
        protected String validNameRegex = "[a-zA-Z]*";

        @Setting(comment = "Min guild name length [default: 4]", value = "min-guild-name-length")
        protected int minGuildNameLength = 4;

        @Setting(comment = "Max guild name length [default: 16]", value = "max-guild-name-length")
        protected int maxGuildNameLength = 16;

        @Setting(comment = "Min guild displayname length [default: 4]", value = "min-guild-displayname-length")
        protected int minGuildDisplayNameLength = 4;

        @Setting(comment = "Max guild displayname length [default: 16]", value = "max-guild-displayname-length")
        protected int maxGuildDisplayNameLength = 16;

        @Setting(comment = "Max Guild displayname includes color? [default: true]", value = "guild-displayname-include-color")
        protected boolean guildDisplayNameIncludeColor = true;

        /**
         * Gets min guild display name length.
         *
         * @return the min guild display name length
         */
        public int getMinGuildDisplayNameLength() {
            return minGuildDisplayNameLength;
        }

        /**
         * Gets max guild display name length.
         *
         * @return the max guild display name length
         */
        public int getMaxGuildDisplayNameLength() {
            return maxGuildDisplayNameLength;
        }

        /**
         * Is guild display name include color boolean.
         *
         * @return the boolean
         */
        public boolean isGuildDisplayNameIncludeColor() {
            return guildDisplayNameIncludeColor;
        }

        /**
         * Gets valid name regex.
         *
         * @return the valid name regex
         */
        public String getValidNameRegex() {
            return validNameRegex;
        }

        /**
         * Gets min guild name length.
         *
         * @return the min guild name length
         */
        public int getMinGuildNameLength() {
            return minGuildNameLength;
        }

        /**
         * Gets max guild name length.
         *
         * @return the max guild name length
         */
        public int getMaxGuildNameLength() {
            return maxGuildNameLength;
        }

    }

    /**
     * The Config version.
     */
    @Setting(value = "config-version")
    public Double configVersion = 1.0;

    /**
     * Gets guild category setting.
     *
     * @return the guild category setting
     */
    public GuildCategorySetting getGuildCategorySetting() {
        return guildCategorySetting;
    }

    /**
     * Gets general category setting.
     *
     * @return the general category setting
     */
    public GeneralCategorySetting getGeneralCategorySetting() {
        return mainCategorySetting;
    }

    /**
     * Gets config version.
     *
     * @return the config version
     */
    public Double getConfigVersion() {
        return configVersion;
    }

}
