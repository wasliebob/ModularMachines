/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import modularmachines.api.guide.IGuided;
import modularmachines.blocks.tiles.TileTransporter;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockTransporter extends BlockContainer implements IWrenchable, IGuided{
	public MMBlockTransporter(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(MM.modName.toLowerCase() + "." + "block" + "." + name);

		this.name = name;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	IIcon side;
	IIcon top;
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileTransporter();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileTransporter ti = (TileTransporter)world.getTileEntity(x, y, z);
		if(ti != null && !player.isSneaking() && player.getHeldItem() != null && player.getHeldItem().getItem() != MMItems.programmer && player.getHeldItem().getItem() != MMItems.wrench && player.getHeldItem().getItem() != MMItems.guide){
			Item heldItem = player.getHeldItem().getItem();
			ForgeDirection dir = ForgeDirection.getOrientation(side);
			if(heldItem == MMItems.input && ti.input == null && ti.output != dir){
				ti.input = dir;
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
			}else if(heldItem == MMItems.output && ti.output == null && ti.input != dir){
				ti.output = dir;
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
			}
		}
		return false;
    }
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        int side = target.sideHit;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        TileEntity te = world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        if(te != null && te instanceof TileTransporter){
        	TileTransporter tr = (TileTransporter)te;
        	if(dir == tr.input)
        		return new ItemStack(MMItems.input);
        	else if(dir == tr.output)
        		return new ItemStack(MMItems.output);
        }
        return super.getPickBlock(target, world, x, y, z);
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(MM.modName.toLowerCase() + ":" + "transporter_top");
		side = ir.registerIcon(MM.modName.toLowerCase() + ":" + "transporter_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int fside, int metadata){
		ForgeDirection dir = ForgeDirection.getOrientation(fside);
		if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
			return top;
		else
			return side;
	}

	@Override
	public String getKey() {
		return "Transporter";
	}
}