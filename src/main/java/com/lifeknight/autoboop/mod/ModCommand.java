package com.lifeknight.autoboop.mod;

import com.lifeknight.autoboop.utilities.Chat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;

import static com.lifeknight.autoboop.mod.Core.MOD_ID;
import static com.lifeknight.autoboop.mod.Core.runMod;

public class ModCommand extends CommandBase {
    private final List<String> aliases = Collections.singletonList("abp");

    public String getCommandName() {
        return MOD_ID;
    }

    public String getCommandUsage(ICommandSender iCommandSender) {
        return MOD_ID;
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
        runMod.toggle();
        Chat.addChatMessage(runMod.getAsString());
    }
}
