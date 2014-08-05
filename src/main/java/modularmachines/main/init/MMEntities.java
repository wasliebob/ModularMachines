/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 18:17:11
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import modularmachines.entities.projectiles.ProjectilePotionOrb;
import modularmachines.main.MM;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.registry.EntityRegistry;

public class MMEntities implements IInitalization{
	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
        EntityRegistry.registerModEntity(ProjectilePotionOrb.class, "orbPotion", 1, MM.instance, 160, 20, true);	
	}

	@Override
	public void postInit() {
		
	}
}