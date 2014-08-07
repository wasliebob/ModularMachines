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
import modularmachines.api.misc.interfaces.IPotionTransport;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class IPotionTransportDataProvider implements IWailaDataProvider{

	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
		 if (da.getTileEntity() instanceof IPotionTransport){
			 IPotionTransport te = (IPotionTransport) da.getTileEntity();
			 if(te.getPotionStorage().potion != null){
				 tt.add("Potion: " + LanguageRegistry.instance().getStringLocalization(te.getPotionStorage().getPotion().getName()));
				 tt.add("Amount: " + te.getPotionStorage().amount + "/" + te.getPotionStorage().capacity);
				 tt.add("Instant Effect: " + te.getPotionStorage().getPotion().isInstant());
				 tt.add("Bad Effect: " + te.getPotionStorage().getPotion().isBadEffect());
			 }else{
				 tt.add("Empty");
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