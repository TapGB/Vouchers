package dev.tapgb.vouchers.commands.subcommands;


import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.commands.CommandHandler;
import dev.tapgb.vouchers.commands.CommandRequirement;
import dev.tapgb.vouchers.commands.CommandRequirements;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.Arrays;

public class CmdHelp extends CommandHandler {

    public CmdHelp(){
        this.aliases = new ArrayList<>(Arrays.asList("help", "?"));
        this.requirements = new CommandRequirements(CommandRequirement.PERMISSION);
        this.permission = new Permission("vouchers.help");
        this.build();
    }


    @Override
    public void perform(CommandSender sender, String[] args) {
        for(String message: Vouchers.getInstance().getConfigManager("messages").getConfig().getStringList("help-message")){
            if(message.contains("{commands}")){
                for(CommandHandler commandHandler: CommandHandler.COMMANDS){
                    String usage = commandHandler.getUsage();
                    sender.sendMessage(Utils.c(message.replace("{commands}", usage)));
                }
            }else{
                sender.sendMessage(Utils.c(message));
            }
        }
    }

    @Override
    public String getUsage() {
        return "/vouchers help";
    }
}
