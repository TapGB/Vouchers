package dev.tapgb.vouchers.commands;

import dev.tapgb.vouchers.Utils;
import dev.tapgb.vouchers.Vouchers;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandHandler {

    public List<String> aliases;
    public Permission permission;
    public static final List<CommandHandler> COMMANDS = new ArrayList<>();
    public CommandRequirements requirements;
    private String alias;

    public String getAlias(){
        return alias;
    }

    public abstract void perform(CommandSender sender, String[] args);

    public abstract String getUsage();

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            for (CommandHandler command : COMMANDS) {
                if (command.aliases.contains(args[0].toLowerCase())) {
                    command.alias = args[0];
                    for (CommandRequirement requirement : command.requirements.getRequirements()) {
                        if (requirement.equals(CommandRequirement.PLAYER)) {
                            if (!(sender instanceof Player)) {
                                missedRequirement(requirement, sender, command);
                                return false;
                            }
                        } else if (requirement.equals(CommandRequirement.PERMISSION)) {
                            if (!sender.hasPermission(command.permission)) {
                                missedRequirement(requirement, sender, command);
                                return false;
                            }
                        }
                    }
                    command.perform(sender, args);
                }
            }
        } else {
            sender.sendMessage(Utils.c("&c[&7&l!&c] &7Running Vouchers Version: " + Vouchers.getInstance().getDescription().getVersion() + " By TapGB!"));
            sender.sendMessage(Utils.c("&c[&7&l!&c] &7Run /vouchers help for a list of commands!"));
        }
        return true;
    }

    public void build() {
        COMMANDS.add(this);
    }

    private void missedRequirement(CommandRequirement requirement, CommandSender sender, CommandHandler command) {
        switch (requirement) {
            case PLAYER:
                sender.sendMessage(Utils.c(Utils.getMessage("error-players-only")));
                break;
            case PERMISSION:
                sender.sendMessage(Utils.c(Utils.getMessage("error-no-permission").replace("{node}", command.permission.getName())));
                break;
        }
    }

}
