package dev.rachamon.rachamonguilds.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * The type Language config.
 */
@ConfigSerializable
public class LanguageConfig {

    @Setting(value = "general", comment = "General Messages")
    private final GeneralCategory generalCategory = new GeneralCategory();

    @Setting(value = "command", comment = "Command Messages")
    private final CommandCategory commandCategory = new CommandCategory();

    @Setting(value = "question", comment = "Question Messages")
    private final QuestionCategory questionCategory = new QuestionCategory();

    /**
     * The type General category.
     */
    @ConfigSerializable
    public static class GeneralCategory {

        /**
         * The Prefix.
         */
        @Setting(comment = "Message prefix", value = "prefix")
        protected String prefix = "&8[&c&lGuild&8]&7 ";

        /**
         * The User not found.
         */
        @Setting(comment = "User not found", value = "user-not-found")
        protected String userNotFound = "&cUser not found";

        /**
         * The Guild not found.
         */
        @Setting(comment = "Guild not found", value = "guild-not-found")
        protected String guildNotFound = "&cGuild doesn't exists.";

        /**
         * The Not guild master.
         */
        @Setting(comment = "when you are not guild master", value = "not-guild-master")
        protected String notGuildMaster = "&cYou cannot do this. You are not guild master.";

        /**
         * The User already in guild.
         */
        @Setting(comment = "if user is already in a guild", value = "user-already-in-guild")
        protected String userAlreadyInGuild = "&cYou are already in a guild!";

        /**
         * The Guild name already exists.
         */
        @Setting(comment = "if guild name already exists", value = "guild-name-already-exist")
        protected String guildNameAlreadyExists = "&cThis guild name already exists.";

        /**
         * The User not in guild.
         */
        @Setting(comment = "if user is not in a guild", value = "user-not-in-guild")
        protected String userNotInGuild = "&cYou are not in a guild!";

        /**
         * The Something went wrong.
         */
        @Setting(comment = "common error message.", value = "something-went-wrong")
        protected String somethingWentWrong = "&cSomething went wrong. Please try again.";

        /**
         * The Member already in guild.
         */
        @Setting(comment = "member already in guild.", value = "member-already-in-guild")
        protected String memberAlreadyInGuild = "&cThat member is already in your guild";

        /**
         * The Member already in another guild.
         */
        @Setting(comment = "member already in other guild.", value = "member-already-in-other-guild")
        protected String memberAlreadyInAnotherGuild = "&cThat member is already in another guild";

        /**
         * The Disband not success.
         */
        @Setting(comment = "message when guild disband error.", value = "disband-not-success")
        protected String disbandNotSuccess = "&cSomething when wrong while disbanding. Please try again.";

        /**
         * The Disband is success.
         */
        @Setting(comment = "message when guild disband success.", value = "disband-is-success")
        protected String disbandIsSuccess = "&aYour guild has been disband successfully.";

        /**
         * The Target is not in guild.
         */
        @Setting(comment = "message when player is not in your guild", value = "target-is-not-in-guild")
        protected String targetIsNotInGuild = "&c&l{target}&c is not in your guild.";

        /**
         * The New guild master.
         */
        @Setting(comment = "message when guild master has been transfer", value = "new-guild-master")
        protected String newGuildMaster = "&a&l{old}&7 has transfer guild master to &a&l{new}&a. &a&l{new}&7 is now a new guild master! please be respect";

        /**
         * The Member join guild.
         */
        @Setting(comment = "message when player join the guild" + "variable:\n" + " - {target}: the target", value = "member-leave-guild")
        protected String memberJoinGuild = "&8( &a→ &8)&7 &a&l{target}&7 has join the guild. welcome!";

        /**
         * The Member leave guild.
         */
        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {target}: the target", value = "member-join-guild")
        protected String memberLeaveGuild = "&8( &c← &8) &c&l{target}&7 has leave the guild.";

