package modularmachines.api.heat;

import java.util.HashMap;

import net.minecraft.item.Item;

public class MMHeatFuels {
	public static void addFuel(Item item, int amount){
		heatFuel.put(item, amount);
	}
	
	public static int getFuel(Item item){
		return heatFuel.get(item);
	}
	
	public static boolean containsItem(Item item){
		return heatFuel.containsKey(item);
	}
	public static HashMap<Item, Integer> heatFuel = new HashMap<Item, Integer>();
}