package com.lifeknight.autoboop.mod;

import com.lifeknight.autoboop.utilities.Chat;
import com.lifeknight.autoboop.utilities.Text;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lifeknight.autoboop.mod.Core.*;
import static net.minecraft.util.EnumChatFormatting.DARK_GREEN;

public class ModCommand extends CommandBase {
    private final List<String> aliases = Collections.singletonList("abp");
    private final String[] mainCommands = {
            "friend",
            "guild",
    };

    public String getCommandName() {
        return MOD_ID;
    }

    public String getCommandUsage(ICommandSender iCommandSender) {
        return MOD_ID;
    }

    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] arguments, BlockPos blockPosition) {

        if (arguments.length > 0) {
            return Text.returnStartingEntries(this.mainCommands, arguments[0], true);
        }

        return Arrays.asList(this.mainCommands);
    }

    public boolean canCommandSenderUseCommand(ICommandSender arg0) {
        return true;
    }

    public List<String> getCommandAliases() {
        return this.aliases;
    }

    public boolean isUsernameIndex(String[] arguments, int argument1) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }

    public void processCommand(ICommandSender iCommandSender, String[] arguments) throws CommandException {
        if (arguments.length == 0) {
            runMod.toggle();
            Chat.addChatMessage(runMod.getAsString());
            return;
        }
        switch (arguments[0].toLowerCase()) {
            case "friend":
                runFriend.toggle();
                Chat.addChatMessage(runFriend.getAsString());
                return;
            case "guild":
                runGuild.toggle();
                Chat.addChatMessage(runGuild.getAsString());
                return;
        }
        this.addMainCommandMessage();
    }

    public void addMainCommandMessage() {
        StringBuilder result = new StringBuilder(DARK_GREEN + "/" + MOD_ID);

        for (String command : this.mainCommands) {
            result.append(" ").append(command).append(",");
        }

        Chat.addChatMessage(result.substring(0, result.length() - 1));
    }
}
