package dev.tapgb.vouchers.files;

import dev.tapgb.vouchers.Vouchers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class ConfigManager {

    private final Vouchers plugin;

    private final String name;
    private final File path;
    private final String folder;
    private File file;
    private YamlConfiguration config;
    private final ConsoleCommandSender console;
    private boolean playerConfig = false;

    public void setPlayerConfig(boolean playerConfig) {
        this.playerConfig = playerConfig;
    }

    public ConfigManager(String name, File path, String folder, Vouchers plugin) {
        this.name = name;
        this.path = path;
        this.folder = folder;
        this.plugin = plugin;
        this.console = Bukkit.getServer().getConsoleSender();
    }

    public void setup() {
        if (this.folder == null) {
            this.file = new File(this.path, this.name);
        } else {
            this.file = new File(this.path + File.separator + folder, this.name);
        }
        if (!this.file.exists()) {
            if (playerConfig) {
                try {
                    file.createNewFile();
                } catch (IOException ignored) {
                }
            } else {
                if (folder != null) {
                    plugin.saveResource(folder + File.separator + name, false);
                } else {
                    plugin.saveResource(name, false);
                }
            }
            this.config = YamlConfiguration.loadConfiguration(this.file);
            if (playerConfig) {
                setupPlayerDefaults();
                saveConfig();
            }
        } else {
            this.loadConfig();
        }
    }

    public void loadConfig() {
        if (this.file.exists()) {
            if (this.folder == null) {
                this.file = new File(this.path, this.name);
            } else {
                this.file = new File(this.path + File.separator + folder, this.name);
            }
            this.config = YamlConfiguration.loadConfiguration(this.file);
        }
    }

    public void saveConfig() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
            console.sendMessage(this.name + ChatColor.RED + " could not be saved!");
        }
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setupPlayerDefaults(){
        config.createSection("winnings");
    }


}
