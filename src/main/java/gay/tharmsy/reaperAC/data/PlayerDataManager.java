package gay.tharmsy.reaperAC.data;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataManager {

    private final Map<UUID, PlayerData> playerDataMap = new ConcurrentHashMap<>();

    public PlayerData add(Player player) {
        PlayerData data = new PlayerData(player);
        playerDataMap.put(player.getUniqueId(), data);
        return data;
    }

    public void remove(Player player) {
        playerDataMap.remove(player.getUniqueId());
    }

    public PlayerData get(Player player) {
        return playerDataMap.get(player.getUniqueId());
    }

    public PlayerData get(UUID uuid) {
        return playerDataMap.get(uuid);
    }

    public Collection<PlayerData> getAll() {
        return playerDataMap.values();
    }
}
