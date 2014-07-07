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