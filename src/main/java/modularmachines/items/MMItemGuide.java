package modularmachines.items;

import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wasliecore.interfaces.IWrench;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMItemGuide extends Item implements IWrench{

	public MMItemGuide() {
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + "guide");
		setCreativeTab(MMTabs.tabMain);
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}

	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "guide");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		player.openGui(MM.instance, 3, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return stack;
    }
	
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
    	super.onUpdate(stack, world, entity, par4, par5);
		if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
    	super.onCreated(stack, world, player);
    	if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    }
}