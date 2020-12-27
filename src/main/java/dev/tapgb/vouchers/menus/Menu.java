package dev.tapgb.vouchers.menus;

import dev.tapgb.vouchers.items.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Menu {

    private static final HashMap<Player, Menu> ACTIVE_MENU = new HashMap<>();

    private List<MenuItem> menuItems = new ArrayList<>();

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public boolean hasNextPage(){
        return nextPage != null;
    }

    public boolean hasPreviousPage(){
        return previousPage != null;
    }

    private Menu previousPage;

    public void setPreviousPage(Menu menu){
        this.previousPage = menu;
    }

    public Menu getPreviousPage(){
        return previousPage;
    }

    private Menu nextPage;

    public void setNextPage(Menu menu){
        this.nextPage = menu;
    }

    public Menu getNextPage(){
        return nextPage;
    }

    public static void setMenu(Player player, Menu menu){
        player.openInventory(menu.getInventory());
        ACTIVE_MENU.put(player, menu);
    }

    public static void closeMenu(Player player){
        player.closeInventory();
    }

    public static void removePlayer(Player player){
        ACTIVE_MENU.remove(player);
    }

    public static Menu getMenu(Player player){
        if (ACTIVE_MENU.containsKey(player)) {
            return ACTIVE_MENU.get(player);
        }
        return null;
    }

    public static boolean isInMenu(Player player){
        return getMenu(player) != null;
    }

    public static boolean isMenuItem(Menu menu, int slot){
        return menu.getBySlot(slot) != null;
    }

    public MenuItem getBySlot(int slot){
        for(MenuItem item: getMenuItems()){
            if(item.getSlot()-1 == slot){
                return item;
            }
        }
        return null;
    }

    private Inventory inventory;

    public Inventory getInventory(){
        return inventory;
    }

    public abstract String getId();

    public abstract int getSize();

    public abstract String getName();

    public void createInventory(){
        this.inventory = Bukkit.createInventory(null, getSize(), getName());
        setItems();
    }

    public void setItems(){
        for(MenuItem menuItem: getMenuItems()){
            setItem(menuItem);
        }
    }

    public Menu(){
        createInventory();
    }

    public void setItem(MenuItem item){
        inventory.setItem(item.getSlot() -1, item.toItemStack());
    }

    public void addItem(MenuItem item){
        getMenuItems().add(item);
        setItems();
    }

}
