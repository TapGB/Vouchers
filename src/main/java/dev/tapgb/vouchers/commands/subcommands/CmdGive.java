package dev.tapgb.vouchers.commands.subcommands;

import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.commands.CommandHandler;
import dev.tapgb.vouchers.commands.CommandRequirement;
import dev.tapgb.vouchers.commands.CommandRequirements;
import dev.tapgb.vouchers.vouchers.Voucher;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Collections;

public class CmdGive extends CommandHandler {

    public CmdGive(){
        this.requirements = new CommandRequirements(CommandRequirement.PERMISSION);
        this.permission = new Permission("vouchers.give");
        this.aliases = Collections.singletonList("give");
        this.build();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length > 1){
            String voucherID = args[1].toLowerCase();
            if(Vouchers.getInstance().getVoucherManager().isVoucher(voucherID)){
                Voucher voucher = Vouchers.getInstance().getVoucherManager().getVoucher(voucherID);
                if(args.length > 2){
                    if(Bukkit.getServer().getPlayer(args[2]) != null){
                        Player target = Bukkit.getServer().getPlayer(args[2]);
                        target.getInventory().addItem(voucher.toItemStack());
                        target.sendMessage(Utils.c(Utils.getMessage("voucher-received").replace("{voucher}", voucher.getId())));
                        sender.sendMessage(Utils.c(Utils.getMessage("voucher-given").replace("{player}", target.getName()).replace("{voucher}", voucher.getId())));
                    }
                }else{
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        player.getInventory().addItem(voucher.toItemStack());
                        player.sendMessage(Utils.c(Utils.getMessage("voucher-received").replace("{voucher}", voucher.getId())));
                    }else{
                        sender.sendMessage(Utils.c(Utils.getMessage("error-players-only")));
                    }
                }
            }else{
                sender.sendMessage(Utils.c(Utils.getMessage("error-voucher-not-found").replace("{voucher}", voucherID)));
            }
        }else{
            sender.sendMessage(Utils.c(Utils.getMessage("usage").replace("{usage}", getUsage())));
        }
    }

    @Override
    public String getUsage() {
        return "/vouchers give <voucher> <player>";
    }
}
