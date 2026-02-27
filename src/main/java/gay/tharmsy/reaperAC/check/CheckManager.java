package gay.tharmsy.reaperAC.check;

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
        
        return checks;
    }
}
