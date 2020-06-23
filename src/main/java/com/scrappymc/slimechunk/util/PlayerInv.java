package com.scrappymc.slimechunk.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlayerInv {
    public static ItemStack decrementItems(ItemStack items) {
        if (items.getAmount() > 1) {
            items.setAmount(items.getAmount() - 1);
        } else {
            items = new ItemStack(Material.AIR);
        }
        return items;
    }
}
