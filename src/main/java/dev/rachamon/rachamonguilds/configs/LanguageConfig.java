package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig {

    @Setting(value = "general", comment = "General Messages")
    private final GeneralCategory generalCategory = new GeneralCategory();

    @Setting(value = "command", comment = "Command Messages")
    private final CommandCategory commandCategory = new CommandCategory();

    @Setting(value = "question", comment = "Question Messages")
    private final QuestionCategory questionCategory = new QuestionCategory();

    @ConfigSerializable
    public static class GeneralCategory {

        @Setting(comment = "Message prefix", value = "prefix")
        private final String prefix = "&8[&c&lGuild&8]&7 ";

        @Setting(comment = "User not found", value = "user-not-found")
        private final String userNotFound = "&cUser not found";

        @Setting(comment = "Guild not found", value = "guild-not-found")
        private final String guildNotFound = "&cGuild doesn't exists.";

        @Setting(comment = "when you are not guild master", value = "not-guild-master")
        private final String notGuildMaster = "&cYou cannot do this. You are not guild master.";

        @Setting(comment = "if user is already in a guild", value = "user-already-in-guild")
        private final String userAlreadyInGuild = "&cYou are already in a guild!";

        @Setting(comment = "if guild name already exists", value = "guild-name-already-exist")
        private final String guildNameAlreadyExists = "&cThis guild name already exists.";

        @Setting(comment = "if user is not in a guild", value = "user-not-in-guild")
        private final String userNotInGuild = "&cYou are not in a guild!";

        @Setting(comment = "common error message.", value = "something-went-wrong")
        private final String somethingWentWrong = "&cSomething went wrong. Please try again.";

        @Setting(comment = "member already in guild.", value = "member-already-in-guild")
        private final String memberAlreadyInGuild = "&cThat member is already in your guild";

        @Setting(comment = "member already in other guild.", value = "member-already-in-other-guild")
        private final String memberAlreadyInAnotherGuild = "&cThat member is already in another guild";

        @Setting(comment = "message when guild disband error.", value = "disband-not-success")
        private final String disbandNotSuccess = "&cSomething when wrong while disbanding. Please try again.";

        @Setting(comment = "message when guild disband success.", value = "disband-is-success")
        private final String disbandIsSuccess = "&aYour guild has been disband successfully.";

        @Setting(comment = "message when player is not in your guild", value = "target-is-not-in-guild")
        private final String targetIsNotInGuild = "&c&l{target}&c is not in your guild.";

        @Setting(comment = "message when guild master has been transfer", value = "new-guild-master")
        private final String newGuildMaster = "&a&l{old}&7 has transfer guild master to &a&l{new}&a. &a&l{new}&7 is now a new guild master! please be respect";

        @Setting(comment = "message when player join the guild" + "variable:\n" + " - {target}: the target", value = "member-leave-guild")
        private final String memberJoinGuild = "&8( &a→ &8)&7 &a&l{target}&7 has join the guild. welcome!";

        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {target}: the target", value = "member-join-guild")
        private final String memberLeaveGuild = "&8( &c← &8) &c&l{target}&7 has leave the guild.";

        @Setting(comment = "message when player accepted the invite" + "variable:\n" + " - {target}: the target", value = "invite-accepted")
        private final String inviteAccepted = "&a&l{target}&7 has accepted yor invitation.";

        @Setting(comment = "message when player declined the invite." + "variable:\n" + " - {target}: the target", value = "invite-declined")
        private final String inviteDeclined = "&c&l{target}&7 has declined yor invitation.";

        @Setting(comment = "message when player accepted the invite to the guild" + "variable:\n" + " - {guild-name}: name of the guild", value = "invite-accepted-target")
        private final String inviteAcceptedTarget = "&7you has accepted &a&l{guild-name}&7 invitation.";

        @Setting(comment = "message when player declined the invite to the guild." + "variable:\n" + " - {guild-name}: name of the guild", value = "invite-declined-target")
        private final String inviteDeclinedTarget = "&7you has declined &a&l{guild-name}&7 invitation.";

        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {guild-master}: the guild master\n" + " - {guild-name}: name of the guild", value = "invite-player-to-guild")
        private final String invitePlayerToGuild = "&a&l{guild-master}&7 has invited you to guild &a&l{guild-name}&7.";

        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {target}: the target", value = "invited-player-to-guild")
        private final String invitedPlayerToGuild = "&a&l{target}&7 has been invited to the guild.";

        @Setting(comment = "guild chat format" + "variable:\n" + " - {member}: member", value = "guild-chat-format")
        private final String guildChatFormat = "&8[&c{member}&8] &6∴&7 ";

        @Setting(comment = "guild chat spy format" + "variable:\n" + " - {member}: member\n" + " - {guild-name}: member", value = "guild-chat-spy-format")
        private final String guildChatSpyFormat = "&8[&aSPY&8][&74{guild-name}&8][&c{member}&8] &6∴&7 ";

        @Setting(comment = "no permission", value = "no-permission")
        private final String noPermission = "&cYou don't have permission to perform this command";

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

        public String getDisbandNotSuccess() {
            return disbandNotSuccess;
        }

        public String getDisbandIsSuccess() {
            return disbandIsSuccess;
        }

        public String getTargetIsNotInGuild() {
            return targetIsNotInGuild;
        }

        public String getNewGuildMaster() {
            return newGuildMaster;
        }

        public String getMemberLeaveGuild() {
            return memberLeaveGuild;
        }

        public String getMemberJoinGuild() {
            return memberJoinGuild;
        }

        public String getSomethingWentWrong() {
            return somethingWentWrong;
        }

        public String getNotGuildMaster() {
            return notGuildMaster;
        }

        public String getMemberAlreadyInGuild() {
            return memberAlreadyInGuild;
        }

        public String getMemberAlreadyInAnotherGuild() {
            return memberAlreadyInAnotherGuild;
        }

        public String getInviteAccepted() {
            return inviteAccepted;
        }

        public String getInviteDeclined() {
            return inviteDeclined;
        }

        public String getInvitePlayerToGuild() {
            return invitePlayerToGuild;
        }

        public String getInvitedPlayerToGuild() {
            return invitedPlayerToGuild;
        }

        public String getInviteAcceptedTarget() {
            return inviteAcceptedTarget;
        }

        public String getInviteDeclinedTarget() {
            return inviteDeclinedTarget;
        }

        public String getUserNotInGuild() {
            return userNotInGuild;
        }

        public String getGuildNameAlreadyExists() {
            return guildNameAlreadyExists;
        }

        public String getGuildChatFormat() {
            return guildChatFormat;
        }

        public String getGuildChatSpyFormat() {
            return guildChatSpyFormat;
        }

        public String getNoPermission() {
            return noPermission;
        }
    }

    @ConfigSerializable
    public static class CommandCategory {

        @Setting(comment = "guild name too short", value = "command-create-name-too-short")
        private final String commandCreatedNameTooShort = "&c&lGuild name too short";
        @Setting(comment = "guild name too short", value = "command-create-name-too-long")
        private final String commandCreatedNameTooLong = "&c&lGuild name too short";
        @Setting(comment = "guild display name too short", value = "command-create-displayname-too-short")
        private final String commandCreatedDisplayNameTooShort = "&c&lGuild display name too short";
        @Setting(comment = "guild display name too long", value = "command-create-displayname-too-long")
        private final String commandCreatedDisplayNameTooLong = "&c&lGuild display name too long";

        @Setting(comment = "guild display name invalid", value = "command-invalid-guild-name")
        private final String commandInvalidGuildName = "&c&lGuild name is invalid. available a-z, A-Z";

        @Setting(comment = "guild display name too long", value = "command-invalid-guild-displayname")
        private final String commandInvalidGuildDisplayName = "&c&lGuild name is invalid. available a-z, A-Z";

        @Setting(comment = "" + "Broadcast message after the guild were created\n" + "variable:\n" + " - {guild-name}: name of the new guild\n", value = "created-broadcast")
        private final String commandCreatedBroadcast = "&aNew Guild has enter the arena! the name is &a&l{guild-name}&7 please be nice! ";

        @Setting(comment = "" + "Broadcast message after the guild were disbanded\n" + "variable:\n" + " - {guild-name}: name of the new guild\n", value = "disband-broadcast")
        private final String commandDisbandBroadcast = "&cGuild &4&l{guild-name}&c has been disbanded.";

        @Setting(comment = "Created guild success message", value = "created-success")
        private final String commandCreatedSuccess = "&a&lThe guild has been created successfully";

        @Setting(comment = "Print guild list\n" + "variables:\n" + " - {index}: index of the guild\n" + " - {guild-master}: Guild master\n" + " - {guild-name}: Guild name\n" + " - {guild-size}: Guild member size\n", value = "list-guild-print")
        private final String commandListGuildPrint = "&7{index} &8. &6&l{guild-name} &8, &2&2guild master is &a&l{guild-master}&r &2with &a&l&n{guild-size}&r &2member";

        @Setting(comment = "Print guild member list\n" + "variables:\n" + " - {index}: index of the guild\n" + " - {name}: member name\n" + " - {first-join}: Guild created date\n" + " - {is-online}: player status", value = "info-member-list-print")
        private final String commandInfoMemberListPrint = "&7{index} &8. &7{name}&c first join &8: &7{first-join}";

        @Setting(comment = "Print guild member list\n" + "variables:\n" + " - {guild-master}: Guild master\n" + " - {guild-name}: Guild name\n" + " - {guild-displayname}: Guild displayname\n" + " - {guild-creation-date}: Guild creation date\n" + " - {guild-master-status}: Guild master status\n" + " - {guild-size}: Guild member size", value = "info-guild-info-print")
        private final String commandGuildInfoPrint = "" + "&6&l{guild-displayname} &8( &6&l{guild-name}&r &8)\n" + "&c&lCreation Date&7 : &7{guild-creation-date}\n" + "&c&lMembers&7 :&7 {guild-size}\n" + "&c&lGuild Master&7 : &6&l{guild-master}&8 ( {guild-master-status} &8)";

        @Setting(comment = "message when guild master has been transferred" + "variable:\n" + " - {target}: new guild master", value = "command-transfer-success")
        private final String commandTransferSuccess = "&7The guild master has been transferred successfully, &a&l{target}&7 is now a guild master";

        @Setting(comment = "message when leave guild but you are master.", value = "command-leave-guild-master")
        private final String commandLeaveGuildMaster = "&7You cannot leave guild. You are guild master. If you wish to disband this guild type &4/guild disband";

        @Setting(comment = "message when you leave guild\n" + "variable:\n" + " - {guild-name}: name of the guild", value = "command-leave-guild-success")
        private final String commandLeaveGuildSuccess = "&7You have successfully leave guild &c&l{guild-name}&7.";

        @Setting(comment = "message when you kick your self", value = "command-kick-self")
        private final String commandKickSelf = "&cYou cannot kick your self from guild.";

        @Setting(comment = "message when set home successfully", value = "command-sethome-success")
        private final String commandSetHomeSuccess = "&aYou have successfully set guild home.";

        @Setting(comment = "message when teleport to guild home successfully", value = "command-home-success")
        private final String commandHomeSuccess = "&aYou have successfully teleported to guild home.";

        @Setting(comment = "message when has no guild home", value = "command-home-not-found")
        private final String commandHomeNotFound = "&cYou guild has no home set.";

        @Setting(comment = "message when you kick successfully" + "variable:\n" + " - {target}: kicked target", value = "command-kick-success")
        private final String commandKickSuccess = "&a&l{target}&7 has been kick successfully";

        @Setting(comment = "message when you change the guild name successfully" + "variable:\n" + " - {name}: new name", value = "command-name-success")
        private final String commandNameSuccess = "&7Guild name has been changed to &a&l{name}&7.";

        @Setting(comment = "message when you change the guild displayname successfully" + "variable:\n" + " - {prefix}: new displayname", value = "command-prefix-success")
        private final String commandPrefixSuccess = "&7Guild name has been changed to &a&l{prefix}&7.";

        @Setting(comment = "message when set motd", value = "command-set-motd-success")
        private final String commandSetMotdSuccess = "&aYou have successfully set MOTD.";

        @Setting(comment = "message when set motd", value = "command-set-motd-success-other")
        private final String commandSetMotdSuccessOther = "&7New MOTD has been set. type &c/guild motd&7 to view new MOTD.";

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

        public String getCommandDisbandBroadcast() {
            return commandDisbandBroadcast;
        }

        public String getCommandTransferSuccess() {
            return commandTransferSuccess;
        }

        public String getCommandLeaveGuildMaster() {
            return commandLeaveGuildMaster;
        }

        public String getCommandLeaveGuildSuccess() {
            return commandLeaveGuildSuccess;
        }

        public String getCommandKickSelf() {
            return commandKickSelf;
        }

        public String getCommandKickSuccess() {
            return commandKickSuccess;
        }

        public String getCommandNameSuccess() {
            return commandNameSuccess;
        }

        public String getCommandPrefixSuccess() {
            return commandPrefixSuccess;
        }

        public String getCommandSetHomeSuccess() {
            return commandSetHomeSuccess;
        }

        public String getCommandHomeSuccess() {
            return commandHomeSuccess;
        }

        public String getCommandHomeNotFound() {
            return commandHomeNotFound;
        }

        public String getCommandInvalidGuildName() {
            return commandInvalidGuildName;
        }

        public String getCommandInvalidGuildDisplayName() {
            return commandInvalidGuildDisplayName;
        }

        public String getCommandSetMotdSuccess() {
            return commandSetMotdSuccess;
        }

        public String getCommandSetMotdSuccessOther() {
            return commandSetMotdSuccessOther;
        }
    }

    @ConfigSerializable
    public static class QuestionCategory {
        @Setting(comment = "question click to view.", value = "click-to-view")
        private final String clickToView = "&aClick to View";

        @Setting(comment = "question click to answer.", value = "click-to-answer")
        private final String clickToAnswer = "&aClick to Answer";

        @Setting(comment = "question click to view.", value = "must-be-player")
        private final String mustBePlayer = "&cYou must be player to answer this question";

        @Setting(comment = "question click to view.", value = "already-responded")
        private final String alreadyResponded = "&cYou have already responded to that question!";

        @Setting(comment = "accept button", value = "accept-button")
        private final String acceptButton = "&aAccept";

        @Setting(comment = "declined button", value = "declined-button")
        private final String declinedButton = "&cDeclined";

        public String getAlreadyResponded() {
            return alreadyResponded;
        }

        public String getMustBePlayer() {
            return mustBePlayer;
        }

        public String getClickToAnswer() {
            return clickToAnswer;
        }

        public String getClickToView() {
            return clickToView;
        }

        public String getDeclinedButton() {
            return declinedButton;
        }

        public String getAcceptButton() {
            return acceptButton;
        }
    }

    public GeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }
}
