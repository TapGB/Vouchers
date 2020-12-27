package dev.tapgb.vouchers.listeners;

import dev.tapgb.vouchers.menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getPlayer() instanceof Player) {
            Player player = (Player) e.getPlayer();
            if (Menu.isInMenu(player)) {
                Menu.removePlayer(player);
            }
        }
    }

}
