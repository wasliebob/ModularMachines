/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main;

import modularmachines.events.MMRenderOverlay;
import modularmachines.handlers.GuiHandler;
import modularmachines.libs.LibMod;
import modularmachines.main.init.MMInit;
import modularmachines.proxies.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = LibMod.modId, name = LibMod.modName, version = LibMod.modVersion, dependencies = LibMod.deps)
public class MM {
    @SidedProxy(clientSide = "modularmachines.proxies.ClientProxy", serverSide = "modularmachines.proxies.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("ModularMachines")
    public static MM instance;
    public static MMInit init = new MMInit();
    
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
		proxy.load();
		Config.loadConfig(event);
		init.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
    	init.init();
    	
    	if(event.getSide() == Side.CLIENT){
    		MinecraftForge.EVENT_BUS.register(new MMRenderOverlay());
    	}
    	
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt){
    	init.postInit();
    }
}