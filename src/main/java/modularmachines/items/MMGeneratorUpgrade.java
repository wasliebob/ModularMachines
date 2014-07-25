package modularmachines.items;

import java.awt.Color;

import modularmachines.api.upgrades.IInteractingUpgrade;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMGeneratorUpgrade extends Item implements IInteractingUpgrade{

	public MMGeneratorUpgrade(String name, Color color) {
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabUpgrades);
		
		this.name = name;
		this.color = color;
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	public Color color;
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id)
	{
    	return color.getRGB();
	}

	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "upgrade_generator");
	}

	@Override
	public Color getColor() {
		return color;
	}
}