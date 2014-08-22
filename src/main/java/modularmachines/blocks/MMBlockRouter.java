/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import modularmachines.api.guide.IGuided;
import modularmachines.blocks.tiles.TileRouter;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
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

public class MMBlockRouter extends BlockContainer implements IWrenchable, IGuided{

	public MMBlockRouter(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);
		this.name = name;
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	IIcon top;
	IIcon bottom;
	IIcon side;
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileRouter();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) 
	{
		TileRouter te = (TileRouter)world.getTileEntity(x, y, z);
		
		if(!world.isRemote && te != null && player.getHeldItem() != null && player.getHeldItem().getItem() != MMItems.wrench && player.getHeldItem().getItem() != MMItems.guide){
			if(player.getHeldItem().getItem() == MMItems.input && te.input == null && te.output != ForgeDirection.getOrientation(side)){
				te.input = ForgeDirection.getOrientation(side);
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
				world.markBlockForUpdate(x, y, z);
			}else if(player.getHeldItem().getItem() == MMItems.output && te.output == null && te.input != ForgeDirection.getOrientation(side)){
				te.output = ForgeDirection.getOrientation(side);
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
				world.markBlockForUpdate(x, y, z);
			}
		}
        return false;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "router_top");
		bottom = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "router_bottom");
		side = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "router_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int fside, int metadata){
		ForgeDirection dir = ForgeDirection.getOrientation(fside);
		if(dir == ForgeDirection.UP)
			return top;
		else if(dir == ForgeDirection.DOWN)
			return bottom;
		else
			return side;
	}
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        int side = target.sideHit;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        TileEntity te = world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        if(te != null && te instanceof TileRouter){
        	TileRouter tr = (TileRouter)te;
        	if(dir == tr.input)
        		return new ItemStack(MMItems.input);
        	else if(dir == tr.output)
        		return new ItemStack(MMItems.output);
        }
        return super.getPickBlock(target, world, x, y, z);
    }
	
	@Override
	public String getKey(){
		return "Heat Router";
	}
}