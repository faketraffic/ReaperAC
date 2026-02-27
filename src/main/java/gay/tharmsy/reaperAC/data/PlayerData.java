package gay.tharmsy.reaperAC.data;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckManager;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private final Player player;
    private final List<Check> checks;

    private boolean alertsEnabled = true;

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.checks = CheckManager.loadChecks(this);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public boolean isAlertsEnabled() {
        return alertsEnabled;
    }

    public void setAlertsEnabled(boolean alertsEnabled) {
        this.alertsEnabled = alertsEnabled;
    }
}