        /**
         * The Invite accepted.
         */
        @Setting(comment = "message when player accepted the invite" + "variable:\n" + " - {target}: the target", value = "invite-accepted")
        protected String inviteAccepted = "&a&l{target}&7 has accepted yor invitation.";

        /**
         * The Invite declined.
         */
        @Setting(comment = "message when player declined the invite." + "variable:\n" + " - {target}: the target", value = "invite-declined")
        protected String inviteDeclined = "&c&l{target}&7 has declined yor invitation.";

        /**
         * The Invite accepted target.
         */
        @Setting(comment = "message when player accepted the invite to the guild" + "variable:\n" + " - {guild-name}: name of the guild", value = "invite-accepted-target")
        protected String inviteAcceptedTarget = "&7you has accepted &a&l{guild-name}&7 invitation.";

        /**
         * The Invite declined target.
         */
        @Setting(comment = "message when player declined the invite to the guild." + "variable:\n" + " - {guild-name}: name of the guild", value = "invite-declined-target")
        protected String inviteDeclinedTarget = "&7you has declined &a&l{guild-name}&7 invitation.";

        /**
         * The Invite player to guild.
         */
        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {guild-master}: the guild master\n" + " - {guild-name}: name of the guild", value = "invite-player-to-guild")
        protected String invitePlayerToGuild = "&a&l{guild-master}&7 has invited you to guild &a&l{guild-name}&7.";

        /**
         * The Invited player to guild.
         */
        @Setting(comment = "message when player leave the guild" + "variable:\n" + " - {target}: the target", value = "invited-player-to-guild")
        protected String invitedPlayerToGuild = "&a&l{target}&7 has been invited to the guild.";

        /**
         * The Guild chat format.
         */
        @Setting(comment = "guild chat format" + "variable:\n" + " - {member}: member", value = "guild-chat-format")
        protected String guildChatFormat = "&8[&c{member}&8] &6∴&7 ";

        /**
         * The Guild chat spy format.
         */
        @Setting(comment = "guild chat spy format" + "variable:\n" + " - {member}: member\n" + " - {guild-name}: member", value = "guild-chat-spy-format")
        protected String guildChatSpyFormat = "&8[&aSPY&8][&74{guild-name}&8][&c{member}&8] &6∴&7 ";

        /**
         * The No permission.
         */
        @Setting(comment = "no permission", value = "no-permission")
        protected String noPermission = "&cYou don't have permission to perform this command";

        /**
         * Gets user already in guild.
         *
         * @return the user already in guild
         */
        public String getUserAlreadyInGuild() {
            return userAlreadyInGuild;
        }

        /**
         * Gets user not found.
         *
         * @return the user not found
         */
        public String getUserNotFound() {
            return userNotFound;
        }

        /**
         * Gets prefix.
         *
         * @return the prefix
         */
        public String getPrefix() {
            return prefix;
        }

        /**
         * Gets guild not found.
         *
         * @return the guild not found
         */
        public String getGuildNotFound() {
            return guildNotFound;
        }

        /**
         * Gets disband not success.
         *
         * @return the disband not success
         */
        public String getDisbandNotSuccess() {
            return disbandNotSuccess;
        }

        /**
         * Gets disband is success.
         *
         * @return the disband is success
         */
        public String getDisbandIsSuccess() {
            return disbandIsSuccess;
        }

        /**
         * Gets target is not in guild.
         *
         * @return the target is not in guild
         */
        public String getTargetIsNotInGuild() {
            return targetIsNotInGuild;
        }

        /**
         * Gets new guild master.
         *
         * @return the new guild master
         */
        public String getNewGuildMaster() {
            return newGuildMaster;
        }

        /**
         * Gets member leave guild.
         *
         * @return the member leave guild
         */
        public String getMemberLeaveGuild() {
            return memberLeaveGuild;
        }

        /**
         * Gets member join guild.
         *
         * @return the member join guild
         */
        public String getMemberJoinGuild() {
            return memberJoinGuild;
        }

