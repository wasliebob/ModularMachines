package modularmachines.main.init;

import java.util.ArrayList;

import modularmachines.api.main.MMApi;
import modularmachines.api.misc.InteractingUpgrade;
import modularmachines.api.misc.Upgrade;
import modularmachines.api.recipes.MMMaceratorRecipe;
import modularmachines.upgrades.base.UpgradeCharger;
import modularmachines.upgrades.base.UpgradeFurnace;
import modularmachines.upgrades.base.UpgradeMacerator;
import modularmachines.upgrades.interacting.UpgradeBreak;
import modularmachines.upgrades.interacting.UpgradeElevator;
import modularmachines.upgrades.interacting.UpgradeFertilize;
import modularmachines.upgrades.interacting.UpgradePlace;
import modularmachines.upgrades.interacting.UpgradeTransfer;
import modularmachines.upgrades.interacting.UpgradeVacuum;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import wasliecore.interfaces.IInitalization;

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
		initMaceratorRecipes();
		initUpgrades();
		initInteractingUpgrades();
	}
	
	public void initCraftingRecipes(){

	}
	
	public void initMaceratorRecipes(){
		ArrayList<ItemStack> output;
		output = OreDictionary.getOres("dustIron");
		if(output != null && output.size() > 0){
			MMMaceratorRecipe.addRecipe(Items.iron_ingot, output.get(0));
			MMMaceratorRecipe.addRecipe(Item.getItemFromBlock(Blocks.iron_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustGold");
		if(output != null && output.size() > 0){
			MMMaceratorRecipe.addRecipe(Items.gold_ingot, output.get(0));
			MMMaceratorRecipe.addRecipe(Item.getItemFromBlock(Blocks.gold_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustDiamond");
		if(output != null && output.size() > 0){
			MMMaceratorRecipe.addRecipe(Items.diamond, output.get(0));
			MMMaceratorRecipe.addRecipe(Item.getItemFromBlock(Blocks.diamond_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
		
		output = OreDictionary.getOres("dustCoal");
		if(output != null && output.size() > 0){
			MMMaceratorRecipe.addRecipe(Items.coal, output.get(0));
			MMMaceratorRecipe.addRecipe(Item.getItemFromBlock(Blocks.coal_ore), new ItemStack(output.get(0).getItem(), 1, output.get(0).getItemDamage()));
		}
	}
	
	public void initFluidFuels(){
		MMApi.addHeatFuel(Blocks.lava, 10);
	}
	
	public void initFuels(){
		MMApi.addFuel(Items.coal, 10);
		MMApi.addFuel(Item.getItemFromBlock(Blocks.log), 5);
		MMApi.addFuel(Item.getItemFromBlock(Blocks.coal_block), 10*9);
	}
	
	public void initUpgrades(){
		MMApi.addUpgrade(MMItems.upgrade_furnace, new Upgrade(new UpgradeFurnace(10)));
		MMApi.addUpgrade(MMItems.upgrade_macerator, new Upgrade(new UpgradeMacerator(10)));
		MMApi.addUpgrade(MMItems.upgrade_charger, new Upgrade(new UpgradeCharger(10)));
	}
	
	public void initInteractingUpgrades(){
		MMApi.addInteractingUpgrade(MMItems.interacting_transfer, new InteractingUpgrade(new UpgradeTransfer()));
		MMApi.addInteractingUpgrade(MMItems.interacting_break, new InteractingUpgrade(new UpgradeBreak()));
		MMApi.addInteractingUpgrade(MMItems.interacting_vacuum, new InteractingUpgrade(new UpgradeVacuum()));
		MMApi.addInteractingUpgrade(MMItems.interacting_place, new InteractingUpgrade(new UpgradePlace()));
		MMApi.addInteractingUpgrade(MMItems.interacting_fertilize, new InteractingUpgrade(new UpgradeFertilize()));
		MMApi.addInteractingUpgrade(MMItems.interacting_elevator, new InteractingUpgrade(new UpgradeElevator()));
	}
}