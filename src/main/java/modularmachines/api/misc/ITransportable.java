package modularmachines.api.misc;

import net.minecraft.nbt.NBTTagCompound;

public interface ITransportable {
	public boolean hasTag();
	public void readNBT(NBTTagCompound tag);
	public NBTTagCompound getTag();
}