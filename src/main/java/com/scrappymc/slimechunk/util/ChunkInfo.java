package com.scrappymc.slimechunk.util;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

import java.util.Random;

public class ChunkInfo {
    public static boolean isSlimeChunk(Chunk chunk) {
        int x = chunk.getX();
        int z = chunk.getZ();
        long seed = chunk.getWorld().getSeed();
        // Slime chunks use this formula from https://minecraft.gamepedia.com/Slime#.22Slime_chunks.22
        Random rnd = new Random(
                seed +
                        (x * x * 0x4c1906) +
                        (x * 0x5ac0db) +
                        (z * z) * 0x4307a7L +
                        (z * 0x5f24f) ^ 0x3ad8025f
        );
        if (rnd.nextInt(10) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isSlimeChunk(Block block) {
        return isSlimeChunk(block.getWorld().getChunkAt(block));
    }

}