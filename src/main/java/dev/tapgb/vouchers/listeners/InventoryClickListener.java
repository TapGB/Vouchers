package dev.tapgb.vouchers.listeners;

import dev.tapgb.vouchers.Vouchers;
import dev.tapgb.vouchers.items.MenuItem;
import dev.tapgb.vouchers.menus.Menu;
import dev.tapgb.vouchers.vouchers.Voucher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null) {
                if (Menu.isInMenu(player)) {
                    if (e.getClickedInventory().equals(Menu.getMenu(player).getInventory())) {
                        if (Menu.getMenu(player).getId().equalsIgnoreCase("admin-menu")) {
                            e.setCancelled(true);
                            int clickedSlot = e.getSlot();
                            MenuItem menuItem = Menu.getMenu(player).getBySlot(clickedSlot);
                            Voucher voucher = Vouchers.getInstance().getVoucherManager().getVoucher(menuItem.toItemStack());
                            player.getInventory().addItem(voucher.toItemStack());
                        }
                    }
                }
            }
        }
    }

}
