/**
* This file is created by wasliebob.
* Created on: 7 aug. 2014, at 09:58:17
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init.integration.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import net.minecraft.item.ItemStack;

public class IHeatedMachineDataProvider implements IWailaDataProvider{

	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
		 if (da.getTileEntity() instanceof IHeatedMachine){
			 IHeatedMachine te = (IHeatedMachine) da.getTileEntity();
			 
			 if(te.getHeatStorage() != null){
				 tt.add("Energy: " + te.getHeatStorage().getHeat() + "/" + te.getHeatStorage().getMaxHeat());
				 tt.add("Transfer: " + te.getHeatStorage().getTransfer());
			 }
		 }
		 return tt;
	}

	@Override
	public List<String> getWailaHead(ItemStack stack, List<String> tt,
			IWailaDataAccessor da, IWailaConfigHandler cj) {
		return tt;
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor da,
			IWailaConfigHandler ch) {
		return null;
	}

	@Override
	public List<String> getWailaTail(ItemStack stack, List<String> tt,
			IWailaDataAccessor da, IWailaConfigHandler ch) {
		return tt;
	}
}