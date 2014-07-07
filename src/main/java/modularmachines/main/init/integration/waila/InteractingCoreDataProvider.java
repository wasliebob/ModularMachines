//package modularmachines.main.init.integration.waila;
//
//import java.util.List;
//
//import mcp.mobius.waila.api.IWailaConfigHandler;
//import mcp.mobius.waila.api.IWailaDataAccessor;
//import mcp.mobius.waila.api.IWailaDataProvider;
//import modularmachines.api.classes.TileInteracting;
//import net.minecraft.item.ItemStack;
//import cpw.mods.fml.common.registry.LanguageRegistry;
//
//public class InteractingCoreDataProvider implements IWailaDataProvider{
//
//	@Override
//	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
//		 if (da.getTileEntity() instanceof TileInteracting){
//			 TileInteracting te = (TileInteracting) da.getTileEntity();
//			 if(te.upgradeSide != null && te.upgrade != null){
//				 if(te.upgrade.getUnlocalizedName() != null && LanguageRegistry.instance().getStringLocalization(te.upgrade.getUnlocalizedName()) != null)
//					 tt.add("Upgrade: " + LanguageRegistry.instance().getStringLocalization(te.upgrade.getUnlocalizedName()));
//				 else
//					 tt.add("Upgrade: " + "Unknown");
//
//				 tt.add("Upgrade Side: " + te.upgradeSide.name());
//			 }else{
//				 tt.add("Upgrade: " + "Unknown");
//				 tt.add("Upgrade Side: " + "Unknown");
//			 }
//			 
//			 if(te.input != null)
//				 tt.add("Input Side: " + te.input);
//			 else
//				 tt.add("Input Side: " + "Unknown");
//			 
//			 if(te.output != null)
//				 tt.add("Output Side: " + te.output);
//			 else
//				 tt.add("Output Side: " + "Unknown");			 
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