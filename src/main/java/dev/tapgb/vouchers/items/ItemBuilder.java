package dev.tapgb.vouchers.items;


import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.files.ConfigManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    public static ItemStack buildItem(ConfigManager configManager, String path){
        ItemStack item;
        if(configManager.getConfig().getString(path + ".id").contains(":")){
            String[] arrayOfID = configManager.getConfig().getString(path + ".id").split(":");
            if (Utils.isInteger(arrayOfID[0])) {
                item = new ItemStack(Material.getMaterial(Integer.parseInt(arrayOfID[0])), 1, (short) Integer.parseInt(arrayOfID[1]));
            }else {
                item = new ItemStack(Material.getMaterial(arrayOfID[0]), 1, (short) Integer.parseInt(arrayOfID[1]));
            }
        }else{
            String id = configManager.getConfig().getString(path + ".id");
            if(Utils.isInteger(id)) {
                item = new ItemStack(Material.getMaterial(Integer.parseInt(id)), 1);
            }else{
                item = new ItemStack(Material.getMaterial(id));
            }
        }
        ItemMeta im = item.getItemMeta();
        if(configManager.getConfig().contains(path +".name")) {
            im.setDisplayName(Utils.c(configManager.getConfig().getString(path + ".name")));
        }
        if(configManager.getConfig().contains(path +".lore")) {
            List<String> loreLines = new ArrayList<>();
            for (String loreLine : configManager.getConfig().getStringList(path + ".lore")) {
                loreLines.add(Utils.c(loreLine));
            }
            im.setLore(loreLines);
        }
        item.setItemMeta(im);
        return item;
    }

}
