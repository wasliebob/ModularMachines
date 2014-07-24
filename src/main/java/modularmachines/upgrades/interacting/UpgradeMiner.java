package modularmachines.upgrades.interacting;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.helpers.MiscHelper;
import modularmachines.api.upgrades.IInteractingAction;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UpgradeMiner implements IInteractingAction, IGuided{
	public UpgradeMiner(int need){
		this.need = need;
	}
	int need;
	
	@Override
	public void onUpdate(TileInteracting ti){
		World world = ti.getWorldObj();
		int x = ti.xCoord;
		int y = ti.yCoord;
		int z = ti.zCoord;
		for(int i = 1; i < 255; i++){
			Block block = world.getBlock(x, y - i, z);
			int meta = world.getBlockMetadata(x, y - i, z);
			if(block != null && block != Blocks.air && !block.hasTileEntity(meta)){
				if(block == Blocks.bedrock)
					return;
				
				if(ti.heat.getHeat() < this.need)
					return;
								
				if(!MiscHelper.bannedMiner.contains(block)){
					for(ItemStack drop : block.getDrops(world, x, y - i, z, meta, 0))
						putInContainer(ti, drop);
					world.setBlock(x, y - i, z, Blocks.air);
					this.decreaseHeat(ti, this.need);
				}
			}
		}
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
	


	@Override
	public boolean hasRequired(TileInteracting ti){
		if(ti.output != null){
			TileEntity te = DirectionHelper.getTileEntity(ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, ti.output);
			if(te != null && te instanceof IInventory){
				if(ti.heat.getHeat() >= this.need){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void increaseHeat(TileInteracting ti, int heat){}

	@Override
	public void decreaseHeat(TileInteracting ti, int heat){
		ti.heat.decreaseEnergy(heat);
	}

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
	}

	@Override
	public String getKey() {
		return "Upgrade: Miner";
	}
}