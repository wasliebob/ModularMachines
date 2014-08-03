/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.upgrades.interacting;

import java.util.List;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.upgrades.IInteractingAction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class UpgradeVacuum implements IInteractingAction, IGuided{

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdate(TileInteracting ti) {
		World world = ti.getWorldObj();
		int x = ti.xCoord;
		int y = ti.yCoord;
		int z = ti.zCoord;
		
		double range = 15.0D;
		
		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);
		List<Entity> entities = world.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
		
		for(Entity entity : entities){
			double xPos = entity.posX;
			double yPos = entity.posY;
			double zPos = entity.posZ;
			
			float distance = (float) ((x - xPos) * (x - xPos) + (y - yPos) * (y - yPos) + (z - zPos) * (z - zPos));
	
			if(!world.isRemote && entity instanceof EntityItem && distance <= 15F){
				putInContainer((EntityItem)entity, ti);
			}
		}
	}
	
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity){
				return entity instanceof EntityItem;}};}

	public void putInContainer(EntityItem item, TileInteracting ti){
		if(getContainer(item, ti) != null){
			IInventory inv = getContainer(item, ti);
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null && stack.getItem() == item.getEntityItem().getItem() && stack.getItemDamage() == item.getEntityItem().getItemDamage() && stack.stackSize  + item.getEntityItem().stackSize < inv.getInventoryStackLimit()){
					int size = stack.stackSize + item.getEntityItem().stackSize;
					inv.setInventorySlotContents(i, new ItemStack(stack.getItem(), size, stack.getItemDamage()));
					item.setDead();
					return;
				}else if(stack == null){
					inv.setInventorySlotContents(i, item.getEntityItem());
					item.setDead();
					return;
				}
			}
		}
	}
	
	public IInventory getContainer(EntityItem item, TileInteracting ti){
		TileEntity tile = DirectionHelper.getTileEntity(ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, ti.output);
		if(tile != null && tile instanceof IInventory)
			return (IInventory)tile;
		else return null;
	}

	@Override
	public boolean hasRequired(TileInteracting ti) {
		TileEntity te = DirectionHelper.getTileEntity(ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, ti.output);
		return ti.output != null && te != null && te instanceof IInventory;
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
		return "Upgrade: Vacuum";
	}
}