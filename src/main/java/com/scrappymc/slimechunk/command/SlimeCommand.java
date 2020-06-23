package com.scrappymc.slimechunk.command;

import com.scrappymc.slimechunk.SlimeChunk;
import com.scrappymc.slimechunk.config.Config;
import com.scrappymc.slimechunk.util.ChunkInfo;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SlimeCommand implements TabExecutor {
    private static SlimeChunk plugin = SlimeChunk.getInstance();

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if ((args.length < 1 || !sender.hasPermission("slimechunk.admin"))) {
                sender.sendMessage(ChatColor.GREEN + "SlimeChunk version " + SlimeChunk.version + " by Hypermoron");
        } else if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
        } else if (args[0].equalsIgnoreCase("check")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to perform this action.");
            } else {
                Player player = (Player) sender;
                Location location = player.getLocation();
                Chunk chunk = location.getChunk();
                if (ChunkInfo.isSlimeChunk(chunk)) {
                    player.sendMessage(ChatColor.GREEN + "You are standing in a slime chunk! Slimes will spawn here below level 40.");
                    player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not standing in a slime chunk.");
                }

            }
        } else {
            sender.sendMessage(ChatColor.RED + "Command not recognized.");
        }
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("slimechunk.admin")) return null;
        if (args.length != 1) return null;
        return Arrays.asList("check", "reload");
    }
}
