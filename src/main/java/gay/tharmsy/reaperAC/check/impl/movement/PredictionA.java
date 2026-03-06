package gay.tharmsy.reaperAC.check.impl.movement;

import gay.tharmsy.reaperAC.check.Check;
import gay.tharmsy.reaperAC.check.CheckInfo;
import gay.tharmsy.reaperAC.check.CheckType;
import gay.tharmsy.reaperAC.data.PlayerData;
import gay.tharmsy.reaperAC.util.PredictionEngine;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@CheckInfo(name = "Prediction", type = "A", category = CheckType.MOVEMENT, maxVl = 10)
public class PredictionA extends Check {

    public PredictionA(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Player player = data.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE || player.isFlying() || player.isGliding()) return;

        Location from = event.getFrom();
        Location to = event.getTo();

        PredictionEngine engine = data.getPredictionEngine();

        // Predict from previous ticks
        double[] predicted = engine.predictNext();

        double actualMotionX = to.getX() - from.getX();
        double actualMotionY = to.getY() - from.getY();
        double actualMotionZ = to.getZ() - from.getZ();

        double diffX = Math.abs(actualMotionX - predicted[0]);
        double diffY = Math.abs(actualMotionY - predicted[1]);
        double diffZ = Math.abs(actualMotionZ - predicted[2]);

        // Very basic threshold
        double maxDiff = 0.5;

        // Ignore if jumping, because we aren't simulating jump yet
        if (actualMotionY > 0 && from.getY() % 1 == 0 && to.getY() > from.getY()) {
            engine.update(from, to);
            return;
        }

        if (diffX > maxDiff || diffY > maxDiff || diffZ > maxDiff) {
            flag("dx: " + diffX + " dy: " + diffY + " dz: " + diffZ);
        } else {
            debug("dx: " + diffX + " dy: " + diffY + " dz: " + diffZ);
        }

        engine.update(from, to);
    }
}
