package dev.tapgb.vouchers.commands;

import dev.tapgb.vouchers.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdRoot extends CommandHandler implements CommandExecutor {

    public CmdRoot(){
        new CmdAdmin();
        new CmdGive();
        new CmdHelp();
        new CmdList();
        new CmdReload();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return this.execute(sender, args);
    }
}
