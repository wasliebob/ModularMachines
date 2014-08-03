/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.upgrades.interacting;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.upgrades.IInteractingAction;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UpgradePlace implements IInteractingAction, IGuided{

	@Override
	public void onUpdate(TileInteracting ti) {
		if(getContainer(ti) != null){
			IInventory inv = getContainer(ti);
			tryPlacingBlock(inv, ti);
		}
	}
	
	public void tryPlacingBlock(IInventory inv, TileInteracting ti){
		if(getBlockFromInventory(inv, ti) != 9999999){
			World world = ti.getWorldObj();
			int x = ti.xCoord;
			int y = ti.yCoord;
			int z = ti.zCoord;
			int slot = getBlockFromInventory(inv, ti);
			
			ItemStack placeStack = inv.getStackInSlot(slot);
			if(placeStack != null){
				Block block = Block.getBlockFromItem(placeStack.getItem());
				int meta = placeStack.getItemDamage();
			
				Block oBlock = DirectionHelper.getBlock(world, x, y, z, ti.output);
				if(oBlock.isAir(world, DirectionHelper.getX(x, ti.output), DirectionHelper.getY(y, ti.output), DirectionHelper.getY(y, ti.output))){
					world.setBlock(DirectionHelper.getX(x, ti.output), DirectionHelper.getY(y, ti.output), DirectionHelper.getZ(z, ti.output), block, meta, 2);
				
					if(placeStack.stackSize > 1)
						placeStack.stackSize--;
					else
						inv.setInventorySlotContents(slot, null);
				}
			}
		}
	}
	
	public int getBlockFromInventory(IInventory inv, TileInteracting ti){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null && Block.getBlockFromItem(stack.getItem()) != null && stack.getItem() instanceof ItemBlock){
				return i;
			}
		}
		return 9999999;
	}

	public IInventory getContainer(TileInteracting ti){
		TileEntity tile = DirectionHelper.getTileEntity(ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, ti.input);
		if(tile != null && tile instanceof IInventory)
			return (IInventory)tile;
		else return null;
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
		return "Upgrade: Place";
	}
}