package gay.tharmsy.reaperAC.check;

import gay.tharmsy.reaperAC.check.impl.combat.KillAuraA;
import gay.tharmsy.reaperAC.check.impl.combat.ReachA;
import gay.tharmsy.reaperAC.check.impl.movement.PredictionA;
import gay.tharmsy.reaperAC.check.impl.movement.SpeedA;
import gay.tharmsy.reaperAC.check.impl.movement.fly.FlyA;
import gay.tharmsy.reaperAC.check.impl.movement.fly.FlyB;
import gay.tharmsy.reaperAC.data.PlayerData;
import java.util.ArrayList;
import java.util.List;

public class CheckManager {

    public static List<Check> loadChecks(PlayerData data) {
        List<Check> checks = new ArrayList<>();
        
        checks.add(new FlyA(data));
        checks.add(new FlyB(data));
        
        checks.add(new KillAuraA(data));
        checks.add(new ReachA(data));

        checks.add(new SpeedA(data));
        checks.add(new PredictionA(data));

        return checks;
    }
}
