package gay.tharmsy.reaperAC.check.impl.combat;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckInfo;
import gay.tharmsy.reaperAC.check.CheckType;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

@CheckInfo(name = "KillAura", type = "A", category = CheckType.COMBAT, maxVl = 15)
public class KillAuraA extends Check {

    public KillAuraA(PlayerData data) {
        super(data);
    }

    @Override
    public void onAttack(EntityDamageByEntityEvent event) {
        Player player = data.getPlayer();
        Entity target = event.getEntity();

        Vector playerDirection = player.getLocation().getDirection();
        Vector directionToTarget = target.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();

        double dotProduct = playerDirection.dot(directionToTarget);

        if (dotProduct < 0.8) {
            flag("dot: " + dotProduct);
        } else {
            debug("dot: " + dotProduct);
        }
    }
}
