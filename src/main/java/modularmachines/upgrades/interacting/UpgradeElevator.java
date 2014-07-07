package modularmachines.upgrades.interacting;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.main.MMInteractingUpgrades;
import modularmachines.api.upgrades.IInteractingAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UpgradeElevator implements IInteractingAction{
	int max = 10;
	
	@Override
	public void onUpdate(TileInteracting ti){}
	
	public void teleport(TileInteracting ti, World world, int x, int y, int z, EntityPlayer player){
		TileInteracting des = getDestination(ti, world, x, y, z, player);
		if(des != null){
			player.setPosition(des.xCoord + 0.5D, des.yCoord + 2, des.zCoord + 0.5D);

		}
	}
	
	public TileInteracting getDestination(TileInteracting ti, World world, int x, int y, int z, EntityPlayer player){
		if(player.isSneaking()){
			for(int i = 1; i < max; i++){
				TileEntity te = world.getTileEntity(x, y - i, z);
				if(te != null && te instanceof TileInteracting){
					if(((TileInteracting)te).upgrade != null && MMInteractingUpgrades.getUpgrade(((TileInteracting)te).upgrade).action == this)
						return (TileInteracting)te;
				}
			}
		}else{
			for(int i = 1; i < max; i++){
				TileEntity te = world.getTileEntity(x, y + i, z);
				if(te != null && te instanceof TileInteracting){
					if(((TileInteracting)te).upgrade != null && MMInteractingUpgrades.getUpgrade(((TileInteracting)te).upgrade).action == this)
						return (TileInteracting)te;
				}
			}
		}
		return null;
	}

	@Override
	public void increaseHeat(TileInteracting ti, int heat){}

	@Override
	public void decreaseHeat(TileInteracting ti, int heat){}

	@Override
	public boolean hasRequired(TileInteracting ti){
		return true;
	}

	@Override
	public void onActivate(TileInteracting ti, EntityPlayer player) {
	}
	
	@Override
	public void onActivateClick(TileInteracting ti, EntityPlayer player) {
		teleport(ti, ti.getWorldObj(), ti.xCoord, ti.yCoord, ti.zCoord, player);
	}

	@Override
	public void onActivateWithItem(TileInteracting ti, ItemStack held, EntityPlayer player) {		
	}
	
	@Override
	public void onActivateWithProgrammer(TileInteracting ti, EntityPlayer player) {
		// TODO Auto-generated method stub
	}
}