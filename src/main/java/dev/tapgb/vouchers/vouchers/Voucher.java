package dev.tapgb.vouchers.vouchers;

import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Voucher {

    public abstract String getId();

    private ItemStack itemStack;

    public Voucher(){
        this.itemStack = ItemBuilder.buildItem(Vouchers.getInstance().getConfigManager("config"), "vouchers." + getId());
    }

    public ItemStack toItemStack(){
        return itemStack;
    }

    public void perform(Player player){
        for(String command: Vouchers.getInstance().getConfigManager("config").getConfig().getStringList("vouchers." + getId() + ".commands")){
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
        }
    }

}
