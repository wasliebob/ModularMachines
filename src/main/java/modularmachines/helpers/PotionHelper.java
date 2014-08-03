/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.helpers;

import java.util.ArrayList;

import net.minecraft.potion.Potion;

public class PotionHelper {
	public static Potion getPotionFromID(int id){
		return Potion.potionTypes[id];
	}
	
	public static int getIDFromPotion(Potion potion){
		return potion.getId();
	}
	
	public static ArrayList<Potion> getPotionList(){
		return PotionHelper.convertToList(Potion.potionTypes);
	}
	
	public static Potion getPotion(int entry){
		return getPotionList().get(entry);
	}
	
	public static ArrayList<Potion> convertToList(Potion[] potions){
		ArrayList<Potion> list = new ArrayList<Potion>();
		for(Potion s : potions)
			list.add(s);
		return list;
	}
}