        /**
         * Gets something went wrong.
         *
         * @return the something went wrong
         */
        public String getSomethingWentWrong() {
            return somethingWentWrong;
        }

        /**
         * Gets not guild master.
         *
         * @return the not guild master
         */
        public String getNotGuildMaster() {
            return notGuildMaster;
        }

        /**
         * Gets member already in guild.
         *
         * @return the member already in guild
         */
        public String getMemberAlreadyInGuild() {
            return memberAlreadyInGuild;
        }

        /**
         * Gets member already in another guild.
         *
         * @return the member already in another guild
         */
        public String getMemberAlreadyInAnotherGuild() {
            return memberAlreadyInAnotherGuild;
        }

        /**
         * Gets invite accepted.
         *
         * @return the invite accepted
         */
        public String getInviteAccepted() {
            return inviteAccepted;
        }

        /**
         * Gets invite declined.
         *
         * @return the invite declined
         */
        public String getInviteDeclined() {
            return inviteDeclined;
        }

        /**
         * Gets invite player to guild.
         *
         * @return the invite player to guild
         */
        public String getInvitePlayerToGuild() {
            return invitePlayerToGuild;
        }

        /**
         * Gets invited player to guild.
         *
         * @return the invited player to guild
         */
        public String getInvitedPlayerToGuild() {
            return invitedPlayerToGuild;
        }

        /**
         * Gets invite accepted target.
         *
         * @return the invite accepted target
         */
        public String getInviteAcceptedTarget() {
            return inviteAcceptedTarget;
        }

        /**
         * Gets invite declined target.
         *
         * @return the invite declined target
         */
        public String getInviteDeclinedTarget() {
            return inviteDeclinedTarget;
        }

        /**
         * Gets user not in guild.
         *
         * @return the user not in guild
         */
        public String getUserNotInGuild() {
            return userNotInGuild;
        }

        /**
         * Gets guild name already exists.
         *
         * @return the guild name already exists
         */
        public String getGuildNameAlreadyExists() {
            return guildNameAlreadyExists;
        }

        /**
         * Gets guild chat format.
         *
         * @return the guild chat format
         */
        public String getGuildChatFormat() {
            return guildChatFormat;
        }

        /**
         * Gets guild chat spy format.
         *
         * @return the guild chat spy format
         */
        public String getGuildChatSpyFormat() {
            return guildChatSpyFormat;
        }

        /**
         * Gets no permission.
         *
         * @return the no permission
         */
        public String getNoPermission() {
            return noPermission;
        }
    }

    /**
     * The type Command category.
     */
    @ConfigSerializable
    public static class CommandCategory {

        /**
         * The Command created name too short.
         */
        @Setting(comment = "guild name too short", value = "command-create-name-too-short")
        protected String commandCreatedNameTooShort = "&c&lGuild name too short";
        /**
         * The Command created name too long.
         */
        @Setting(comment = "guild name too short", value = "command-create-name-too-long")
        protected String commandCreatedNameTooLong = "&c&lGuild name too short";
        /**
         * The Command created display name too short.
         */
        @Setting(comment = "guild display name too short", value = "command-create-displayname-too-short")
        protected String commandCreatedDisplayNameTooShort = "&c&lGuild display name too short";
        /**
         * The Command created display name too long.
         */
        @Setting(comment = "guild display name too long", value = "command-create-displayname-too-long")
        protected String commandCreatedDisplayNameTooLong = "&c&lGuild display name too long";

        /**
         * The Command invalid guild name.
         */
        @Setting(comment = "guild display name invalid", value = "command-invalid-guild-name")
        protected String commandInvalidGuildName = "&c&lGuild name is invalid. available a-z, A-Z";

        /**
         * The Command invalid guild display name.
         */
        @Setting(comment = "guild display name too long", value = "command-invalid-guild-displayname")
        protected String commandInvalidGuildDisplayName = "&c&lGuild name is invalid. available a-z, A-Z";

