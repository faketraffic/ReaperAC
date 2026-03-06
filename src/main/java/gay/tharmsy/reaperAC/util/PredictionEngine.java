package gay.tharmsy.reaperAC.util;

import gay.tharmsy.reaperAC.data.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PredictionEngine {

    private final PlayerData data;

    private double lastX;
    private double lastY;
    private double lastZ;

    private double motionX;
    private double motionY;
    private double motionZ;

    public PredictionEngine(PlayerData data) {
        this.data = data;
    }

    public void update(Location from, Location to) {
        this.motionX = to.getX() - from.getX();
        this.motionY = to.getY() - from.getY();
        this.motionZ = to.getZ() - from.getZ();

        this.lastX = from.getX();
        this.lastY = from.getY();
        this.lastZ = from.getZ();
    }

    public double[] predictNext() {
        Player player = data.getPlayer();
        boolean isOnGround = player.isOnGround();

        double friction = isOnGround ? 0.6 : 0.91; // Simplified friction
        double gravity = 0.08;

        double predictedMotionX = this.motionX * friction;
        double predictedMotionY = (this.motionY - gravity) * 0.98; // Drag in air
        double predictedMotionZ = this.motionZ * friction;

        if (Math.abs(predictedMotionX) < 0.005) predictedMotionX = 0;
        if (Math.abs(predictedMotionY) < 0.005) predictedMotionY = 0;
        if (Math.abs(predictedMotionZ) < 0.005) predictedMotionZ = 0;

        return new double[]{predictedMotionX, predictedMotionY, predictedMotionZ};
    }
}
