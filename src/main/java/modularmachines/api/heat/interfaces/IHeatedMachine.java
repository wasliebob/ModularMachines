/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.interfaces;

import modularmachines.api.heat.HeatStorage;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IHeatedMachine {
	public HeatStorage getHeatStorage();
	public boolean canAdd(int energy);
	public ForgeDirection getInput();
	public ForgeDirection getOutput();
	public int xCoord();
	public int yCoord();
	public int zCoord();
	public World getWorldObj();
}