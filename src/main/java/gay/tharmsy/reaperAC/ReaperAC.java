package gay.tharmsy.reaperAC;

import gay.tharmsy.reaperAC.data.PlayerDataManager;
import gay.tharmsy.reaperAC.listener.PlayerListener;
import gay.tharmsy.reaperAC.manager.AlertManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReaperAC extends JavaPlugin {

    private static ReaperAC instance;
    private PlayerDataManager playerDataManager;
    private AlertManager alertManager;

    @Override
    public void onEnable() {
        instance = this;
        playerDataManager = new PlayerDataManager();
        alertManager = new AlertManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ReaperAC getInstance() {
        return instance;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public AlertManager getAlertManager() {
        return alertManager;
    }
}
