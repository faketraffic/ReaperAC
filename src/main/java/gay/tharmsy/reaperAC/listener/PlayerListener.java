package gay.tharmsy.reaperAC.listener;

import gay.tharmsy.reaperAC.ReaperAC;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final ReaperAC plugin;

    public PlayerListener(ReaperAC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = plugin.getPlayerDataManager().add(player);
        if (player.hasPermission("reaperac.alerts")) {
            data.setAlertsEnabled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getPlayerDataManager().remove(event.getPlayer());
    }

    @EventHandler
    public void onMove(org.bukkit.event.player.PlayerMoveEvent event) {
        PlayerData data = plugin.getPlayerDataManager().get(event.getPlayer());
        if (data != null) {
            data.getChecks().forEach(check -> check.onMove(event));
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            PlayerData data = plugin.getPlayerDataManager().get(player);
            if (data != null) {
                data.getChecks().forEach(check -> check.onAttack(event));
            }
        }
    }
}
