package gay.tharmsy.reaperAC.manager;

import gay.tharmsy.reaperAC.ReaperAC;
import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.data.PlayerData;
import gay.tharmsy.reaperAC.data.PlayerDataManager;
import gay.tharmsy.reaperAC.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AlertManager {

    private final ReaperAC plugin;

    public AlertManager(ReaperAC plugin) {
        this.plugin = plugin;
    }

    public void handleFlag(PlayerData data, Check check, String info) {
        String alertMessage = CC.PREFIX + "&c" + data.getPlayer().getName() + " &7failed &e" + 
                check.getName() + " &7(&e" + check.getType() + "&7)";
        
        if (info != null && !info.isEmpty()) {
            alertMessage += " &7[&f" + info + "&7]";
        }
        
        alertMessage += " &c(x" + check.getViolations() + ")";

        String formatted = CC.translate(alertMessage);

        for (PlayerData pData : plugin.getPlayerDataManager().getAll()) {
            if (pData.isAlertsEnabled() && pData.getPlayer().hasPermission("reaperac.alerts")) {
                pData.getPlayer().sendMessage(formatted);
            }
        }
        Bukkit.getConsoleSender().sendMessage(formatted);

        if (check.getViolations() >= check.getMaxVl()) {
            punish(data, check);
        }
    }

    private void punish(PlayerData data, Check check) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + data.getPlayer().getName() + " You have been kicked by ReaperAC for " + check.getName());
        });
    }
}
