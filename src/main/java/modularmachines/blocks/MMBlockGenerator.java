/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockGenerator extends BlockContainer implements IWrenchable, IGuided{
	public MMBlockGenerator(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);
		this.name = name;
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	IIcon top;
	IIcon side;
	IIcon front;

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileGenerator();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) {
		player.openGui(MM.instance, 5, world, x, y, z);
		return true;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "generator_top");
		front = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "generator_front");
		side = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "generator_side");
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		ForgeDirection dir = DirectionHelper.getFace(living);
		world.setBlockMetadataWithNotify(x, y, z, dir.ordinal(), 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int fside, int meta){
		ForgeDirection dir = ForgeDirection.getOrientation(fside);
		
        if(meta == 0 && dir == ForgeDirection.SOUTH)
            return front;
        else if(fside == meta && fside > 1)
            return front;
        else if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
			return top;
		else
			return side;
	}

	@Override
	public String getKey() {
		return "Heat Generator";
	}
}