/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 15:31:14
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import java.util.List;

import modularmachines.entities.projectiles.ProjectilePotionOrb;
import modularmachines.helpers.PotionHelper;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMItemPotionOrb extends Item{
	public MMItemPotionOrb(){
		setMaxStackSize(1);
		setUnlocalizedName(LibMod.modName.toLowerCase() + "." + "item" + "." + "potion orb");
		setCreativeTab(MMTabs.tabPotionOrbs);
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	public IIcon icon;
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id){
		if(PotionHelper.getPotion(stack.getItemDamage()) != null){
			return PotionHelper.getPotion(stack.getItemDamage()).getLiquidColor();
		}
		return 0;
	}
	
	@Override
    public void registerIcons(IIconRegister ir) {
		itemIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "orb");
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id){
		if(PotionHelper.getPotionList().get(stack.getItemDamage()) != null){
			list.add(EnumChatFormatting.RED + "Contains: " + EnumChatFormatting.GRAY + StatCollector.translateToLocal(PotionHelper.getPotionList().get(stack.getItemDamage()).getName()));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void getSubItems(Item item, CreativeTabs tabs, List list){
    	for(int i = 0; i < PotionHelper.getPotionList().size(); i++){
    		Potion potion = PotionHelper.getPotionList().get(i);
    		if(potion != null){
    			ItemStack stack = new ItemStack(item, 1, i);
    			list.add(stack);
    		}
    	}
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote){
			if(PotionHelper.getPotion(stack.getItemDamage()) != null){
	    		ProjectilePotionOrb orb = new ProjectilePotionOrb(world, player);
	    		orb.setPotion(PotionHelper.getPotion(stack.getItemDamage()));
	    		world.spawnEntityInWorld(orb);

	    		if(!player.capabilities.isCreativeMode){
					if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
					else
						player.setCurrentItemOrArmor(0, null);
	    		}
			}
		}
		return stack;
	}
}