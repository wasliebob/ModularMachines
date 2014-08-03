/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.upgrades.interacting;

import java.util.ArrayList;
import java.util.Random;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.upgrades.IInteractingAction;
import modularmachines.main.MM;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UpgradeBreak implements IInteractingAction, IGuided{

	@Override
	public void onUpdate(TileInteracting ti){
		World world = ti.getWorldObj();
		int x = ti.xCoord;
		int y = ti.yCoord;
		int z = ti.zCoord;
		Block input = DirectionHelper.getBlock(world, x, y, z, ti.input);
		int meta = DirectionHelper.getMeta(world, x, y, z, ti.input);
		if(input != null && !input.isAir(world, x, y, z) && !input.hasTileEntity(0)){
			int iX = DirectionHelper.getX(x, ti.input);
			int iY = DirectionHelper.getY(y, ti.input);
			int iZ = DirectionHelper.getZ(z, ti.input);
			
			if(!input.isAir(world, iX, iY, iZ) && input != Blocks.bedrock && input != Blocks.bed && !input.hasTileEntity(meta)){
				ArrayList<ItemStack> filter = new ArrayList<ItemStack>();
				for(ItemStack f : ti.filter){
					if(f != null)
						filter.add(f);
				}
				if(filter.isEmpty() && canHarvest(ti, world.getBlock(iX, iY, iZ), world, iX, iY, iZ)){
					if(ti.output == null || (ti.output != null && getContainer(ti) == null)){
						for(ItemStack drop : input.getDrops(world, iX, iY, iZ, meta, 0))
							dropItem(drop, world, iX, iY, iZ);
						world.setBlock(iX, iY, iZ, Blocks.air);
					}else if(ti.output != null && getContainer(ti) != null){
						for(ItemStack drop : input.getDrops(world, iX, iY, iZ, meta, 0))
							putInContainer(ti, drop);
						world.setBlock(iX, iY, iZ, Blocks.air);
					}
				}else if(!filter.isEmpty() && canHarvest(ti, world.getBlock(iX, iY, iZ), world, iX, iY, iZ)){
					ArrayList<Block> items = new ArrayList<Block>();
					for(ItemStack s : ti.filter){
						if(s != null){
							items.add(Block.getBlockFromItem(s.getItem()));
						}
					}
					if(items.contains(input)){
						if(ti.output == null || (ti.output != null && getContainer(ti) == null)){
							for(ItemStack drop : input.getDrops(world, iX, iY, iZ, meta, 0))
								dropItem(drop, world, iX, iY, iZ);
							world.setBlock(iX, iY, iZ, Blocks.air);
						}else if(ti.output != null && getContainer(ti) != null){
							for(ItemStack drop : input.getDrops(world, iX, iY, iZ, meta, 0))
								putInContainer(ti, drop);
							world.setBlock(iX, iY, iZ, Blocks.air);
						}
					}
				}
			}
		}
	}
	
	public boolean canHarvest(TileInteracting ti, Block block, World world, int x, int y, int z){
		if(block != null && block instanceof IGrowable && !((IGrowable)block).func_149851_a(world, x, y, z, world.isRemote))
			return true;
		else if(block != null && !(block instanceof IGrowable) && world.getBlockMetadata(x, y, z) == ti.meta)
			return true;
		else if(block != null && !(block instanceof IGrowable) && ti.meta == -1)
			return true;
		return false;
	}

	public void putInContainer(TileInteracting ti, ItemStack drop){
		if(getContainer(ti) != null){
			IInventory inv = getContainer(ti);					
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null && stack.getItem() == drop.getItem() && stack.getItemDamage() == drop.getItemDamage() && stack.stackSize + drop.stackSize <= inv.getInventoryStackLimit()){
					int size = stack.stackSize + drop.stackSize;
					inv.setInventorySlotContents(i, new ItemStack(drop.getItem(), size, drop.getItemDamage()));
					return;
				}else if(stack == null){
					inv.setInventorySlotContents(i, drop);
					return;
				}
			}
		}
	}
	
	public IInventory getContainer(TileInteracting ti){
		TileEntity tile = DirectionHelper.getTileEntity(ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, ti.output);
		if(tile != null && tile instanceof IInventory)
			return (IInventory)tile;
		else return null;
	}
	
	public void dropItem(ItemStack stack, World world, int x, int y, int z){
		Random rand = new Random();
    	ItemStack drop = stack;
		float rx = rand.nextFloat() * 0.8F + 0.1F;
    	float ry = rand.nextFloat() * 0.8F + 0.1F;
    	float rz = rand.nextFloat() * 0.8F + 0.1F;
    	
        EntityItem ei = new EntityItem(world, x + rx, y + ry, z + rz, drop);
        float factor = 0.05F;
        ei.motionX = rand.nextGaussian() * factor;
        ei.motionY = rand.nextGaussian() * factor + 0.2F;
        ei.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(ei);
	}

	@Override
	public boolean hasRequired(TileInteracting ti) {
		return ti.input != null;
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
	public void onActivateWithProgrammer(TileInteracting ti, EntityPlayer player){
		player.openGui(MM.instance, 4, ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord);
	}

	@Override
	public String getKey() {
		return "Upgrade: Break";
	}
}