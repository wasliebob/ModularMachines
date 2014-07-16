package modularmachines.upgrades.interacting;

import java.util.Random;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.misc.helpers.MiscHelper;
import modularmachines.api.upgrades.IInteractingAction;
import modularmachines.main.init.MMItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class UpgradeFertilize implements IInteractingAction{
	int need = 10;
	
	@Override
	public void onUpdate(TileInteracting ti){
		World world = ti.getWorldObj();
		int x = ti.xCoord;
		int y = ti.yCoord;
		int z = ti.zCoord;
		if(getFertilizing(ti, world, x, y + ti.up, z) != null){
			IGrowable b = (IGrowable)getFertilizing(ti, world, x, y + ti.up, z);
			if(b.func_149851_a(world, x, y + ti.up, z, world.isRemote)){
				world.getBlock(x, y + ti.up, z).updateTick(world, x, y + ti.up, z, new Random());
				this.decreaseHeat(ti, this.need);
			}
		}
	}

	public Block getFertilizing(TileInteracting ti, World world, int x, int y, int z){
		Block above = world.getBlock(x, y, z);
		if(above != null && above instanceof IGrowable && !MiscHelper.bannedCrops.contains(above)){
			return above;
		}
		return null;
	}

	@Override
	public void increaseHeat(TileInteracting ti, int heat){}

	@Override
	public void decreaseHeat(TileInteracting ti, int heat){
		ti.heat.decreaseEnergy(heat);
	}

	@Override
	public boolean hasRequired(TileInteracting ti) {
		return ti.heat.getHeat() >= this.need;
	}

	@Override
	public void onActivate(TileInteracting ti, EntityPlayer player) {
		
	}
	
	@Override
	public void onActivateClick(TileInteracting ti, EntityPlayer player){
	}

	@Override
	public void onActivateWithItem(TileInteracting ti, ItemStack held, EntityPlayer player) {
		if(!ti.getWorldObj().isRemote && held.getItem() == MMItems.programmer)
			player.addChatMessage(new ChatComponentText(ti.heat.getHeat() + "/" + ti.heat.getMaxHeat() + "Heat"));
	}
	
	@Override
	public void onActivateWithProgrammer(TileInteracting ti, EntityPlayer player){
		if(ti.up < 7)
			ti.up = ti.up + 1;
		else
			ti.up = 2;
		
		if(!ti.getWorldObj().isRemote)
			player.addChatMessage(new ChatComponentText("Fertilizing " + ti.up + " blocks above"));
		
		ti.getWorldObj().markBlockForUpdate(ti.xCoord, ti.yCoord, ti.zCoord);
	}
	
	@Override
	public String getKey() {
		return null;
	}
}