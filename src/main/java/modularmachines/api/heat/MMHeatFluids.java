/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat;

import java.util.HashMap;

import net.minecraft.block.Block;

public class MMHeatFluids {
	public static void addFuel(Block block, int amount){
		heatFuel.put(block, amount);
	}
	
	public static int getFuel(Block block){
		return heatFuel.get(block);
	}
	
	public static boolean containsBlock(Block block){
		return heatFuel.containsKey(block);
	}
	public static HashMap<Block, Integer> heatFuel = new HashMap<Block, Integer>();
}