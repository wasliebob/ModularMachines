package modularmachines.api.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class MMRecipeHelper {
	public static HashMap<Item, ItemStack> macerator = new HashMap<Item, ItemStack>();
	public static HashMap<Item, ItemStack> blast = new HashMap<Item, ItemStack>();
	public static HashMap<Item, Potion> brewing = new HashMap<Item, Potion>();
}