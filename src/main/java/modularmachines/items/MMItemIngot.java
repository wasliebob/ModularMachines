/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMItemIngot extends Item{

	public MMItemIngot(String name, int color, Block input) {
		setMaxStackSize(64);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "ingot" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMaterials);
		
		this.color = color;
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre("ingot" + name, this);
		
		if(input != null)
			GameRegistry.addSmelting(input, new ItemStack(this), 0);
	}
	int color;
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id){
    	return color;
	}
	
	@Override
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "ingot");
	}
}