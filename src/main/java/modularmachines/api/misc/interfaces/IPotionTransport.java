/**
* This file is created by wasliebob.
* Created on: 4 aug. 2014, at 11:45:34
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc.interfaces;

import java.util.ArrayList;

import modularmachines.api.misc.PotionStorage;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;

public interface IPotionTransport {
	public PotionStorage getPotionStorage();
	public ArrayList<TileEntity> getOutput();
	public boolean canReceive(Potion potion, int amount);
	public void send();
	public int getSending();
	public void markUpdate();
}