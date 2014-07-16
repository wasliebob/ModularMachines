package modularmachines.main.init;

import modularmachines.api.guide.EntryHelper;
import modularmachines.api.guide.IEntry;
import modularmachines.api.guide.entries.EntryRecipe;
import modularmachines.api.guide.entries.EntryText;
import modularmachines.blocks.guis.guide.entries.EntryTitle;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
		ItemStack[] recipe;

		EntryHelper.addEntry("Modular Machines", new IEntry[]{new EntryTitle("modularmachines:textures/misc/logo.png", 122, 44), new EntryText()}, 0);
		
		recipe = new ItemStack[]{
			new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot),
			new ItemStack(Items.redstone), new ItemStack(Blocks.iron_block), new ItemStack(Items.redstone), 
			new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot)
		};
		EntryHelper.addEntry("Modular Core", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);

		
		recipe = new ItemStack[]{
				new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot),
				new ItemStack(Items.redstone), new ItemStack(MMBlocks.core_machine), new ItemStack(Items.redstone), 
				new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot)
		};
		EntryHelper.addEntry("Interacting Core", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);
	
	
		recipe = new ItemStack[]{
				new ItemStack(Items.iron_ingot), new ItemStack(Items.coal), new ItemStack(Items.iron_ingot),
				new ItemStack(Items.coal), new ItemStack(Blocks.redstone_block),  new ItemStack(Items.coal),
				new ItemStack(Items.iron_ingot), new ItemStack(Items.coal), new ItemStack(Items.iron_ingot) 
		};
		EntryHelper.addEntry("Heat Router", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);

		recipe = new ItemStack[]{
				new ItemStack(Items.lava_bucket), new ItemStack(Items.diamond), new ItemStack(Items.lava_bucket),
				new ItemStack(Items.diamond), new ItemStack(MMBlocks.router),  new ItemStack(Items.diamond),
				new ItemStack(Items.lava_bucket), new ItemStack(Items.diamond), new ItemStack(Items.lava_bucket) 
		};
		EntryHelper.addEntry("Fluid Heater", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);
		
		recipe = new ItemStack[]{
				new ItemStack(Items.glass_bottle), new ItemStack(Blocks.glass), new ItemStack(Items.glass_bottle),
				new ItemStack(Blocks.glass), new ItemStack(Items.diamond),  new ItemStack(Blocks.glass),
				new ItemStack(Items.glass_bottle), new ItemStack(Blocks.glass), new ItemStack(Items.glass_bottle) 
		};
		EntryHelper.addEntry("Potion Tank", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);
		
		recipe = new ItemStack[]{
				new ItemStack(Items.iron_ingot), new ItemStack(Items.ender_pearl), new ItemStack(Items.iron_ingot),
				new ItemStack(Blocks.chest), new ItemStack(Blocks.hopper),  new ItemStack(Blocks.chest),
				new ItemStack(Items.iron_ingot), new ItemStack(Items.ender_pearl), new ItemStack(Items.iron_ingot) 
		};
		EntryHelper.addEntry("Item Transporter", new IEntry[]{new EntryText(), new EntryRecipe(recipe)}, 0);
	
		EntryHelper.addEntry("Upgrade: Break", new IEntry[]{new EntryText()}, 0);
		
		EntryHelper.addEntry("Upgrade: Elevator", new IEntry[]{new EntryText()}, 0);
		
		EntryHelper.addEntry("Upgrade: Fertilize", new IEntry[]{new EntryText()}, 0);
		
		EntryHelper.addEntry("Upgrade: Place", new IEntry[]{new EntryText()}, 0);

		EntryHelper.addEntry("Upgrade: Transfer", new IEntry[]{new EntryText()}, 0);

		EntryHelper.addEntry("Upgrade: Update", new IEntry[]{new EntryText()}, 0);

		EntryHelper.addEntry("Upgrade: Vacuum", new IEntry[]{new EntryText()}, 0);

	}
}