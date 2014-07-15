package modularmachines.main;

import modularmachines.handlers.GuiHandler;
import modularmachines.main.init.MMInit;
import modularmachines.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "ModularMachines", name = "ModularMachines", version = "1.0" ,dependencies = "required-after:WaslieCore")
public class MM {
    @SidedProxy(clientSide = "modularmachines.proxies.ClientProxy", serverSide = "modularmachines.proxies.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("ModularMachines")
    public static MM instance;
    public static double version = 0.2;
    public static String mcVersion ="1.7.10";
    
    public static String modName = "ModularMachines";
    public static String alias = "MM";
    public static boolean isUnstable = true;
    public static MMInit init = new MMInit();
    
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
		proxy.load();
		init.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
    	init.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt){
    	init.postInit();
    }
}