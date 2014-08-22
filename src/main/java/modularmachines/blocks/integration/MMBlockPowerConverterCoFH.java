/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.integration;

import wasliecore.interfaces.IWrenchable;
import modularmachines.blocks.tiles.intergration.TilePowerConverterCoFH;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockPowerConverterCoFH extends BlockContainer implements IWrenchable{

	public MMBlockPowerConverterCoFH(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);
		this.name = name;
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TilePowerConverterCoFH();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) 
	{
		TilePowerConverterCoFH te = (TilePowerConverterCoFH)world.getTileEntity(x, y, z);
		
		if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() == MMItems.programmer){
			if(!world.isRemote && !player.isSneaking()){
				player.addChatComponentMessage(new ChatComponentText("Heat: " + te.heat.getHeat() + "/" + te.heat.getMaxHeat()));
			}
		}
		return true;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
        blockIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "core");
	}
}