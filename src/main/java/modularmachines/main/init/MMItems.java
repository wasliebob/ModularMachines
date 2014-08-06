/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import java.awt.Color;

import modularmachines.items.MMGeneratorUpgrade;
import modularmachines.items.MMInteractingUpgrade;
import modularmachines.items.MMItem;
import modularmachines.items.MMItemCrystal;
import modularmachines.items.MMItemDust;
import modularmachines.items.MMItemGuide;
import modularmachines.items.MMItemIngot;
import modularmachines.items.MMItemNugget;
import modularmachines.items.MMItemOrbEmpty;
import modularmachines.items.MMItemPotionOrb;
import modularmachines.items.MMItemScanner;
import modularmachines.items.MMLinker;
import modularmachines.items.MMProgrammer;
import modularmachines.items.MMSpecialUpgrade;
import modularmachines.items.MMUpgrade;
import modularmachines.items.MMWrench;
import modularmachines.items.tools.HeatedAxe;
import modularmachines.items.tools.HeatedPickaxe;
import modularmachines.items.tools.HeatedShovel;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import wasliecore.helpers.ColorHelper;
import wasliecore.interfaces.IInitalization;

public class MMItems implements IInitalization{
	@Override
	public void preInit(){
		initItems();
		initIngots();
		initDusts();
		initNuggets();
		initTools();
		initUpgrades();
		initInteractingUpgrades();
		initGeneratorUpgrades();
	}
	
	public void initUpgrades(){
		upgrade_furnace = new MMUpgrade("furnace", ColorHelper.getColorCodeFromColor(Color.red));
		upgrade_macerator = new MMUpgrade("macerator", ColorHelper.getColorCodeFromColor(Color.magenta));
		upgrade_charger = new MMUpgrade("charger", ColorHelper.getColorCodeFromColor(Color.cyan));
		upgrade_blast = new MMUpgrade("blast", ColorHelper.getColorCodeFromColor(Color.gray));
		upgrade_brewing = new MMUpgrade("brewing", ColorHelper.getColorCodeFromColor(Color.pink));
	}
	public static MMUpgrade upgrade_furnace;
	public static MMUpgrade upgrade_macerator;
	public static MMUpgrade upgrade_charger;
	public static MMUpgrade upgrade_blast;
	public static MMUpgrade upgrade_brewing;

	public void initInteractingUpgrades(){
		interacting_transfer = new MMInteractingUpgrade("transfer", Color.blue);
		interacting_break = new MMInteractingUpgrade("break", Color.green);
		interacting_vacuum = new MMInteractingUpgrade("vacuum", Color.darkGray);
		interacting_place = new MMInteractingUpgrade("place", new Color(100, 0, 100));
		interacting_fertilize = new MMInteractingUpgrade("fertilize", new Color(0, 100, 0));
		interacting_elevator = new MMInteractingUpgrade("elevator", new Color(0, 100, 100));
		interacting_update = new MMInteractingUpgrade("update", new Color(150, 0, 150));
		interacting_miner = new MMInteractingUpgrade("miner", new Color(150, 150, 150));
	}
	public static MMInteractingUpgrade interacting_transfer;
	public static MMInteractingUpgrade interacting_break;
	public static MMInteractingUpgrade interacting_vacuum;
	public static MMInteractingUpgrade interacting_place;
	public static MMInteractingUpgrade interacting_fertilize;
	public static MMInteractingUpgrade interacting_elevator;
	public static MMInteractingUpgrade interacting_update;
	public static MMInteractingUpgrade interacting_miner;

	public void initGeneratorUpgrades(){
		generator_base = new MMGeneratorUpgrade("base", Color.cyan);
	}
	public static MMGeneratorUpgrade generator_base;
	
