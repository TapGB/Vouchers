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

public class CmdReload extends CommandHandler {

    public CmdReload(){
        this.aliases = new ArrayList<>(Arrays.asList("reload", "rl"));
        this.permission = new Permission("vouchers.reload");
        this.requirements = new CommandRequirements(CommandRequirement.PERMISSION);
        this.build();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Vouchers.getInstance().registerMainConfigs();
        Vouchers.getInstance().getVoucherManager().getRegisteredVouchers().clear();
        Vouchers.getInstance().getVoucherManager().registerAllVouchers();
        Vouchers.getInstance().createAdminMenu();
        sender.sendMessage(Utils.c(Utils.getMessage("reload-complete")));
    }

    @Override
    public String getUsage() {
        return "/vouchers reload";
    }
}
