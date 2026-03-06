package gay.tharmsy.reaperAC.check.impl.combat;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckInfo;
import gay.tharmsy.reaperAC.check.CheckType;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@CheckInfo(name = "Reach", type = "A", category = CheckType.COMBAT, maxVl = 10)
public class ReachA extends Check {

    public ReachA(PlayerData data) {
        super(data);
    }

    @Override
    public void onAttack(EntityDamageByEntityEvent event) {
        Player player = data.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        Entity target = event.getEntity();
        double distance = player.getLocation().distance(target.getLocation());

        // Simple reach check. Accounts for some lag/hitboxes roughly
        double maxDistance = 4.2;

        if (distance > maxDistance) {
            flag("dist: " + distance);
        } else {
            debug("dist: " + distance);
        }
    }
}
