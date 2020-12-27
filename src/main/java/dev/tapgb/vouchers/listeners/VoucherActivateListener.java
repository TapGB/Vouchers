package dev.tapgb.vouchers.listeners;

import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.vouchers.Voucher;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VoucherActivateListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
            if(e.getItem() != null){
                ItemStack itemStack = e.getItem();
                if(Vouchers.getInstance().getVoucherManager().isVoucher(itemStack)){
                    Voucher voucher = Vouchers.getInstance().getVoucherManager().getVoucher(itemStack);
                    voucher.perform(e.getPlayer());
                    if(e.getItem().getAmount() == 1){
                        e.getPlayer().getInventory().removeItem(e.getItem());
                    }else{
                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                    }
                }
            }
        }
    }

}
