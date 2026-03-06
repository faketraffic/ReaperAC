package gay.tharmsy.reaperAC.check;

import gay.tharmsy.reaperAC.ReaperAC;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.Bukkit;

public abstract class Check {

    protected final PlayerData data;
    
    private final String name;
    private final String type;
    private final CheckType category;
    private final int maxVl;

    private int violations;

    public Check(PlayerData data) {
        this.data = data;

        if (this.getClass().isAnnotationPresent(CheckInfo.class)) {
            CheckInfo info = this.getClass().getAnnotation(CheckInfo.class);
            this.name = info.name();
            this.type = info.type();
            this.category = info.category();
            this.maxVl = info.maxVl();
        } else {
            this.name = "Unknown";
            this.type = "A";
            this.category = CheckType.PLAYER;
            this.maxVl = 20;
        }
    }

    public void flag(String info) {
        violations++;
        ReaperAC.getInstance().getAlertManager().handleFlag(data, this, info);
    }
    
    public void flag() {
        flag("");
    }

    public void debug(String info) {
        ReaperAC.getInstance().getAlertManager().handleVerbose(data, this, info);
    }

    public void onMove(org.bukkit.event.player.PlayerMoveEvent event) {
        // To be overridden by implementations
    }

    public void onAttack(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        // To be overridden by implementations
    }

    public void setViolations(int violations) {
        this.violations = Math.max(0, violations);
    }

    public PlayerData getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public CheckType getCategory() {
        return category;
    }

    public int getMaxVl() {
        return maxVl;
    }

    public int getViolations() {
        return violations;
    }
}
