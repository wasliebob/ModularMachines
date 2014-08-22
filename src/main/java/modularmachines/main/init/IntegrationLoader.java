/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import modularmachines.libs.LibMod;
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
		System.out.println("[" + LibMod.alias + "] " + "You have " + mod + " installed, adding intergration");
	}
}