	public void initItems(){
		programmer = new MMProgrammer("programmer");
		input = new MMSpecialUpgrade("input", ColorHelper.getColorCodeFromColor(Color.cyan));
		output = new MMSpecialUpgrade("output", ColorHelper.getColorCodeFromColor(Color.red));
		expension = new MMSpecialUpgrade("expension", ColorHelper.getColorCodeFromColor(Color.magenta));
		guide = new MMItemGuide();
		linker = new MMLinker();
		wrench = new MMWrench("modular wrench");
		crystal_energy = new MMItemCrystal("energy crystal", new Color(65, 105, 225).getRGB());
		stick_iron = new MMItem(64, "iron stick", "stick_iron");
		upgrade_empty = new MMItem(64, "empty upgrade", "upgrade");
		scanner = new MMItemScanner();
		orb_empty = new MMItemOrbEmpty();
		orb_potion = new MMItemPotionOrb();
	}
	public static MMProgrammer programmer;
	public static MMSpecialUpgrade input;
	public static MMSpecialUpgrade output;
	public static MMSpecialUpgrade expension;
	public static MMWrench wrench;
	public static MMItemCrystal crystal_energy;
	public static MMItem stick_iron;
	public static MMItemGuide guide;
	public static MMLinker linker;
	public static MMItem upgrade_empty;
	public static MMItemScanner scanner;
	public static MMItemOrbEmpty orb_empty;
	public static MMItemPotionOrb orb_potion;
	
	public void initIngots(){
		ingot_copper = new MMItemIngot("Copper", new Color(210, 105, 30).getRGB(), MMBlocks.ore_copper);
		ingot_tin = new MMItemIngot("Tin", new Color(255, 245, 238).getRGB(), MMBlocks.ore_tin);
		ingot_bronze = new MMItemIngot("Bronze", new Color(218, 165, 32).getRGB(), null);
		ingot_steel = new MMItemIngot("Steel", new Color(139, 139, 131).getRGB(), null);
	}
	public static MMItemIngot ingot_copper;
	public static MMItemIngot ingot_tin;
	public static MMItemIngot ingot_bronze;
	public static MMItemIngot ingot_steel;

	public void initNuggets(){
		nugget_copper = new MMItemNugget("Copper", new Color(210, 105, 30).getRGB());
		nugget_tin = new MMItemNugget("Tin", new Color(255, 245, 238).getRGB());
		nugget_bronze = new MMItemNugget("Bronze", new Color(218, 165, 32).getRGB());
		nugget_steel = new MMItemNugget("Steel", new Color(139, 139, 131).getRGB());
	}
	public static MMItemNugget nugget_copper;
	public static MMItemNugget nugget_tin;
	public static MMItemNugget nugget_bronze;
	public static MMItemNugget nugget_steel;

	public void initDusts(){
		//Vanilla Materials;
		dust_iron = new MMItemDust("Iron", new Color(190, 190, 190).getRGB());
		dust_gold = new MMItemDust("Gold", new Color(255, 215, 0).getRGB());
		dust_diamond = new MMItemDust("Diamond", new Color(135, 206, 250).getRGB());
		dust_emerald = new MMItemDust("Emerald", new Color(50, 205, 50).getRGB());
		dust_coal = new MMItemDust("Coal", new Color(105, 105, 105).getRGB());

		//Modular Machines Materials
		dust_copper = new MMItemDust("Copper", new Color(210, 105, 30).getRGB());
		dust_tin = new MMItemDust("Tin", new Color(255, 245, 238).getRGB());
		dust_bronze = new MMItemDust("Bronze", new Color(218, 165, 32).getRGB());
		dust_steel = new MMItemDust("Steel", new Color(139, 139, 131).getRGB());
	}
	public static MMItemDust dust_iron;
	public static MMItemDust dust_gold;
	public static MMItemDust dust_diamond;
	public static MMItemDust dust_emerald;
	public static MMItemDust dust_coal;

	public static MMItemDust dust_copper;
	public static MMItemDust dust_tin;
	public static MMItemDust dust_bronze;
	public static MMItemDust dust_steel;
	
	public void initTools(){
		heated = EnumHelper.addToolMaterial("HEATED", 4, -1, 10F, 0F, 0);
		pickaxe_heated = new HeatedPickaxe("heated pickaxe");
		shovel_heated = new HeatedShovel("heated shovel");
		axe_heated = new HeatedAxe("heated axe");
	}
	public static Item.ToolMaterial heated;
	
	public static HeatedPickaxe pickaxe_heated;
	public static HeatedShovel shovel_heated;
	public static HeatedAxe axe_heated;
	
	@Override
	public void init(){
	}
	
	@Override
	public void postInit(){		
	}
}