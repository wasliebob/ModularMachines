/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import modularmachines.events.MMEvent;
import modularmachines.events.MMEventStartup;
import modularmachines.events.MMRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import wasliecore.interfaces.IInitalization;

public class MMEvents implements IInitalization{
	@Override
	public void preInit(){}
	
	@Override
	public void init(){
		MinecraftForge.EVENT_BUS.register(new MMEvent());
		MinecraftForge.EVENT_BUS.register(new MMRenderEvent());
		MinecraftForge.EVENT_BUS.register(new MMEventStartup());
	}
	
	@Override
	public void postInit() {}
}