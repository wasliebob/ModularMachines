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
		String[] text;
		ItemStack[] recipe;
		
		text = new String[]{
				"Next: Button",
				"Previous: Button",
				"Return: Backspace"
		};
		EntryHelper.addEntry("Modular Machines", new IEntry[]{new EntryTitle("modularmachines:textures/misc/logo.png", 122, 44), new EntryText(text)}, 0);
		
		
		text = new String[]{
				"The machine core",
				"is the block you",
				"most likely want",
				"to make first",
		};
		
		recipe = new ItemStack[]{
			new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot),
			new ItemStack(Items.redstone), new ItemStack(Blocks.iron_block), new ItemStack(Items.redstone), 
			new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot)
		};
		EntryHelper.addEntry("Machine Core", new IEntry[]{new EntryText(text), new EntryRecipe(recipe)}, 0);
		
		
		
		text = new String[]{
				"The interacting" ,
				"machine core.",
				"is a really",
				"helpfull block",
				"that will interact",
				"with the world.",
				"this block has a",
				"lot of different",
				"upgrades.",
		};
		
		recipe = new ItemStack[]{
				new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot),
				new ItemStack(Items.redstone), new ItemStack(MMBlocks.core_machine), new ItemStack(Items.redstone), 
				new ItemStack(Items.gold_ingot), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot)
			};
			EntryHelper.addEntry("Interacting Core", new IEntry[]{new EntryText(text), new EntryRecipe(recipe)}, 0);
	}
}