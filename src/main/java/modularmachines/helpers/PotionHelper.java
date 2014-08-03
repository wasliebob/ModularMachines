/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.helpers;

import net.minecraft.potion.Potion;

public class PotionHelper {
	public static Potion getPotionFromID(int id){
		return Potion.potionTypes[id];
	}
	
	public static int getIDFromPotion(Potion potion){
		return potion.getId();
	}
}