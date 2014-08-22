/**
* This file is created by wasliebob.
* Created on: 12 aug. 2014, at 18:48:04
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.world.structures.components;

import java.util.List;
import java.util.Random;

import modularmachines.main.init.MMBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class ComponentVillageEngineer extends StructureVillagePieces.House1{
	public ComponentVillageEngineer() {}
	
	public ComponentVillageEngineer(StructureVillagePieces.Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode){
		this();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;
	}
	
  	@SuppressWarnings("rawtypes")
	public static ComponentVillageEngineer buildComponent(StructureVillagePieces.Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5){
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 6, 6, coordBaseMode);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentVillageEngineer(villagePiece, p5, random, structureboundingbox, coordBaseMode) : null;
  	}
  	
	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box){
		if(this.field_143015_k < 0){
			this.field_143015_k = this.getAverageGroundLevel(world, box);

			if(this.field_143015_k < 0){
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
		}

		this.fillWithBlocks(world, box, 1, 1, 1, 7, 5, 4, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(world, box, 0, 0, 0, 8, 0, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 5, 0, 8, 5, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 6, 1, 8, 6, 4, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 7, 2, 8, 7, 3, MMBlocks.brick_black, MMBlocks.brick_black, false);
        int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
        int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
        int k;
        int l;

        for(k = -1; k <= 2; ++k){
        	for (l = 0; l <= 8; ++l){
        		this.placeBlockAtCurrentPosition(world, MMBlocks.stairs_brick_black, i, l, 6 + k, k, box);
                this.placeBlockAtCurrentPosition(world, MMBlocks.stairs_brick_black, j, l, 6 + k, 5 - k, box);
        	}
        }
        
        this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 1, 1, 5, 8, 1, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 8, 1, 0, 8, 1, 4, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 2, 1, 0, 7, 1, 0, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 2, 0, 0, 4, 0, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 2, 5, 0, 4, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 8, 2, 5, 8, 4, 5, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 8, 2, 0, 8, 4, 0, MMBlocks.brick_black, MMBlocks.brick_black, false);
        this.fillWithBlocks(world, box, 0, 2, 1, 0, 4, 4, MMBlocks.brick_red, MMBlocks.brick_red, false);
        this.fillWithBlocks(world, box, 1, 2, 5, 7, 4, 5, MMBlocks.brick_red, MMBlocks.brick_red, false);
        this.fillWithBlocks(world, box, 8, 2, 1, 8, 4, 4, MMBlocks.brick_red, MMBlocks.brick_red, false);
        this.fillWithBlocks(world, box, 1, 2, 0, 7, 4, 0, MMBlocks.brick_red, MMBlocks.brick_red, false);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 4, 2, 0, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 5, 2, 0, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 6, 2, 0, box);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 4, 3, 0, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 5, 3, 0, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 6, 3, 0, box);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 0, 2, 2, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 0, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 0, 3, 2, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 0, 3, 3, box);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 8, 2, 2, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 8, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 8, 3, 2, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 8, 3, 3, box);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 2, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 3, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 5, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 6, 2, 5, box);

        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 2, 3, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 3, 3, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 5, 3, 5, box);
        this.placeBlockAtCurrentPosition(world, MMBlocks.glass_default, 1, 6, 3, 5, box);
        
        this.placeBlockAtCurrentPosition(world, MMBlocks.core_machine, 0, 1, 1, 4, box);

        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 1, 0, box);
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 0, box);
        this.placeDoorAtCurrentPosition(world, box, rand, 1, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

        if(this.getBlockAtCurrentPosition(world, 1, 0, -1, box).getMaterial() == Material.air && this.getBlockAtCurrentPosition(world, 1, -1, -1, box).getMaterial() != Material.air){
        	this.placeBlockAtCurrentPosition(world, MMBlocks.stairs_brick_black, this.getMetadataWithOffset(MMBlocks.stairs_brick_black, 3), 1, 0, -1, box);
        }

        for (l = 0; l < 6; ++l){
        	for(int i1 = 0; i1 < 9; ++i1){
        		this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, box);
                 this.func_151554_b(world, MMBlocks.stone_black, 0, i1, -1, l, box);
        	}
        }

        this.spawnVillagers(world, box, 2, 1, 2, 1);
        return true;
	}
}