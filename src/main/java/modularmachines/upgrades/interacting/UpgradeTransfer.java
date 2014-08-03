/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.upgrades.interacting;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.interfaces.ITransportable;
import modularmachines.api.upgrades.IInteractingAction;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UpgradeTransfer implements IInteractingAction, IGuided{
	@Override
	public void onUpdate(TileInteracting ti){
		World world = ti.getWorldObj();
		int x = ti.xCoord;
		int y = ti.yCoord;
		int z = ti.zCoord;
		Block input = DirectionHelper.getBlock(world, x, y, z, ti.input);
		int meta = DirectionHelper.getMeta(world, x, y, z, ti.input);
		if(input != null && !input.isAir(world, x, y, z) && !input.hasTileEntity(meta)){			
			int oX = DirectionHelper.getX(x, ti.output);
			int oY = DirectionHelper.getY(y, ti.output);
			int oZ = DirectionHelper.getZ(z, ti.output);
			
			int iX = DirectionHelper.getX(x, ti.input);
			int iY = DirectionHelper.getY(y, ti.input);
			int iZ = DirectionHelper.getZ(z, ti.input);
			
			Block output = world.getBlock(oX, oY, oZ);
			
			if(output == null || output.isAir(world, oX, oY, oZ) && output != Blocks.bedrock){
				world.setBlock(oX, oY, oZ, input);
				world.setBlockMetadataWithNotify(oX, oY, oZ, meta, 2);
				world.setBlock(iX, iY, iZ, Blocks.air);
			}
		}else if(input != null && !input.isAir(world, x, y, z)){
			int oX = DirectionHelper.getX(x, ti.output);
			int oY = DirectionHelper.getY(y, ti.output);
			int oZ = DirectionHelper.getZ(z, ti.output);

			int iX = DirectionHelper.getX(x, ti.input);
			int iY = DirectionHelper.getY(y, ti.input);
			int iZ = DirectionHelper.getZ(z, ti.input);
			
			Block output = world.getBlock(oX, oY, oZ);	
			
			TileEntity te = world.getTileEntity(iX, iY, iZ);
			if(te != null && te instanceof ITransportable){
				if(output == null || output.isAir(world, oX, oY, oZ) && output != Blocks.bedrock){
					ITransportable it = (ITransportable)world.getTileEntity(iX, iY, iZ);

					world.setBlock(oX, oY, oZ, input);
					world.setBlockMetadataWithNotify(oX, oY, oZ, meta, 2);					
					
					if(world.getTileEntity(oX, oY, oZ) != null){
						ITransportable ot = (ITransportable)world.getTileEntity(oX, oY, oZ);
						ot.readNBT(it.getTag());
						world.markBlockForUpdate(oX, oY, oZ);
					}
					world.setBlock(iX, iY, iZ, Blocks.air);

				}
			}
		}
	}


	@Override
	public boolean hasRequired(TileInteracting ti) {
		return ti.input != null && ti.output != null;
	}

	@Override
	public void increaseHeat(TileInteracting ti, int heat){}

	@Override
	public void decreaseHeat(TileInteracting ti, int heat){}

	@Override
	public void onActivate(TileInteracting ti, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onActivateClick(TileInteracting ti, EntityPlayer player) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onActivateWithItem(TileInteracting ti, ItemStack held, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onActivateWithProgrammer(TileInteracting ti, EntityPlayer player) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String getKey() {
		return "Upgrade: Transfer";
	}
}