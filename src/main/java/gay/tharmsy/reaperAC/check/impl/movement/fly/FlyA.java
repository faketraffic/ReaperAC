package gay.tharmsy.reaperAC.check.impl.movement.fly;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckInfo;
import gay.tharmsy.reaperAC.check.CheckType;
import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@CheckInfo(
        name = "Fly",
        type = "A",
        category = CheckType.MOVEMENT,
        description = "BasicA",
        maxVl = 10
)
public class FlyA extends Check {

    private int upwardAirTicks = 0;
    private double lastDeltaY = 0.0;

    public FlyA(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if (player.getAllowFlight() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        if (player.isGliding() || player.isSwimming() || player.isInsideVehicle() || player.hasPotionEffect(org.bukkit.potion.PotionEffectType.JUMP_BOOST)) {
            return;
        }

        Location to = event.getTo();
        Location from = event.getFrom();
        
        if (to == null) return;

        double deltaY = to.getY() - from.getY();

        boolean serverGround = !from.clone().subtract(0, 0.1, 0).getBlock().getType().isAir() ||
                               !to.clone().subtract(0, 0.1, 0).getBlock().getType().isAir() ||
                               !from.getBlock().getType().isAir();

        String blockName = player.getLocation().getBlock().getType().name();

        if (blockName.contains("LADDER") || blockName.contains("VINE") || blockName.contains("WATER") || 
            blockName.contains("LAVA") || blockName.contains("BUBBLE_COLUMN")) {
            upwardAirTicks = 0;
            return;
        }
        if (player.getLocation().clone().subtract(0, 0.1, 0).getBlock().getType().name().contains("SLIME")) {
            upwardAirTicks = 0;
            return;
        }

        if (deltaY > 0.0 && !serverGround) {
            upwardAirTicks++;
            if (upwardAirTicks > 10) {
                flag("Ticks Ascending: " + upwardAirTicks + " | deltaY: " + String.format("%.3f", deltaY));
            }
            if (upwardAirTicks > 3 && deltaY == lastDeltaY) {
                flag("Constant Ascent: " + String.format("%.3f", deltaY));
            }
        } else {
            upwardAirTicks = 0;
        }
        this.lastDeltaY = deltaY;
    }
}
