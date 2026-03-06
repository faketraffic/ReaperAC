package gay.tharmsy.reaperAC.command;

import gay.tharmsy.reaperAC.ReaperAC;
import gay.tharmsy.reaperAC.data.PlayerData;
import gay.tharmsy.reaperAC.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReaperCommand implements CommandExecutor {

    private final ReaperAC plugin;

    public ReaperCommand(ReaperAC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.format("&cYou must be a player to execute this command."));
            return true;
        }

        Player player = (Player) sender;
        PlayerData data = plugin.getPlayerDataManager().get(player);

        if (data == null) {
            player.sendMessage(CC.format("&cFailed to load your player data."));
            return true;
        }

        if (command.getName().equalsIgnoreCase("alerts")) {
            if (!player.hasPermission("reaperac.alerts")) {
                player.sendMessage(CC.format("&cNo permission."));
                return true;
            }
            data.setAlertsEnabled(!data.isAlertsEnabled());
            player.sendMessage(CC.format("&7Alerts toggled " + (data.isAlertsEnabled() ? "&aON" : "&cOFF")));
            return true;
        }

        if (command.getName().equalsIgnoreCase("verbose")) {
            if (!player.hasPermission("reaperac.verbose")) {
                player.sendMessage(CC.format("&cNo permission."));
                return true;
            }
            data.setVerboseEnabled(!data.isVerboseEnabled());
            player.sendMessage(CC.format("&7Verbose toggled " + (data.isVerboseEnabled() ? "&aON" : "&cOFF")));
            return true;
        }

        if (command.getName().equalsIgnoreCase("reaperac")) {
            if (args.length == 0) {
                player.sendMessage(CC.format("&cUsage: /reaperac <verbose|alerts>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("verbose")) {
                if (!player.hasPermission("reaperac.verbose")) {
                    player.sendMessage(CC.format("&cNo permission."));
                    return true;
                }
                data.setVerboseEnabled(!data.isVerboseEnabled());
                player.sendMessage(CC.format("&7Verbose toggled " + (data.isVerboseEnabled() ? "&aON" : "&cOFF")));
                return true;
            }

            if (args[0].equalsIgnoreCase("alerts")) {
                if (!player.hasPermission("reaperac.alerts")) {
                    player.sendMessage(CC.format("&cNo permission."));
                    return true;
                }
                data.setAlertsEnabled(!data.isAlertsEnabled());
                player.sendMessage(CC.format("&7Alerts toggled " + (data.isAlertsEnabled() ? "&aON" : "&cOFF")));
                return true;
            }

            player.sendMessage(CC.format("&cUsage: /reaperac <verbose|alerts>"));
            return true;
        }

        return false;
    }
}
