package modularmachines.api.misc.interfaces;

import net.minecraft.nbt.NBTTagCompound;

public interface IScanable{
	/**
	 * Argument accepting:
	 * name - String,
	 * heat - String,
	 * transfer - String,
	 * potion - String,
	 * @return filled tag compound
	 */
	public NBTTagCompound getInfo();
}