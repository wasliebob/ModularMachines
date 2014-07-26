package modularmachines.upgrades.generator;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.heat.MMHeatFuels;
import modularmachines.api.upgrades.IGeneratorAction;

public class UpgradeBase implements IGeneratorAction{
	@Override
	public int generateHeat(TileGenerator te){
		int heat = 0;
		if(te.getStackInSlot(0) != null && MMHeatFuels.containsItem(te.getStackInSlot(0).getItem())){
			heat = MMHeatFuels.getFuel(te.getStackInSlot(0).getItem());
			if(te.storage.getHeat() + heat <= te.storage.getMaxHeat()){
				if(te.getStackInSlot(0).stackSize > 1){
					te.stacks[0].stackSize--;
				}else{
					te.stacks[0] = null;
				}
			}
		}
		return heat;
	}
}