package dev.tapgb.vouchers;

import dev.tapgb.vouchers.commands.CmdRoot;
import dev.tapgb.vouchers.files.ConfigManager;
import dev.tapgb.vouchers.items.MenuItem;
import dev.tapgb.vouchers.listeners.InventoryClickListener;
import dev.tapgb.vouchers.listeners.InventoryCloseListener;
import dev.tapgb.vouchers.listeners.VoucherActivateListener;
import dev.tapgb.vouchers.menus.Menu;
import dev.tapgb.vouchers.menus.MenuManager;
import dev.tapgb.vouchers.vouchers.Voucher;
import dev.tapgb.vouchers.vouchers.VoucherManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class Vouchers extends JavaPlugin {

    private static Vouchers instance;

    public static Vouchers getInstance(){
        return instance;
    }

    private final HashMap<String, ConfigManager> configManagers = new HashMap<>();

    public ConfigManager getConfigManager(String config) {
        return configManagers.get(config);
    }

    private MenuManager menuManager;

    public MenuManager getMenuManager() {
        return menuManager;
    }

    private VoucherManager voucherManager;

    public VoucherManager getVoucherManager(){
        return voucherManager;
    }

    public void registerMainConfigs() {
        List<String> defaultConfigs = new ArrayList<>(Arrays.asList("config", "messages"));
        for (String config : defaultConfigs) {
            ConfigManager configManager = new ConfigManager(config + ".yml", this.getDataFolder(), null, this);
            configManager.setup();
            configManagers.put(config, configManager);
        }
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void sendConsole(String msg){
        getServer().getConsoleSender().sendMessage(Utils.c(msg));
    }

    @Override
    public void onEnable() {
        instance = this;
        registerMainConfigs();
        registerEvents(new VoucherActivateListener(), new InventoryCloseListener(), new InventoryClickListener());
        this.voucherManager = new VoucherManager();
        this.menuManager = new MenuManager();
        getCommand("vouchers").setExecutor(new CmdRoot());
        createAdminMenu();
    }

    private Menu adminMenu;

    public Menu getAdminMenu(){
        return adminMenu;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void createAdminMenu(){
        List<MenuItem> menuItemList = new ArrayList<>();
        int i = 1;
        for(Voucher voucher: getVoucherManager().getAllVouchers()){
            int slot = i;
            MenuItem menuItem = new MenuItem() {
                @Override
                public ItemStack toItemStack() {
                    return voucher.toItemStack();
                }

                @Override
                public int getSlot() {
                    return slot;
                }
            };
            i++;
            menuItemList.add(menuItem);
        }
        MenuItem[] menuItemArray = new MenuItem[menuItemList.size()];
        menuItemArray = menuItemList.toArray(menuItemArray);
        this.adminMenu = getMenuManager().createMenu(Utils.c(getConfigManager("config").getConfig().getString("menus.admin-menu.name")), "admin-menu", getInventorySize(menuItemList.size()), menuItemArray);
    }

    private int getInventorySize(int max) {
        if (max <= 0) return 9;
        int quotient = (int)Math.ceil(max / 9.0);
        return quotient > 5 ? 54: quotient * 9;
    }

}
