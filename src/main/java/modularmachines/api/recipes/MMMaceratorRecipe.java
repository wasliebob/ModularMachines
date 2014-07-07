package modularmachines.api.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MMMaceratorRecipe {
	public static void addRecipe(Item input, ItemStack output){
		if(input != null && output != null)
			macerator.put(input, output);
	}
	
	public static ItemStack getRecipe(Item input){
		return macerator.get(input);
	}
	
	public static boolean containsItem(Item input){
		return macerator.containsKey(input);
	}
	public static HashMap<Item, ItemStack> macerator = new HashMap<Item, ItemStack>();
}