package gay.tharmsy.reaperAC.check.impl.movement;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckInfo;
import gay.tharmsy.reaperAC.check.CheckType;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@CheckInfo(name = "Speed", type = "A", category = CheckType.MOVEMENT, maxVl = 20)
public class SpeedA extends Check {

    public SpeedA(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Player player = data.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE || player.isFlying() || player.isGliding()) return;

        Location from = event.getFrom();
        Location to = event.getTo();

        double deltaX = to.getX() - from.getX();
        double deltaZ = to.getZ() - from.getZ();

        double distSq = deltaX * deltaX + deltaZ * deltaZ;

        // Base max speed. We use a somewhat lenient value since we don't have full 1:1 speed handling yet
        double limit = player.isOnGround() ? 0.38 : 0.35;
        limit = limit * limit;

        if (distSq > limit) {
            flag("distSq: " + distSq);
        } else {
            debug("distSq: " + distSq);
        }
    }
}
