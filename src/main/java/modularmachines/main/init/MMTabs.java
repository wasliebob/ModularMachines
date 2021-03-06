/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMTabs {
    public static CreativeTabs tabMain = new CreativeTabs("tabModularMachinesMain"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return Item.getItemFromBlock(MMBlocks.core_machine);
		}
	};
	
    public static CreativeTabs tabUpgrades = new CreativeTabs("tabModularMachinesUpgrades"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return MMItems.interacting_transfer;
		}
	};
	
    public static CreativeTabs tabMaterials = new CreativeTabs("tabModularMachinesMaterials"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return MMItems.ingot_copper;
		}
	};
	
    public static CreativeTabs tabPotionOrbs = new CreativeTabs("tabModularMachinesPotionOrbs"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return MMItems.orb_empty;
		}
	};
	
    public static CreativeTabs tabDecoration = new CreativeTabs("tabModularMachinesDecoration"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return Item.getItemFromBlock(MMBlocks.brick_black);
		}
	};
}