package modularmachines.api.misc.helpers;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHelper {
	public static boolean entryExists(String material){
		return OreDictionary.getOres(material).size() > 0;
	}
}