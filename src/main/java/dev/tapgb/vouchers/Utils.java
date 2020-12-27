package dev.tapgb.vouchers;

import org.bukkit.ChatColor;

public class Utils {

    public static String c(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isInteger(String x){
        try{
            Integer.parseInt(x);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static String getMessage(String message){
        return Vouchers.getInstance().getConfigManager("messages").getConfig().getString(message);
    }

}
