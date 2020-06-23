package com.scrappymc.slimechunk.listener;

import com.scrappymc.slimechunk.config.Config;
import com.scrappymc.slimechunk.util.ChunkInfo;
import com.scrappymc.slimechunk.util.PlayerInv;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack heldItems = player.getInventory().getItemInMainHand();
        //Only works if player is holding detection block(s), punching a block using main hand, and in a valid world
        if (event.getHand() == EquipmentSlot.HAND
                && event.getAction() == Action.LEFT_CLICK_BLOCK
                && heldItems.getType() == Config.getDetectionItem()
                && (Config.isValidWorld(world))) {

            if (player.hasPermission("slimechunk.find")) {
                event.setCancelled(true);

                if (Config.isItemConsumed()) {
                    player.getInventory().setItemInMainHand(PlayerInv.decrementItems(heldItems));
                }

                if (ChunkInfo.isSlimeChunk(event.getClickedBlock())) {
                    player.sendMessage(ChatColor.GREEN + "This block is in a slime chunk! Slimes will spawn here below level 40.");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                } else {
                    player.sendMessage(ChatColor.RED + "This block is not in a slime chunk.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to find slime chunks!");
            }
        }
    }
}