        /**
         * The Command created broadcast.
         */
        @Setting(comment = "" + "Broadcast message after the guild were created\n" + "variable:\n" + " - {guild-name}: name of the new guild\n", value = "created-broadcast")
        protected String commandCreatedBroadcast = "&aNew Guild has enter the arena! the name is &a&l{guild-name}&7 please be nice! ";

        /**
         * The Command disband broadcast.
         */
        @Setting(comment = "" + "Broadcast message after the guild were disbanded\n" + "variable:\n" + " - {guild-name}: name of the new guild\n", value = "disband-broadcast")
        protected String commandDisbandBroadcast = "&cGuild &4&l{guild-name}&c has been disbanded.";

        /**
         * The Command created success.
         */
        @Setting(comment = "Created guild success message", value = "created-success")
        protected String commandCreatedSuccess = "&a&lThe guild has been created successfully";

        /**
         * The Command list guild print.
         */
        @Setting(comment = "Print guild list\n" + "variables:\n" + " - {index}: index of the guild\n" + " - {guild-master}: Guild master\n" + " - {guild-name}: Guild name\n" + " - {guild-size}: Guild member size\n", value = "list-guild-print")
        protected String commandListGuildPrint = "&7{index} &8. &6&l{guild-name} &8, &2&2guild master is &a&l{guild-master}&r &2with &a&l&n{guild-size}&r &2member";

        /**
         * The Command info member list print.
         */
        @Setting(comment = "Print guild member list\n" + "variables:\n" + " - {index}: index of the guild\n" + " - {name}: member name\n" + " - {first-join}: Guild created date\n" + " - {is-online}: player status", value = "info-member-list-print")
        protected String commandInfoMemberListPrint = "&7{index} &8. &7{name}&c first join &8: &7{first-join}";

        /**
         * The Command guild info print.
         */
        @Setting(comment = "Print guild member list\n" + "variables:\n" + " - {guild-master}: Guild master\n" + " - {guild-name}: Guild name\n" + " - {guild-displayname}: Guild displayname\n" + " - {guild-creation-date}: Guild creation date\n" + " - {guild-master-status}: Guild master status\n" + " - {guild-size}: Guild member size", value = "info-guild-info-print")
        protected String commandGuildInfoPrint = "" + "&6&l{guild-displayname} &8( &6&l{guild-name}&r &8)\n" + "&c&lCreation Date&7 : &7{guild-creation-date}\n" + "&c&lMembers&7 :&7 {guild-size}\n" + "&c&lGuild Master&7 : &6&l{guild-master}&8 ( {guild-master-status} &8)";

        /**
         * The Command transfer success.
         */
        @Setting(comment = "message when guild master has been transferred" + "variable:\n" + " - {target}: new guild master", value = "command-transfer-success")
        protected String commandTransferSuccess = "&7The guild master has been transferred successfully, &a&l{target}&7 is now a guild master";

        /**
         * The Command leave guild master.
         */
        @Setting(comment = "message when leave guild but you are master.", value = "command-leave-guild-master")
        protected String commandLeaveGuildMaster = "&7You cannot leave guild. You are guild master. If you wish to disband this guild type &4/guild disband";

        /**
         * The Command leave guild success.
         */
        @Setting(comment = "message when you leave guild\n" + "variable:\n" + " - {guild-name}: name of the guild", value = "command-leave-guild-success")
        protected String commandLeaveGuildSuccess = "&7You have successfully leave guild &c&l{guild-name}&7.";

        /**
         * The Command kick self.
         */
        @Setting(comment = "message when you kick your self", value = "command-kick-self")
        protected String commandKickSelf = "&cYou cannot kick your self from guild.";

        /**
         * The Command set home success.
         */
        @Setting(comment = "message when set home successfully", value = "command-sethome-success")
        protected String commandSetHomeSuccess = "&aYou have successfully set guild home.";

        /**
         * The Command home success.
         */
        @Setting(comment = "message when teleport to guild home successfully", value = "command-home-success")
        protected String commandHomeSuccess = "&aYou have successfully teleported to guild home.";

