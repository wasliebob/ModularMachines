package modularmachines.api.heat.interfaces;

import net.minecraft.item.ItemStack;

public interface IHeatGenerator {
	public IHeatTransport getBound();
	public void increaseHeat(int heat);
	public ItemStack getFuel();
}