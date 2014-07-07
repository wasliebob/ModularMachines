//package modularmachines.main.init.integration.waila;
//
//import java.util.List;
//
//import mcp.mobius.waila.api.IWailaConfigHandler;
//import mcp.mobius.waila.api.IWailaDataAccessor;
//import mcp.mobius.waila.api.IWailaDataProvider;
//import modularmachines.api.misc.IPotionStorage;
//import net.minecraft.item.ItemStack;
//import cpw.mods.fml.common.registry.LanguageRegistry;
//
//public class IPotionStorageDataProvider implements IWailaDataProvider{
//
//	@Override
//	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
//		 if (da.getTileEntity() instanceof IPotionStorage){
//			 IPotionStorage te = (IPotionStorage) da.getTileEntity();
//			 if(te.getPotionStorage().potion != null){
//				 tt.add("Potion: " + LanguageRegistry.instance().getStringLocalization(te.getPotionStorage().getPotion().getName()));
//				 tt.add("Amount: " + te.getPotionStorage().amount + "/" + te.getPotionStorage().capacity);
//				 tt.add("Instant Effect: " + te.getPotionStorage().getPotion().isInstant());
//				 tt.add("Bad Effect: " + te.getPotionStorage().getPotion().isBadEffect());
//			 }else{
//				 tt.add("Empty");
//			 }
//		 }
//		 return tt;
//	}
//
//	@Override
//	public List<String> getWailaHead(ItemStack stack, List<String> tt,
//			IWailaDataAccessor da, IWailaConfigHandler cj) {
//		return tt;
//	}
//
//	@Override
//	public ItemStack getWailaStack(IWailaDataAccessor da,
//			IWailaConfigHandler ch) {
//		return null;
//	}
//
//	@Override
//	public List<String> getWailaTail(ItemStack stack, List<String> tt,
//			IWailaDataAccessor da, IWailaConfigHandler ch) {
//		return tt;
//	}
//}