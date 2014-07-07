package modularmachines.items;

import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMUpgrade extends Item{

	public MMUpgrade(String name, int color) {
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMain);
		
		this.name = name;
		this.color = color;
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	int color;
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id)
	{
    	return color;
	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "upgrade");
	}
}