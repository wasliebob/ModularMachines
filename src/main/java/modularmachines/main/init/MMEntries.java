package modularmachines.main.init;

import modularmachines.api.guide.EntryHelper;
import modularmachines.api.guide.IEntry;
import modularmachines.api.guide.entries.EntryInfo;
import modularmachines.api.guide.entries.EntryRecipe;
import modularmachines.api.guide.entries.EntryText;
import modularmachines.blocks.guis.guide.entries.EntryTitle;
import wasliecore.interfaces.IInitalization;

public class MMEntries implements IInitalization{
	@Override
	public void preInit() {

	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {

		/** Page One */
		EntryHelper.addEntry("Modular Machines", new IEntry[]{new EntryTitle("modularmachines:textures/misc/logo.png", 122, 44), new EntryText()}, 0);
		EntryHelper.addEntry("Modular Core", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.core_machine)}, 0);
		EntryHelper.addEntry("Interacting Core", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.core_interacting)}, 0);
		EntryHelper.addEntry("Heat Router", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.router)}, 0);
		EntryHelper.addEntry("Fluid Heater", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.heater_fluid)}, 0);
		EntryHelper.addEntry("Potion Tank", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.tank_potion)}, 0);
		EntryHelper.addEntry("Item Transporter", new IEntry[]{new EntryText(), new EntryRecipe(MMRecipes.transporter)}, 0);
	
		
		EntryHelper.addEntry("Upgrade: Break", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_break)}, 0);
		EntryHelper.addEntry("Upgrade: Elevator", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_elevator)}, 0);
		EntryHelper.addEntry("Upgrade: Fertilize", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_fertilize)}, 0);
		EntryHelper.addEntry("Upgrade: Place", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_place)}, 0);
		EntryHelper.addEntry("Upgrade: Transfer", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_transfer)}, 0);
		EntryHelper.addEntry("Upgrade: Update", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_update)}, 0);
		EntryHelper.addEntry("Upgrade: Vacuum", new IEntry[]{new EntryText(), new EntryInfo(1), new EntryRecipe(MMRecipes.interacting_vacuum)}, 0);
		
		/** Page Two */
		EntryHelper.addEntry("Upgrade: Furnace", new IEntry[]{new EntryText(), new EntryInfo(0), new EntryRecipe(MMRecipes.upgrade_furnace)}, 1);
		EntryHelper.addEntry("Upgrade: Macerator", new IEntry[]{new EntryText(), new EntryInfo(0), new EntryRecipe(MMRecipes.upgrade_macerator)}, 1);
		EntryHelper.addEntry("Upgrade: Charger", new IEntry[]{new EntryText(), new EntryInfo(0), new EntryRecipe(MMRecipes.upgrade_charger)}, 1);
	}
}