        /**
         * The Command home not found.
         */
        @Setting(comment = "message when has no guild home", value = "command-home-not-found")
        protected String commandHomeNotFound = "&cYou guild has no home set.";

        /**
         * The Command kick success.
         */
        @Setting(comment = "message when you kick successfully" + "variable:\n" + " - {target}: kicked target", value = "command-kick-success")
        protected String commandKickSuccess = "&a&l{target}&7 has been kick successfully";

        /**
         * The Command name success.
         */
        @Setting(comment = "message when you change the guild name successfully" + "variable:\n" + " - {name}: new name", value = "command-name-success")
        protected String commandNameSuccess = "&7Guild name has been changed to &a&l{name}&7.";

        /**
         * The Command prefix success.
         */
        @Setting(comment = "message when you change the guild displayname successfully" + "variable:\n" + " - {prefix}: new displayname", value = "command-prefix-success")
        protected String commandPrefixSuccess = "&7Guild name has been changed to &a&l{prefix}&7.";

        /**
         * The Command set motd success.
         */
        @Setting(comment = "message when set motd", value = "command-set-motd-success")
        protected String commandSetMotdSuccess = "&aYou have successfully set MOTD.";

        /**
         * The Command set motd success other.
         */
        @Setting(comment = "message when set motd", value = "command-set-motd-success-other")
        protected String commandSetMotdSuccessOther = "&7New MOTD has been set. type &c/guild motd&7 to view new MOTD.";

        /**
         * Gets command info member list print.
         *
         * @return the command info member list print
         */
        public String getCommandInfoMemberListPrint() {
            return commandInfoMemberListPrint;
        }

        /**
         * Gets command guild info print.
         *
         * @return the command guild info print
         */
        public String getCommandGuildInfoPrint() {
            return commandGuildInfoPrint;
        }

        /**
         * Gets command created broadcast.
         *
         * @return the command created broadcast
         */
        public String getCommandCreatedBroadcast() {
            return commandCreatedBroadcast;
        }

        /**
         * Gets command created success.
         *
         * @return the command created success
         */
        public String getCommandCreatedSuccess() {
            return commandCreatedSuccess;
        }

        /**
         * Gets command created name too short.
         *
         * @return the command created name too short
         */
        public String getCommandCreatedNameTooShort() {
            return commandCreatedNameTooShort;
        }

        /**
         * Gets command created name too long.
         *
         * @return the command created name too long
         */
        public String getCommandCreatedNameTooLong() {
            return commandCreatedNameTooLong;
        }

        /**
         * Gets command created display name too short.
         *
         * @return the command created display name too short
         */
        public String getCommandCreatedDisplayNameTooShort() {
            return commandCreatedDisplayNameTooShort;
        }

        /**
         * Gets command created display name too long.
         *
         * @return the command created display name too long
         */
        public String getCommandCreatedDisplayNameTooLong() {
            return commandCreatedDisplayNameTooLong;
        }

        /**
         * Gets command list guild print.
         *
         * @return the command list guild print
         */
        public String getCommandListGuildPrint() {
            return commandListGuildPrint;
        }

        /**
         * Gets command disband broadcast.
         *
         * @return the command disband broadcast
         */
        public String getCommandDisbandBroadcast() {
            return commandDisbandBroadcast;
        }

        /**
         * Gets command transfer success.
         *
         * @return the command transfer success
         */
        public String getCommandTransferSuccess() {
            return commandTransferSuccess;
        }

        /**
         * Gets command leave guild master.
         *
         * @return the command leave guild master
         */
        public String getCommandLeaveGuildMaster() {
            return commandLeaveGuildMaster;
        }

        /**
         * Gets command leave guild success.
         *
         * @return the command leave guild success
         */
        public String getCommandLeaveGuildSuccess() {
            return commandLeaveGuildSuccess;
        }

        /**
         * Gets command kick self.
         *
         * @return the command kick self
         */
        public String getCommandKickSelf() {
            return commandKickSelf;
        }

