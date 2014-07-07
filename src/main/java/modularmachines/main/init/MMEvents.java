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