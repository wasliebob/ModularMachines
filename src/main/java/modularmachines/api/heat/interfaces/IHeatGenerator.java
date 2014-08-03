/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.interfaces;

import net.minecraft.item.ItemStack;

public interface IHeatGenerator {
	public IHeatTransport getBound();
	public void increaseHeat(int heat);
	public ItemStack getFuel();
}