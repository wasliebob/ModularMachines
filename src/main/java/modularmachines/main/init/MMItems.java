package modularmachines.main.init;

import java.awt.Color;

import modularmachines.items.MMInteractingUpgrade;
import modularmachines.items.MMItem;
import modularmachines.items.MMItemGuide;
import modularmachines.items.MMItemIngot;
import modularmachines.items.MMLinker;
import modularmachines.items.MMProgrammer;
import modularmachines.items.MMSpecialUpgrade;
import modularmachines.items.MMSpecialUpgradeScreen;
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
		initTools();
		initUpgrades();
		initInteractingUpgrades();
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

	public void initItems(){
		programmer = new MMProgrammer("programmer");
		input = new MMSpecialUpgrade("input", ColorHelper.getColorCodeFromColor(Color.cyan));
		output = new MMSpecialUpgrade("output", ColorHelper.getColorCodeFromColor(Color.red));
		expension = new MMSpecialUpgrade("expension", ColorHelper.getColorCodeFromColor(Color.magenta));
		screen = new MMSpecialUpgradeScreen();
		guide = new MMItemGuide();
		linker = new MMLinker();
		wrench = new MMWrench("modular wrench");
		crystal_energy = new MMItem(1, "energy crystal", "crystal_energy");
		stick_iron = new MMItem(64, "iron stick", "stick_iron");
		upgrade_empty = new MMItem(64, "empty upgrade", "upgrade");
	}
	public static MMProgrammer programmer;
	public static MMSpecialUpgrade input;
	public static MMSpecialUpgrade output;
	public static MMSpecialUpgrade expension;
	public static MMSpecialUpgradeScreen screen;
	public static MMWrench wrench;
	public static MMItem crystal_energy;
	public static MMItem stick_iron;
	public static MMItemGuide guide;
	public static MMLinker linker;
	public static MMItem upgrade_empty;
	
	public void initIngots(){
		ingot_copper = new MMItemIngot("Copper", new Color(210, 105, 30).getRGB(), MMBlocks.ore_copper);
		ingot_tin = new MMItemIngot("Tin", new Color(255, 245, 238).getRGB(), MMBlocks.ore_tin);
		ingot_bronze = new MMItemIngot("Bronze", new Color(218, 165, 32).getRGB(), null);
	}
	public static MMItemIngot ingot_copper;
	public static MMItemIngot ingot_tin;
	public static MMItemIngot ingot_bronze;

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