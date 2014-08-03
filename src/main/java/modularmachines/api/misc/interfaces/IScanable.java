/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
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