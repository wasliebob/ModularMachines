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