package modularmachines.main.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMTabs {
    public static CreativeTabs tabMain = new CreativeTabs("tabModularMachines") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return Item.getItemFromBlock(MMBlocks.core_machine);}}; 
}