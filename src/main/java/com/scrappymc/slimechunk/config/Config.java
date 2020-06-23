package com.scrappymc.slimechunk.config;

import com.scrappymc.slimechunk.SlimeChunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;


public class Config {
    private static FileConfiguration getConfig() {
        return SlimeChunk.getInstance().getConfig();
    }
    public static Material getDetectionItem() {
        Material material = Material.getMaterial(getConfig().getString("detection-item"));
        if (material == null) {
            return Material.SLIME_BALL;
        } else if (material.isItem()) {
            return material;
        }
        return Material.SLIME_BALL;
    }

    public static boolean isItemConsumed() {
        return getConfig().getBoolean("consume-item");
    }

    public static boolean isValidWorld(World world) {
        return (getConfig().getStringList("whitelisted-worlds").contains(world.getName())
                ^ getConfig().getBoolean("blacklist-mode"));
    }

}
