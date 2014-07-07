package modularmachines.proxies;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.classes.TileMachineBase;
import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.blocks.tiles.TileRouter;
import modularmachines.client.renders.TileInteractingRenderer;
import modularmachines.client.renders.TileMachineBaseRenderer;
import modularmachines.client.renders.TilePotionTankRenderer;
import modularmachines.client.renders.TileRouterRenderer;
import modularmachines.events.MMKeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerPackets(){
		super.registerPackets();
	}
	
	@Override
	public void registerParticles(){}
	
	@Override
	public void render(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileMachineBase.class, new TileMachineBaseRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInteracting.class, new TileInteractingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRouter.class, new TileRouterRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePotionTank.class, new TilePotionTankRenderer());
	}
	
	@Override
	public void spawnParticle(World world, String particle, int minX, int minY, int minZ, int maxX, int maxY, int maxZ){
		if(Minecraft.getMinecraft().renderViewEntity != null)
			world.spawnParticle(particle, minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	@Override
	public void spawnSound(World worldObj, int xCoord, int yCoord, int zCoord, String id){}

	@Override
	public boolean renderView(){
		return Minecraft.getMinecraft().renderViewEntity != null;
	}
	
	@Override
	public void bindKeys(){ 
		FMLCommonHandler.instance().bus().register(new MMKeyHandler());
	}
}