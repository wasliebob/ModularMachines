//package modularmachines.main.init.integration.waila;
//
//import java.util.List;
//
//import mcp.mobius.waila.api.IWailaConfigHandler;
//import mcp.mobius.waila.api.IWailaDataAccessor;
//import mcp.mobius.waila.api.IWailaDataProvider;
//import modularmachines.blocks.tiles.TileRouter;
//import net.minecraft.block.Block;
//import net.minecraft.item.ItemStack;
//
//public class RouterDataProvider implements IWailaDataProvider{
//
//	@Override
//	public List<String> getWailaBody(ItemStack stack, List<String> tt, IWailaDataAccessor da, IWailaConfigHandler ch) {
//		 if (da.getTileEntity() instanceof TileRouter){
//			 TileRouter te = (TileRouter) da.getTileEntity();
//			 if(te.getBound() != null){
//				 Block block = te.getBound().getWorldObj().getBlock(te.getBound().xCoord, te.getBound().yCoord, te.getBound().zCoord);
//				 int meta = te.getBound().getWorldObj().getBlockMetadata(te.getBound().xCoord, te.getBound().yCoord, te.getBound().zCoord);
//
//				 tt.add("Bound Block: " + new ItemStack(block, 1, meta).getDisplayName());
//			 }else if(te.getBoundMachine() != null){
//				 Block block = te.getBoundMachine().getWorldObj().getBlock(te.getBoundMachine().xCoord(), te.getBoundMachine().yCoord(), te.getBoundMachine().zCoord());
//				 int meta = te.getBoundMachine().getWorldObj().getBlockMetadata(te.getBoundMachine().xCoord(), te.getBoundMachine().yCoord(), te.getBoundMachine().zCoord());
//
//				 tt.add("Bound Block: " + new ItemStack(block, 1, meta).getDisplayName());
//			 }else{
//				 tt.add("Bound Block: " + "Unknown");
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