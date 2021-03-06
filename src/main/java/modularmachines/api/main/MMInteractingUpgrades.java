/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.main;

import java.util.HashMap;

import modularmachines.api.misc.InteractingUpgrade;
import net.minecraft.item.Item;

public class MMInteractingUpgrades {
	public static void addUpgrade(Item item, InteractingUpgrade upgrade){
		upgrades.put(item, upgrade);
	}
	
	public static InteractingUpgrade getUpgrade(Item item){
		return upgrades.get(item);
	}
	
	public static boolean containsItem(Item item){
		return upgrades.containsKey(item);
	}
	
	public static HashMap<Item, InteractingUpgrade> upgrades = new HashMap<Item, InteractingUpgrade>();
}