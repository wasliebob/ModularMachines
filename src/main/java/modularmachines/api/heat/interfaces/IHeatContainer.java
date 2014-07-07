package modularmachines.api.heat.interfaces;

import net.minecraft.item.ItemStack;

public interface IHeatContainer {
	public int getHeatStored(ItemStack stack);
	public void setHeatStored(ItemStack stack, int heat);
	public void increaseHeatStored(ItemStack stack, int heat);
	public void decreaseHeatStored(ItemStack stack, int heat);
	public int getCapacity(ItemStack stack);
}