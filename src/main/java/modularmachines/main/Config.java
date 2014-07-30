package modularmachines.main;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {
	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		worldGen = config.get(Configuration.CATEGORY_GENERAL, "worldGen", true).getBoolean(worldGen);
		
		config.save();
	}
	public static boolean worldGen;
}