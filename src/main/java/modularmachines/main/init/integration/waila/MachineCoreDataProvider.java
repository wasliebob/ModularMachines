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
import modularmachines.api.classes.TileMachineBase;
import net.minecraft.item.ItemStack;

public class MachineCoreDataProvider implements IWailaDataProvider{

	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
		 if (da.getTileEntity() instanceof TileMachineBase){
			 TileMachineBase te = (TileMachineBase) da.getTileEntity();
			 if(te.input != null)
				 tt.add("Input Side: " + te.input);
			 else
				 tt.add("Input Side: " + "Unknown");
			 
			 if(te.output != null)
				 tt.add("Output Side: " + te.output);
			 else
				 tt.add("Output Side: " + "Unknown");
			 
			 if(te.expension != null)
				 tt.add("Expansion Side: " + te.expension);
			 else
				 tt.add("Expansion Side: " + "Unknown");
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