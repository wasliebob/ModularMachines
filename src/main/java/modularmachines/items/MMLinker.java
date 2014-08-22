/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wasliecore.helpers.MathHelper;
import wasliecore.helpers.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMLinker extends Item{

	public MMLinker() {
		setMaxStackSize(1);
		setUnlocalizedName(LibMod.modName.toLowerCase() + "." + "item" + "." + "linker");
		setCreativeTab(MMTabs.tabMain);
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}

	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "linker");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote && player.isSneaking()){
			TileEntity te = Utils.getTargetTile(player);
			if(te != null && te instanceof TilePotionTank){
				TilePotionTank tank = (TilePotionTank)te;
				int x = tank.xCoord;
				int y = tank.yCoord;
				int z = tank.zCoord;
				
				stack.getTagCompound().setInteger("posX", x);
				stack.getTagCompound().setInteger("posY", y);
				stack.getTagCompound().setInteger("posZ", z);

			}
		}else if(!world.isRemote && !player.isSneaking()){
			int x = stack.getTagCompound().getInteger("posX");
			int y = stack.getTagCompound().getInteger("posY");
			int z = stack.getTagCompound().getInteger("posZ");
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null && tile instanceof TilePotionTank){
				TilePotionTank te = (TilePotionTank)tile;
				if(te.tank.getPotion() != null && te.tank.getAmount() - 10 >= 0){
					Potion p = Potion.potionTypes[te.tank.potion.id];
					if(!te.tank.potion.isInstant()){	
						if(player.getActivePotionEffect(p) == null){
							player.addPotionEffect(new PotionEffect(te.tank.potion.id, MathHelper.secondToTick(10)));
						}else if(player.getActivePotionEffect(p) != null){
							player.addPotionEffect(new PotionEffect(te.tank.potion.id, player.getActivePotionEffect(p).getDuration() + MathHelper.secondToTick(10)));
						}
					}else{
						player.addPotionEffect(new PotionEffect(te.tank.potion.id, 1));
					}
					te.tank.decreaseAmount(10);
					
					if(te.tank.getAmount() == 0)
						te.tank.setPotion(null);
					
					world.markBlockForUpdate(x, y, z);
				}
			}
		}
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