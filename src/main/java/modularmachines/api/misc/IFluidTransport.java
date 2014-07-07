package modularmachines.api.misc;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

public interface IFluidTransport {
	public FluidTank getTank();
	public int getTransfer();
	public int calcSending(IFluidTransport ft);
	public boolean canAdd(Fluid fluid, int amount);
}