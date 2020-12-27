package dev.tapgb.vouchers.commands.subcommands;

import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.commands.CommandHandler;
import dev.tapgb.vouchers.commands.CommandRequirement;
import dev.tapgb.vouchers.commands.CommandRequirements;
import dev.tapgb.vouchers.vouchers.Voucher;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.Collections;

public class CmdList extends CommandHandler {

    public CmdList(){
        this.aliases = Collections.singletonList("list");
        this.requirements = new CommandRequirements(CommandRequirement.PERMISSION);
        this.permission = new Permission("shops.list");
        this.build();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        for(String message: Vouchers.getInstance().getConfigManager("messages").getConfig().getStringList("vouchers-list")){
            if(message.contains("{vouchers}")){
                for(Voucher voucher: Vouchers.getInstance().getVoucherManager().getAllVouchers()){
                    sender.sendMessage(Utils.c(message.replace("{vouchers}", voucher.getId())));
                }
            }else{
                sender.sendMessage(Utils.c(message));
            }
        }
    }

    @Override
    public String getUsage() {
        return "/vouchers list";
    }

}
