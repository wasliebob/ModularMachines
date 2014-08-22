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
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMItemNugget extends Item{
	public MMItemNugget(String name, int color) {
		setMaxStackSize(64);
		setUnlocalizedName(LibMod.modName.toLowerCase() + "." + "nugget" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMaterials);
		
		this.color = color;
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre("nugget" + name, this);
		
		if(OreDictionary.getOres("ingot" + name).size() > 0){
			GameRegistry.addShapedRecipe(OreDictionary.getOres("ingot" + name).get(0), new Object[]{
					"XXX",
					"XXX",
					"XXX",
					'X', this});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(this, 9, 0), "ingot" + name));
		}
	}
	int color;
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id){
    	return color;
	}
	
	@Override
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "nugget");
	}
}