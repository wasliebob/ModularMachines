package modularmachines.main.init;

import java.awt.Color;

import modularmachines.items.MMInteractingUpgrade;
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
		initTools();
		initUpgrades();
		initInteractingUpgrades();
	}
	
	public void initUpgrades(){
		upgrade_furnace = new MMUpgrade("furnace", ColorHelper.getColorCodeFromColor(Color.red));
		upgrade_macerator = new MMUpgrade("macerator", ColorHelper.getColorCodeFromColor(Color.magenta));
		upgrade_charger = new MMUpgrade("charger", ColorHelper.getColorCodeFromColor(Color.cyan));
	}
	public static MMUpgrade upgrade_furnace;
	public static MMUpgrade upgrade_macerator;
	public static MMUpgrade upgrade_charger;

	public void initInteractingUpgrades(){
		interacting_transfer = new MMInteractingUpgrade("transfer", Color.blue);
		interacting_break = new MMInteractingUpgrade("break", Color.green);
		interacting_vacuum = new MMInteractingUpgrade("vacuum", Color.DARK_GRAY);
		interacting_place = new MMInteractingUpgrade("place", new Color(100, 0, 100));
		interacting_fertilize = new MMInteractingUpgrade("fertilize", new Color(0, 100, 0));
		interacting_elevator = new MMInteractingUpgrade("elevator", new Color(0, 100, 100));
	}
	public static MMInteractingUpgrade interacting_transfer;
	public static MMInteractingUpgrade interacting_break;
	public static MMInteractingUpgrade interacting_vacuum;
	public static MMInteractingUpgrade interacting_place;
	public static MMInteractingUpgrade interacting_fertilize;
	public static MMInteractingUpgrade interacting_elevator;
	
	public void initItems(){
		programmer = new MMProgrammer("programmer");
		input = new MMSpecialUpgrade("input", ColorHelper.getColorCodeFromColor(Color.cyan));
		output = new MMSpecialUpgrade("output", ColorHelper.getColorCodeFromColor(Color.red));
		screen = new MMSpecialUpgradeScreen();
		wrench = new MMWrench("modular wrench");
	}
	public static MMProgrammer programmer;
	public static MMSpecialUpgrade input;
	public static MMSpecialUpgrade output;
	public static MMSpecialUpgradeScreen screen;
	public static MMWrench wrench;

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