/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.main;

import modularmachines.api.heat.MMHeatFuels;
import modularmachines.api.misc.GeneratorUpgrade;
import modularmachines.api.misc.InteractingUpgrade;
import modularmachines.api.misc.Upgrade;
import net.minecraft.item.Item;

public class MMApi {
	public static void addUpgrade(Item item, Upgrade upgrade){
		MMUpgrades.addUpgrade(item, upgrade);
	}
	public static MMUpgrades upgrades;
	
	public static void addInteractingUpgrade(Item item, InteractingUpgrade upgrade){
		MMInteractingUpgrades.addUpgrade(item, upgrade);
	}
	public static MMInteractingUpgrades interactingUpgrades;
	
	public static void addGeneratorUpgrade(Item item, GeneratorUpgrade upgrade){
		MMGeneratorUpgrades.addUpgrade(item, upgrade);
	}
	public static MMGeneratorUpgrades generatorUpgrades;
	
	
	public static void addFuel(Item Item, int amount){
		MMHeatFuels.addFuel(Item, amount);
	}
	public static MMHeatFuels heatFuel;
}