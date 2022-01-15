package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig {

    @Setting(value = "general", comment = "General Messages")
    private final GeneralCategorySetting generalCategorySetting = new GeneralCategorySetting();

    @Setting(value = "command", comment = "Command Messages")
    private final CommandCategorySetting commandCategorySetting = new CommandCategorySetting();

    @ConfigSerializable
    public static class GeneralCategorySetting {

        @Setting(comment = "Message prefix", value = "prefix")
        private final String prefix = "&8[&c&lGuild&8]&7 ";

        @Setting(comment = "User not found", value = "user-not-found")
        private final String userNotFound = "&c&lUser not found";

        @Setting(comment = "Guild not found", value = "guild-not-found")
        private final String guildNotFound = "&c&lGuild doesn't exists.";

        @Setting(comment = "if user is already in a guild", value = "user-already-in-guild")
        private final String userAlreadyInGuild = "&c&lYou are already in a guild!";

        public String getUserAlreadyInGuild() {
            return userAlreadyInGuild;
        }

        public String getUserNotFound() {
            return userNotFound;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getGuildNotFound() {
            return guildNotFound;
        }
    }

    @ConfigSerializable
    public static class CommandCategorySetting {

        @Setting(comment = "guild name too short", value = "command-create-name-too-short")
        private final String commandCreatedNameTooShort = "&c&lGuild name too short";
        @Setting(comment = "guild name too short", value = "command-create-name-too-long")
        private final String commandCreatedNameTooLong = "&c&lGuild name too short";
        @Setting(comment = "guild display name too short", value = "command-create-displayname-too-short")
        private final String commandCreatedDisplayNameTooShort = "&c&lGuild display name too short";
        @Setting(comment = "guild display name too long", value = "command-create-displayname-too-long")
        private final String commandCreatedDisplayNameTooLong = "&c&lGuild display name too long";

        @Setting(
                comment = "" +
                        "Broadcast message after the guild were created\n" +
                        "variable:\n" +
                        " - {guild-name}: name of the new guild\n",
                value = "created-broadcast"
        )
        private final String commandCreatedBroadcast = "&aNew Guild has enter the arena! the name is &a&l{guild-name}&r please be nice! ";

        @Setting(comment = "Created guild success message", value = "created-success")
        private final String commandCreatedSuccess = "&a&lThe guild has been created successfully";

        @Setting(
                comment = "Print guild list\n" +
                        "variables:\n" +
                        " - {index}: index of the guild\n" +
                        " - {guild-master}: Guild master\n" +
                        " - {guild-name}: Guild name\n" +
                        " - {guild-size}: Guild member size\n",
                value = "list-guild-print")
        private final String commandListGuildPrint = "&7{index} &8. &6&l{guild-name} &8, &2&2guild master is &a&l{guild-master}&r &2with &a&l&n{guild-size}&r &2member";

        @Setting(
                comment = "Print guild member list\n" +
                        "variables:\n" +
                        " - {index}: index of the guild\n" +
                        " - {name}: member name\n" +
                        " - {first-join}: Guild created date\n" +
                        " - {is-online}: player status",
                value = "info-member-list-print")
        private final String commandInfoMemberListPrint = "&7{index} &8. &7{name}&c first join &7: {first-join}";

        @Setting(
                comment = "Print guild member list\n" +
                        "variables:\n" +
                        " - {guild-master}: Guild master\n" +
                        " - {guild-name}: Guild name\n" +
                        " - {guild-displayname}: Guild displayname\n" +
                        " - {guild-creation-date}: Guild creation date\n" +
                        " - {guild-master-status}: Guild master status\n" +
                        " - {guild-size}: Guild member size",
                value = "info-guild-info-print")
        private final String commandGuildInfoPrint = "" +
                "&6&l{guild-displayname} &8( &6&l{guild-name}&r &8)\n" +
                "&c&lCreation Date&7 : &7{guild-creation-date}\n" +
                "&c&lMembers&7 :&7 {guild-size}\n" +
                "&c&lGuild Master&7 : &6&l{guild-master}&8 ( {guild-master-status} &8)";

        public String getCommandInfoMemberListPrint() {
            return commandInfoMemberListPrint;
        }

        public String getCommandGuildInfoPrint() {
            return commandGuildInfoPrint;
        }

        public String getCommandCreatedBroadcast() {
            return commandCreatedBroadcast;
        }

        public String getCommandCreatedSuccess() {
            return commandCreatedSuccess;
        }

        public String getCommandCreatedNameTooShort() {
            return commandCreatedNameTooShort;
        }

        public String getCommandCreatedNameTooLong() {
            return commandCreatedNameTooLong;
        }

        public String getCommandCreatedDisplayNameTooShort() {
            return commandCreatedDisplayNameTooShort;
        }

        public String getCommandCreatedDisplayNameTooLong() {
            return commandCreatedDisplayNameTooLong;
        }

        public String getCommandListGuildPrint() {
            return commandListGuildPrint;
        }

//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";
//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";
//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";
//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";
//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";
//
//        @Setting(comment = "", value = "")
//        private final String commandCreatedBroadcast = "";


    }

    public GeneralCategorySetting getGeneralCategorySetting() {
        return generalCategorySetting;
    }

    public CommandCategorySetting getCommandCategorySetting() {
        return commandCategorySetting;
    }
}
