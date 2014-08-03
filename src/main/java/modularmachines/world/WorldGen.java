/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.world;

import java.util.Random;

import modularmachines.main.init.MMBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		if(world.provider.terrainType.equals(WorldType.FLAT))
			return;
		if(!world.provider.isSurfaceWorld())
			return;
		
        for (int i = 0; i < 20; i++){
            int x = chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(world.getHeight() / 5);
            int z = chunkZ * 16 + random.nextInt(16);
            Block block = world.getBlock(x, y, z);
        	if(block != null && (block.isReplaceableOreGen(world, x, y, z, Blocks.stone))){
        		world.setBlock(x, y, z, MMBlocks.ore_copper);
        	}
        }
        
        for (int i = 0; i < 16; i++){
            int x = chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(world.getHeight() / 5);
            int z = chunkZ * 16 + random.nextInt(16);
            Block block = world.getBlock(x, y, z);
        	if(block != null && (block.isReplaceableOreGen(world, x, y, z, Blocks.stone))){
        		world.setBlock(x, y, z, MMBlocks.ore_tin);
        	}
        }
	}
}