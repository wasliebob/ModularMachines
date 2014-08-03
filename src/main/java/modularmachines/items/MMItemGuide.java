/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.main.MMInteractingUpgrades;
import modularmachines.api.upgrades.IInteractingAction;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.helpers.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMItemGuide extends Item{
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
		if(!player.isSneaking()){
			player.openGui(MM.instance, 3, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}else{
			Block block = Utils.getTargetBlock(player);
			if(block != null && block instanceof IGuided){
				TileEntity te = Utils.getTargetTile(player);
				IGuided guided = (IGuided)block;
				if(te != null && te instanceof TileInteracting){
					TileInteracting ti = (TileInteracting)te;
					ForgeDirection dir = ForgeDirection.getOrientation(Utils.getTargetBlockSide(player));
					if(dir == ti.upgradeSide){
						IInteractingAction action = MMInteractingUpgrades.getUpgrade(ti.upgrade).action;
						if(action != null && action instanceof IGuided){
							String key = ((IGuided)action).getKey();
							if(key != null){
								player.openGui(MM.instance, 7, world, Utils.getTargetX(player), Utils.getTargetY(player), Utils.getTargetZ(player));
							}else{
								openGuide(guided, player, world);
							}
						}else{
							openGuide(guided, player, world);
						}
					}else{
						openGuide(guided, player, world);
					}
				}else{
					openGuide(guided, player, world);
				}
			}
		}
		return stack;
    }
	
	public void openGuide(IGuided guided, EntityPlayer player, World world){
		if(guided.getKey() != null)
			player.openGui(MM.instance, 6, world, Utils.getTargetX(player), Utils.getTargetY(player), Utils.getTargetZ(player));
	}
}