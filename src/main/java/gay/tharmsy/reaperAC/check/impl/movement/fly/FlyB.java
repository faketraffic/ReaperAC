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
        type = "B",
        category = CheckType.MOVEMENT,
        description = "BauiscB",
        maxVl = 10
)
public class FlyB extends Check {

    private int hoverTicks = 0;

    public FlyB(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getAllowFlight() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        if (player.isGliding() || player.isSwimming() || player.isInsideVehicle()) {
            return;
        }

        Location to = event.getTo();
        Location from = event.getFrom();
        
        if (to == null) return;

        double deltaY = Math.abs(to.getY() - from.getY());

        boolean serverGround = !from.clone().subtract(0, 0.1, 0).getBlock().getType().isAir() ||
                               !to.clone().subtract(0, 0.1, 0).getBlock().getType().isAir() ||
                               !from.getBlock().getType().isAir();

        if (!serverGround && !player.isOnGround()) {
            if (deltaY < 0.05) {
                hoverTicks++;
                if (hoverTicks > 5) {
                    flag("Ticks: " + hoverTicks);
                }
            } else {
                hoverTicks = 0;
            }
        } else {
            hoverTicks = 0;
        }
    }
}
