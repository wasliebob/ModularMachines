package modularmachines.upgrades.base;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.heat.interfaces.IHeatContainer;
import modularmachines.api.upgrades.IMachineAction;
import net.minecraft.item.ItemStack;

public class UpgradeCharger implements IMachineAction{
	public UpgradeCharger(int cost){
		this.cost = cost;
	}
	public int cost;
	
	@Override
	public void onUpdate(TileMachineBase base){		
		if(base.heat.getHeat() >= cost){
			charge(base);
		}
	}
	
	public void charge(TileMachineBase base){
		ItemStack input = base.getStackInSlot(0);
		ItemStack output = base.getStackInSlot(2);
		if(input != null && input.getItem() instanceof IHeatContainer && output == null){
			IHeatContainer con = (IHeatContainer)input.getItem();
			if(base.heat.getHeat() >= cost && con.getHeatStored(input) + cost <= con.getCapacity(input)){
				con.increaseHeatStored(input, cost);
				base.heat.decreaseEnergy(cost);
				base.getWorldObj().markBlockForUpdate(base.xCoord, base.yCoord, base.zCoord);
			}else if(base.heat.getHeat() < cost && con.getHeatStored(input) + base.heat.getHeat() < con.getCapacity(input)){
				con.increaseHeatStored(input, base.heat.getHeat());
				base.heat.decreaseEnergy(base.heat.getHeat());
				base.getWorldObj().markBlockForUpdate(base.xCoord, base.yCoord, base.zCoord);
			}
			
			if(con.getHeatStored(input) == con.getCapacity(input)){
				base.setInventorySlotContents(2, input);
				base.setInventorySlotContents(0, null);
			}
		}
	}
	
	@Override
	public void drainFuel(TileMachineBase base){
		
	}

	@Override
	public void addFuel(TileMachineBase base){
		
	}

	@Override
	public int getTimeLeft(TileMachineBase base){
		return 0;
	}

	@Override
	public int getStartTime(TileMachineBase base){
		return 0;
	}

	@Override
	public boolean hasRequired(TileMachineBase base) {
		return true;
	}
}