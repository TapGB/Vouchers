package dev.tapgb.vouchers.commands.subcommands;

import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.commands.CommandHandler;
import dev.tapgb.vouchers.commands.CommandRequirement;
import dev.tapgb.vouchers.commands.CommandRequirements;
import dev.tapgb.vouchers.menus.Menu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.Arrays;

public class CmdAdmin extends CommandHandler {


    public CmdAdmin(){
        this.aliases = new ArrayList<>(Arrays.asList("admin", "all"));
        this.permission = new Permission("vouchers.admin");
        this.requirements = new CommandRequirements(CommandRequirement.PERMISSION, CommandRequirement.PLAYER);
        this.build();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Menu.setMenu(player, Vouchers.getInstance().getAdminMenu());
        player.sendMessage(Utils.c(Utils.getMessage("admin-menu-opened")));
    }

    @Override
    public String getUsage() {
        return "/vouchers admin";
    }
}
