package modularmachines.main.init;

import modularmachines.main.MM;
import modularmachines.network.PacketPipeline;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class MMInit implements IInitalization{
	@Override
	public void preInit(){
		blocks.preInit();
		items.preInit();
		recipes.preInit();
		integration.preInit();
	}

	@Override
	public void init()
	{
		blocks.init();
		items.init();
		recipes.init();
		events.init();
		integration.init();
		packetPipeline.initialise();
	}
	
	@Override
	public void postInit()
	{
		blocks.postInit();
		items.postInit();
		recipes.postInit();
		integration.postInit();
		packetPipeline.postInitialise();
	}
	public static MMRecipes recipes = new MMRecipes();
	public static MMBlocks blocks = new MMBlocks();
	public static MMItems items = new MMItems();
	public static MMEvents events = new MMEvents();
	public static IntegrationLoader integration = new IntegrationLoader();
	public static PacketPipeline packetPipeline = new PacketPipeline();
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MM.alias + "|PACKET");
}