package modularmachines.main.init;

import modularmachines.main.MM;
import modularmachines.main.init.integration.WailaIntegration;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.Loader;

public class IntegrationLoader implements IInitalization{
	@Override
	public void preInit(){
		if(Loader.isModLoaded("Waila")){
			waila.preInit();
			message("Waila");
		}
	}
	
	@Override
	public void init(){	
	}

	@Override
	public void postInit(){
	}
	
	public static WailaIntegration waila = new WailaIntegration();

	public static void message(String mod){
		System.out.println("[" + MM.alias + "] " + "You have " + mod + " installed, adding intergration");
	}
}