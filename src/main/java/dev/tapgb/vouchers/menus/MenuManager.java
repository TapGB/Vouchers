package dev.tapgb.vouchers.menus;

import dev.tapgb.vouchers.items.MenuItem;

import java.util.Arrays;

public class MenuManager {

    public Menu createMenu(String name, String id, int size, MenuItem... menuItems){
        Menu menu = new Menu() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public int getSize() {
                return size;
            }

            @Override
            public String getName() {
                return name;
            }
        };
        menu.getMenuItems().addAll(Arrays.asList(menuItems));
        menu.setItems();
        return menu;
    }

}
