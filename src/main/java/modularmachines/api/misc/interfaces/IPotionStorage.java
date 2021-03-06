/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc.interfaces;

import modularmachines.api.misc.PotionStorage;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPotionStorage {
	public PotionStorage getPotionStorage();
	public boolean canSend();
	public ForgeDirection inputSide();
	public ForgeDirection outputSide();
	public void send();
	public boolean canReceive(Potion potion, int amount);
	public int getSending();
	public boolean canConnect(ForgeDirection dir);
	public void markUpdate();
}