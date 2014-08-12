/**
* This file is created by wasliebob.
* Created on: 12 aug. 2014, at 20:09:52
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import modularmachines.main.Config;
import modularmachines.world.WorldGenOre;
import modularmachines.world.structures.components.ComponentVillageEngineer;
import modularmachines.world.structures.handlers.EngineerCreationHandler;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class MMWorldGen implements IInitalization{
	@Override
	public void preInit(){
		if(Config.worldGen = true)
			GameRegistry.registerWorldGenerator(new WorldGenOre(), 2);
		VillagerRegistry.instance().registerVillageCreationHandler(new EngineerCreationHandler());
		MapGenStructureIO.func_143031_a(ComponentVillageEngineer.class, "modularmachines:engineer");
	}
	
	@Override
	public void init(){

	}
	
	@Override
	public void postInit() {}
}