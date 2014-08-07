/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init.integration;

import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.event.FMLInterModComms;

public class WailaIntegration implements IInitalization{
	@Override
	public void preInit(){
        FMLInterModComms.sendMessage("Waila", "register", "modularmachines.main.init.integration.waila.WailaRegistar.callbackRegister");
	}
	
	@Override
	public void init(){}

	@Override
	public void postInit(){}
}