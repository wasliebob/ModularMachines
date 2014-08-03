/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.interfaces;

import net.minecraft.item.ItemStack;

public interface IHeatContainer {
	public int getHeatStored(ItemStack stack);
	public void setHeatStored(ItemStack stack, int heat);
	public void increaseHeatStored(ItemStack stack, int heat);
	public void decreaseHeatStored(ItemStack stack, int heat);
	public int getCapacity(ItemStack stack);
}