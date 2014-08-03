/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.main;

import java.util.HashMap;

import modularmachines.api.misc.GeneratorUpgrade;
import net.minecraft.item.Item;

public class MMGeneratorUpgrades {
	public static void addUpgrade(Item item, GeneratorUpgrade upgrade){
		upgrades.put(item, upgrade);
	}
	
	public static GeneratorUpgrade getUpgrade(Item item){
		return upgrades.get(item);
	}
	
	public static boolean containsItem(Item item){
		return upgrades.containsKey(item);
	}
	
	public static HashMap<Item, GeneratorUpgrade> upgrades = new HashMap<Item, GeneratorUpgrade>();
}