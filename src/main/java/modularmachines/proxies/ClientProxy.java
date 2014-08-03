/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.proxies;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.classes.TileMachineBase;
import modularmachines.blocks.tiles.TileFluidHeater;
import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.blocks.tiles.TileRouter;
import modularmachines.blocks.tiles.TileTransporter;
import modularmachines.client.renders.RenderFluidHeater;
import modularmachines.client.renders.TileFluidHeaterRenderer;
import modularmachines.client.renders.TileInteractingRenderer;
import modularmachines.client.renders.TileMachineBaseRenderer;
import modularmachines.client.renders.TilePotionTankRenderer;
import modularmachines.client.renders.TileRouterRenderer;
import modularmachines.client.renders.TileTransporterRenderer;
import modularmachines.events.MMKeyHandler;
import modularmachines.main.init.MMInit;
import modularmachines.network.packets.newpackets.PacketToggle;
import modularmachines.network.packets.newpackets.PacketToggleInteracting;
import modularmachines.network.packets.newpackets.PacketTransferRate;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerPackets(){
		super.registerPackets();
		MMInit.network.registerMessage(PacketToggle.class, PacketToggle.class, 201, Side.SERVER);
		MMInit.network.registerMessage(PacketToggleInteracting.class, PacketToggleInteracting.class, 202, Side.SERVER);
		MMInit.network.registerMessage(PacketTransferRate.class, PacketTransferRate.class, 203, Side.SERVER);
	}
	
	@Override
	public void registerParticles(){}
	
	@Override
	public void render(){
		RenderingRegistry.registerBlockHandler(new RenderFluidHeater());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMachineBase.class, new TileMachineBaseRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInteracting.class, new TileInteractingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRouter.class, new TileRouterRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePotionTank.class, new TilePotionTankRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTransporter.class, new TileTransporterRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileFluidHeater.class, new TileFluidHeaterRenderer());

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