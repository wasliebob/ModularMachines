package modularmachines.api.heat;

import java.util.HashMap;

import net.minecraft.item.Item;

public class MMHeatFuels {
	public static void addFuel(Item Item, int amount){
		heatFuel.put(Item, amount);
	}
	
	public static int getFuel(Item Item){
		return heatFuel.get(Item);
	}
	
	public static boolean containsItem(Item Item){
		return heatFuel.containsKey(Item);
	}
	public static HashMap<Item, Integer> heatFuel = new HashMap<Item, Integer>();
}