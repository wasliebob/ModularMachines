/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMProgrammer extends Item{

	public MMProgrammer(String name) {
		setMaxStackSize(1);
		setUnlocalizedName(LibMod.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMain);
		this.name = name;		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;

	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "programmer");
	}
}