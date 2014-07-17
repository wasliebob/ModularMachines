package modularmachines.main.init;

import modularmachines.helpers.BugHelper;
import modularmachines.main.MM;
import modularmachines.network.PacketPipeline;
import net.minecraft.nbt.NBTTagCompound;
import wasliecore.helpers.IMCHelper;
import wasliecore.helpers.NBTHelper;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class MMInit implements IInitalization{
	@Override
	public void preInit(){
		BugHelper.createBugFolder();
		
		blocks.preInit();
		items.preInit();
		recipes.preInit();
		entries.preInit();
		integration.preInit();
		
	     NBTTagCompound tag = NBTHelper.createTagCompound();
	     tag.setBoolean(IMCHelper.author_wasliebob, true);
	     tag.setBoolean(IMCHelper.mod_wasliecore, true);

	     FMLInterModComms.sendMessage("WaslieCore", IMCHelper.message_register, tag);	
	}

	@Override
	public void init(){
		blocks.init();
		items.init();
		recipes.init();
		events.init();
		entries.init();
		integration.init();
		packetPipeline.initialise();
	}
	
	@Override
	public void postInit(){
		blocks.postInit();
		items.postInit();
		recipes.postInit();
		entries.postInit();
		integration.postInit();
		packetPipeline.postInitialise();
	}
	public static MMRecipes recipes = new MMRecipes();
	public static MMBlocks blocks = new MMBlocks();
	public static MMItems items = new MMItems();
	public static MMEvents events = new MMEvents();
	public static MMEntries entries = new MMEntries();

	public static IntegrationLoader integration = new IntegrationLoader();
	public static PacketPipeline packetPipeline = new PacketPipeline();
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MM.alias + "|PACKET");
}