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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMItem extends Item{
	public MMItem(int stackSize, String name, String texture, CreativeTabs tab){
		setMaxStackSize(stackSize);
		setUnlocalizedName(LibMod.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(tab);
		this.name = name;
		this.texture = texture;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	String texture;
	
	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + texture);
	}
}