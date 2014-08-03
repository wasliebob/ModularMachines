package modularmachines.main.init;

import java.util.ArrayList;

import modularmachines.api.heat.MMHeatFluids;
import modularmachines.api.main.MMApi;
import modularmachines.api.misc.GeneratorUpgrade;
import modularmachines.api.misc.InteractingUpgrade;
import modularmachines.api.misc.Upgrade;
import modularmachines.api.misc.helpers.MiscHelper;
import modularmachines.api.misc.helpers.RecipeHelper;
import modularmachines.api.recipes.MMRecipeHelper;
import modularmachines.upgrades.base.UpgradeBlast;
import modularmachines.upgrades.base.UpgradeBrewing;
import modularmachines.upgrades.base.UpgradeCharger;
import modularmachines.upgrades.base.UpgradeFurnace;
import modularmachines.upgrades.base.UpgradeMacerator;
import modularmachines.upgrades.generator.UpgradeBase;
import modularmachines.upgrades.interacting.UpgradeBreak;
import modularmachines.upgrades.interacting.UpgradeElevator;
import modularmachines.upgrades.interacting.UpgradeFertilize;
import modularmachines.upgrades.interacting.UpgradeMiner;
import modularmachines.upgrades.interacting.UpgradePlace;
import modularmachines.upgrades.interacting.UpgradeTransfer;
import modularmachines.upgrades.interacting.UpgradeUpdate;
import modularmachines.upgrades.interacting.UpgradeVacuum;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraftforge.oredict.OreDictionary;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMRecipes implements IInitalization{
	@Override
	public void preInit(){
	}
	
	@Override
	public void init(){}

	@Override
	public void postInit(){
		initCraftingRecipes();
		initFuels();
		initFluidFuels();
		initFurnaceRecipes();
		initMaceratorRecipes();
		initBlastRecipes();
		initBrewingRecipes();
		initMisc();
		initUpgrades();
		initInteractingUpgrades();
		initGeneratorUpgrades();
	}

	public void initCraftingRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(MMItems.guide), 
				new ItemStack(Items.book), new ItemStack(Items.iron_ingot));
		
		RecipeHelper.addShapedOreRecipe(new ItemStack(MMItems.ingot_bronze), new Object[]{
			"XX",
			"XY",
			'X', "ingotCopper",
			'Y', "ingotTin"});
		ingot_bronze = RecipeHelper.getLatest();
	
	RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.core_machine), new Object[]{
				"XYX",
				"YIY",
				"XYX",
				'X', Items.gold_ingot,
				'Y', Items.redstone,
				'I', Blocks.iron_block});
		core_machine = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.core_interacting), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.gold_ingot,
			'Y', Items.redstone,
			'I', MMBlocks.core_machine});
		core_interacting = RecipeHelper.getLatest();

		
		RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.router), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.coal,
			'I', Blocks.redstone_block});
		router = RecipeHelper.getLatest();

		RecipeHelper.addShapedOreRecipe(new ItemStack(MMBlocks.generator), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', "ingotBronze",
			'Y', Items.gold_ingot,
			'I', MMBlocks.router});
		generator = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.heater_fluid), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.lava_bucket,
			'Y', Items.diamond,
			'I', MMBlocks.router});
		heater_fluid = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.tank_potion), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.glass_bottle,
			'Y', Blocks.glass,
			'I', Items.diamond});
		tank_potion = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.input), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.redstone,
			'I', Items.gold_ingot});
		input = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.output), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.redstone,
			'Y', Items.iron_ingot,
			'I', Items.gold_ingot});
		output = RecipeHelper.getLatest();

		RecipeHelper.addShapedOreRecipe(new ItemStack(MMItems.upgrade_empty), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', "ingotCopper",
			'Y', Items.gold_nugget,
			'I', Items.redstone});
		upgrade_empty = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.expension), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.gold_ingot,
			'Y', Blocks.chest,
			'I', Items.diamond});
		expension = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.screen), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.gold_ingot,
			'Y', Items.iron_ingot,
			'I', Blocks.glass});
		screen = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.upgrade_furnace), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.redstone,
			'I', MMItems.upgrade_empty});
		upgrade_furnace = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.upgrade_macerator), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.flint,
			'I', MMItems.upgrade_empty});
		upgrade_macerator = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.upgrade_blast), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Blocks.iron_block,
			'Y', Blocks.coal_block,
			'I', MMItems.upgrade_empty});
		upgrade_blast = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.upgrade_charger), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.gold_ingot,
			'I', MMItems.upgrade_empty});
		upgrade_charger = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.upgrade_brewing), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.blaze_rod,
			'I', MMItems.upgrade_empty});
		upgrade_brewing = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.wrench), new Object[]{
			"X X",
			"XYX",
			" X ",
			'X', Items.iron_ingot,
			'Y', Items.diamond});
		wrench = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.programmer), new Object[]{
			"XYX",
			"YZY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.gold_ingot,
			'Z', Blocks.glass});
		programmer = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.linker), new Object[]{
			"XZX",
			"XYX",
			"XXX",
			'X', Items.gold_ingot,
			'Z', Blocks.glass_pane,
			'Y', MMItems.programmer});
		linker = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.scanner), new Object[]{
			"XZX",
			"XYX",
			"XXX",
			'X', MMItems.ingot_steel,
			'Z', Blocks.glass_pane,
			'Y', MMItems.programmer});
		scanner = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_break), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.piston,
			'I', MMItems.upgrade_empty});
		interacting_break = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_elevator), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.piston,
			'I', MMItems.upgrade_empty});
		interacting_elevator = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_fertilize), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.diamond,
			'I', MMItems.upgrade_empty});
		interacting_fertilize = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_place), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.piston,
			'I', MMItems.upgrade_empty});
		interacting_place = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_transfer), new Object[]{
			"XYX",
			"WIW",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.ender_pearl,
			'W', Blocks.chest,
			'I', MMItems.upgrade_empty});
		interacting_transfer = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_vacuum), new Object[]{
			"XYX",
			"WIW",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.ender_pearl,
			'W', Items.gold_ingot,
			'I', MMItems.upgrade_empty});
		interacting_vacuum = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_update), new Object[]{
			"XYX",
			"WIW",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.stone_pressure_plate,
			'W', Items.redstone,
			'I', MMItems.upgrade_empty});
		interacting_update = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.interacting_miner), new Object[]{
			"XYX",
			"WIW",
			"XZX",
			'X', Blocks.redstone_block,
			'Y', Blocks.gold_block,
			'Z', Blocks.gold_block,
			'W', Blocks.iron_block,
			'I', MMItems.upgrade_empty});
		interacting_miner = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedOreRecipe(new ItemStack(MMItems.generator_base), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', "ingotTin",
			'Y', "ingotCopper",
			'I', MMItems.upgrade_empty});
		generator_base = RecipeHelper.getLatest();
		
		RecipeHelper.addShapedRecipe(new ItemStack(MMBlocks.transporter), new Object[]{
			"XYX",
			"WIW",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Items.ender_pearl,
			'W', Blocks.chest,
			'I', Blocks.hopper});
		transporter = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.crystal_energy), new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.redstone_block,
			'I', Items.diamond});
		crystal_energy = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.stick_iron), new Object[]{
			"X",
			"X",
			'X', Items.iron_ingot});
		stick_iron = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.axe_heated), new Object[]{
			" XY",
			" IX",
			" I ",
			'X', Items.iron_ingot,
			'Y', MMItems.crystal_energy,
			'I', MMItems.stick_iron});
		axe_heated = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.pickaxe_heated), new Object[]{
			"XYX",
			" I ",
			" I ",
			'X', Items.iron_ingot,
			'Y', MMItems.crystal_energy,
			'I', MMItems.stick_iron});
		pickaxe_heated = RecipeHelper.getLatest();

		RecipeHelper.addShapedRecipe(new ItemStack(MMItems.shovel_heated), new Object[]{
			" X ",
			" Y ",
			" I ",
			'X', Items.iron_ingot,
			'Y', MMItems.crystal_energy,
			'I', MMItems.stick_iron});
		shovel_heated = RecipeHelper.getLatest();

	}
	public static IRecipe core_machine;
	public static IRecipe core_interacting;
	public static IRecipe heater_fluid;
	public static IRecipe tank_potion;
	public static IRecipe router;
	public static IRecipe transporter;
	public static IRecipe generator;

	public static IRecipe interacting_break;
	public static IRecipe interacting_elevator;
	public static IRecipe interacting_transfer;
	public static IRecipe interacting_update;
	public static IRecipe interacting_place;
	public static IRecipe interacting_vacuum;
	public static IRecipe interacting_fertilize;
	public static IRecipe interacting_miner;

	public static IRecipe upgrade_furnace;
	public static IRecipe upgrade_macerator;
	public static IRecipe upgrade_charger;
	public static IRecipe upgrade_blast;
	public static IRecipe upgrade_brewing;

	public static IRecipe generator_base;

	public static IRecipe axe_heated;
	public static IRecipe pickaxe_heated;
	public static IRecipe shovel_heated;

	public static IRecipe input;
	public static IRecipe output;
	public static IRecipe screen;
	public static IRecipe expension;

	public static IRecipe wrench;
	public static IRecipe programmer;
	public static IRecipe linker;
	public static IRecipe scanner;
	
	public static IRecipe stick_iron;
	public static IRecipe crystal_energy;
	public static IRecipe ingot_bronze;
	public static IRecipe upgrade_empty;
	
	public void initFurnaceRecipes(){
		//Modular Machines Materials
		RecipeHelper.addDustSmeltingRecipe("Iron");
		RecipeHelper.addDustSmeltingRecipe("Gold");
		
		//Vanilla Materials
		RecipeHelper.addDustSmeltingRecipe("Copper");
		RecipeHelper.addDustSmeltingRecipe("Tin");
		RecipeHelper.addDustSmeltingRecipe("Bronze");
	}
	
	public void initMaceratorRecipes(){
		ArrayList<ItemStack> output;
		output = OreDictionary.getOres("dustIron");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(Items.iron_ingot, output.get(0));
			MMRecipeHelper.macerator.put(Item.getItemFromBlock(Blocks.iron_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustGold");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(Items.gold_ingot, output.get(0));
			MMRecipeHelper.macerator.put(Item.getItemFromBlock(Blocks.gold_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustDiamond");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(Items.diamond, output.get(0));
			MMRecipeHelper.macerator.put(Item.getItemFromBlock(Blocks.diamond_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustEmerald");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(Items.emerald, output.get(0));
			MMRecipeHelper.macerator.put(Item.getItemFromBlock(Blocks.emerald_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustCoal");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(Items.coal, output.get(0));
			MMRecipeHelper.macerator.put(Item.getItemFromBlock(Blocks.coal_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustCopper");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(MMItems.ingot_copper, output.get(0));
		}
		
		output = OreDictionary.getOres("dustTin");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(MMItems.ingot_tin, output.get(0));
		}
		
		output = OreDictionary.getOres("dustBronze");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(MMItems.ingot_bronze, output.get(0));
		}
		
		output = OreDictionary.getOres("dustSteel");
		if(output != null && output.size() > 0){
			MMRecipeHelper.macerator.put(MMItems.ingot_steel, output.get(0));
		}
	}
	
	public void initBlastRecipes(){
		ArrayList<ItemStack> output;
		output = OreDictionary.getOres("ingotSteel");
		if(output != null && output.size() > 0){
			MMRecipeHelper.blast.put(Items.iron_ingot, output.get(0));
			MMRecipeHelper.blast.put(MMItems.dust_iron, output.get(0));
			MMRecipeHelper.blast.put(MMItems.dust_steel, output.get(0));
		}
	}
	
	public void initBrewingRecipes(){
		MMRecipeHelper.brewing.put(Items.golden_carrot, Potion.invisibility);
		MMRecipeHelper.brewing.put(Items.ghast_tear, Potion.regeneration);
		MMRecipeHelper.brewing.put(Items.fermented_spider_eye, Potion.weakness);
		MMRecipeHelper.brewing.put(Items.gunpowder, Potion.harm);
		MMRecipeHelper.brewing.put(Items.sugar, Potion.moveSpeed);
		MMRecipeHelper.brewing.put(Items.spider_eye, Potion.poison);
		MMRecipeHelper.brewing.put(Items.magma_cream, Potion.fireResistance);
		MMRecipeHelper.brewing.put(Items.speckled_melon, Potion.heal);
		MMRecipeHelper.brewing.put(Items.blaze_powder, Potion.damageBoost);
		MMRecipeHelper.brewing.put(Items.fish, Potion.waterBreathing);
	}
	
	public void initFluidFuels(){
		MMHeatFluids.addFuel(Blocks.lava, 10);
	}
	
	public void initFuels(){
		MMApi.addFuel(Items.coal, 10);

		MMApi.addFuel(Item.getItemFromBlock(Blocks.log), 5);
		MMApi.addFuel(Item.getItemFromBlock(Blocks.coal_block), 10*9);
	}
	
	public void initMisc(){
		MiscHelper.bannedMiner.add(Blocks.stone);
		MiscHelper.bannedMiner.add(Blocks.cobblestone);
		MiscHelper.bannedMiner.add(Blocks.sand);
		MiscHelper.bannedMiner.add(Blocks.gravel);
		MiscHelper.bannedMiner.add(Blocks.sandstone);
		MiscHelper.bannedMiner.add(Blocks.dirt);
		MiscHelper.bannedMiner.add(Blocks.grass);
		MiscHelper.bannedMiner.add(Blocks.mossy_cobblestone);
		MiscHelper.bannedMiner.add(Blocks.bedrock);
		MiscHelper.bannedMiner.add(Blocks.chest);
		MiscHelper.bannedMiner.add(Blocks.furnace);
		MiscHelper.bannedMiner.add(Blocks.mycelium);
		MiscHelper.bannedMiner.add(Blocks.log);
		MiscHelper.bannedMiner.add(Blocks.log2);
		MiscHelper.bannedMiner.add(Blocks.planks);
		MiscHelper.bannedMiner.add(Blocks.fence);
		MiscHelper.bannedMiner.add(Blocks.torch);		
	}
	
	public void initUpgrades(){
		MMApi.addUpgrade(MMItems.upgrade_furnace, new Upgrade(new UpgradeFurnace(10)));
		MMApi.addUpgrade(MMItems.upgrade_macerator, new Upgrade(new UpgradeMacerator(10)));
		MMApi.addUpgrade(MMItems.upgrade_charger, new Upgrade(new UpgradeCharger(10)));
		MMApi.addUpgrade(MMItems.upgrade_blast, new Upgrade(new UpgradeBlast(100)));
		MMApi.addUpgrade(MMItems.upgrade_brewing, new Upgrade(new UpgradeBrewing(10)));
	}
	
	public void initInteractingUpgrades(){
		MMApi.addInteractingUpgrade(MMItems.interacting_transfer, new InteractingUpgrade(new UpgradeTransfer()));
		MMApi.addInteractingUpgrade(MMItems.interacting_break, new InteractingUpgrade(new UpgradeBreak()));
		MMApi.addInteractingUpgrade(MMItems.interacting_vacuum, new InteractingUpgrade(new UpgradeVacuum()));
		MMApi.addInteractingUpgrade(MMItems.interacting_place, new InteractingUpgrade(new UpgradePlace()));
		MMApi.addInteractingUpgrade(MMItems.interacting_fertilize, new InteractingUpgrade(new UpgradeFertilize(10)));
		MMApi.addInteractingUpgrade(MMItems.interacting_elevator, new InteractingUpgrade(new UpgradeElevator()));
		MMApi.addInteractingUpgrade(MMItems.interacting_update, new InteractingUpgrade(new UpgradeUpdate()));
		MMApi.addInteractingUpgrade(MMItems.interacting_miner, new InteractingUpgrade(new UpgradeMiner(500)));
	}
	
	public void initGeneratorUpgrades(){
		MMApi.addGeneratorUpgrade(MMItems.generator_base, new GeneratorUpgrade(new UpgradeBase()));
	}
}