        /**
         * Gets command kick success.
         *
         * @return the command kick success
         */
        public String getCommandKickSuccess() {
            return commandKickSuccess;
        }

        /**
         * Gets command name success.
         *
         * @return the command name success
         */
        public String getCommandNameSuccess() {
            return commandNameSuccess;
        }

        /**
         * Gets command prefix success.
         *
         * @return the command prefix success
         */
        public String getCommandPrefixSuccess() {
            return commandPrefixSuccess;
        }

        /**
         * Gets command set home success.
         *
         * @return the command set home success
         */
        public String getCommandSetHomeSuccess() {
            return commandSetHomeSuccess;
        }

        /**
         * Gets command home success.
         *
         * @return the command home success
         */
        public String getCommandHomeSuccess() {
            return commandHomeSuccess;
        }

        /**
         * Gets command home not found.
         *
         * @return the command home not found
         */
        public String getCommandHomeNotFound() {
            return commandHomeNotFound;
        }

        /**
         * Gets command invalid guild name.
         *
         * @return the command invalid guild name
         */
        public String getCommandInvalidGuildName() {
            return commandInvalidGuildName;
        }

        /**
         * Gets command invalid guild display name.
         *
         * @return the command invalid guild display name
         */
        public String getCommandInvalidGuildDisplayName() {
            return commandInvalidGuildDisplayName;
        }

        /**
         * Gets command set motd success.
         *
         * @return the command set motd success
         */
        public String getCommandSetMotdSuccess() {
            return commandSetMotdSuccess;
        }

        /**
         * Gets command set motd success other.
         *
         * @return the command set motd success other
         */
        public String getCommandSetMotdSuccessOther() {
            return commandSetMotdSuccessOther;
        }
    }

    /**
     * The type Question category.
     */
    @ConfigSerializable
    public static class QuestionCategory {
        /**
         * The Click to view.
         */
        @Setting(comment = "question click to view.", value = "click-to-view")
        protected String clickToView = "&aClick to View";

        /**
         * The Click to answer.
         */
        @Setting(comment = "question click to answer.", value = "click-to-answer")
        protected String clickToAnswer = "&aClick to Answer";

        /**
         * The Must be player.
         */
        @Setting(comment = "question click to view.", value = "must-be-player")
        protected String mustBePlayer = "&cYou must be player to answer this question";

        /**
         * The Already responded.
         */
        @Setting(comment = "question click to view.", value = "already-responded")
        protected String alreadyResponded = "&cYou have already responded to that question!";

        /**
         * The Accept button.
         */
        @Setting(comment = "accept button", value = "accept-button")
        protected String acceptButton = "&aAccept";

        /**
         * The Declined button.
         */
        @Setting(comment = "declined button", value = "declined-button")
        protected String declinedButton = "&cDeclined";

        /**
         * Gets already responded.
         *
         * @return the already responded
         */
        public String getAlreadyResponded() {
            return alreadyResponded;
        }

        /**
         * Gets must be player.
         *
         * @return the must be player
         */
        public String getMustBePlayer() {
            return mustBePlayer;
        }

        /**
         * Gets click to answer.
         *
         * @return the click to answer
         */
        public String getClickToAnswer() {
            return clickToAnswer;
        }

        /**
         * Gets click to view.
         *
         * @return the click to view
         */
        public String getClickToView() {
            return clickToView;
        }

        /**
         * Gets declined button.
         *
         * @return the declined button
         */
        public String getDeclinedButton() {
            return declinedButton;
        }

        /**
         * Gets accept button.
         *
         * @return the accept button
         */
        public String getAcceptButton() {
            return acceptButton;
        }
    }

    /**
     * Gets general category.
     *
     * @return the general category
     */
    public GeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    /**
     * Gets command category.
     *
     * @return the command category
     */
    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    /**
     * Gets question category.
     *
     * @return the question category
     */
    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }
}
