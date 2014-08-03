/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc.helpers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHelper {
	public static boolean entryExists(String material){
		return OreDictionary.getOres(material).size() > 0;
	}
	
	public static ItemStack getItemStack(String material, int entry){
		if(entryExists(material))
			return OreDictionary.getOres(material).get(entry);
		return null;
	}
}