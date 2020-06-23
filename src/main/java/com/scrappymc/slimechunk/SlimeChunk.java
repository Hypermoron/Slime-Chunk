package com.scrappymc.slimechunk;

import com.scrappymc.slimechunk.command.SlimeCommand;
import com.scrappymc.slimechunk.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimeChunk extends JavaPlugin implements org.bukkit.event.Listener {

    public static final String version = "1.0.0";

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("slimechunk").setExecutor(new SlimeCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public static SlimeChunk getInstance() {
        return getPlugin(SlimeChunk.class);
    }
}
