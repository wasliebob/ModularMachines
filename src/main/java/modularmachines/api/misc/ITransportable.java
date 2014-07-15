package modularmachines.api.misc;

import net.minecraft.nbt.NBTTagCompound;

public interface ITransportable {
	/**
	 * @return if block has a NBTTag
	 */
	public boolean hasTag();
	
	/**
	 * This will get called when having a tag
	 * @param tag
	 * The NBTTag to read.
	 */
	public void readNBT(NBTTagCompound tag);
	
	/**
	 * @return this will return the NBTTag bound the the block
	 */
	public NBTTagCompound getTag();
}