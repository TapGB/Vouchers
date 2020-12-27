package dev.tapgb.vouchers.vouchers;

import dev.tapgb.vouchers.Vouchers;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class VoucherManager {

    private final HashMap<String, Voucher> voucherHashMap = new HashMap<>();

    public HashMap<String, Voucher> getRegisteredVouchers(){
        return voucherHashMap;
    }

    public Voucher getVoucher(String id){
        return voucherHashMap.get(id);
    }

    public boolean isVoucher(String id){
        return getVoucher(id) != null;
    }

    public Collection<Voucher> getAllVouchers(){
        return voucherHashMap.values();
    }

    public VoucherManager(){
        registerAllVouchers();
    }

    public void registerAllVouchers(){
        for(String id: Vouchers.getInstance().getConfigManager("config").getConfig().getConfigurationSection("vouchers").getKeys(false)){
            registerVoucher(id);
        }
    }

    public void registerVoucher(String id){
        Voucher voucher = new Voucher() {
            @Override
            public String getId() {
                return id;
            }
        };
        voucherHashMap.put(id.toLowerCase(), voucher);
    }

    public boolean isVoucher(ItemStack itemStack){
        return getVoucher(itemStack) != null;
    }

    public Voucher getVoucher(ItemStack itemStack){
        for(Voucher voucher: getAllVouchers()){
            if(voucher.toItemStack().isSimilar(itemStack)){
                return voucher;
            }
        }
        return null;
    }

}
