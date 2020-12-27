package dev.tapgb.vouchers.items;

import org.bukkit.inventory.ItemStack;

public abstract class MenuItem {

    public abstract ItemStack toItemStack();

    public abstract int getSlot();

}
