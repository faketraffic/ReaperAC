package gay.tharmsy.reaperAC.util;

import org.bukkit.ChatColor;

public final class CC {

    public static String PREFIX = translate("&8[&cReaperAC&8] &7");

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static String format(String message) {
        return translate(PREFIX + message);
    }
}
