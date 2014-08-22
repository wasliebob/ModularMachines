/**
* This file is created by wasliebob.
* Created on: 4 aug. 2014, at 09:33:18
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import modularmachines.blocks.tiles.TilePotionTube;
import modularmachines.helpers.RenderingHelper;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockPotionTube extends BlockContainer implements IWrenchable{

	public MMBlockPotionTube() {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + "potion tube");		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void setBlockBoundsBasedOnState(IBlockAccess acces, int x, int y, int z){
		TileEntity tile = acces.getTileEntity(x, y, z);
		
		if(tile instanceof TilePotionTube){
			TilePotionTube te = (TilePotionTube)tile;
			float minX = 0.4F;
			float minY = 0.4F;
			float minZ = 0.4F;
			
			float maxX = 0.6F;
			float maxY = 0.6F;
			float maxZ = 0.6F;
			
			if(te.coords.isEmpty()){

			}else{
				if(te.coords.containsKey(ForgeDirection.UP)){
					maxY = 1F;
				}
				
				if(te.coords.containsKey(ForgeDirection.DOWN)){
					minY = 0.4F;
				}
				
				if(te.coords.containsKey(ForgeDirection.SOUTH)){
					maxZ = 1F;
				}
				
				if(te.coords.containsKey(ForgeDirection.NORTH)){
					minZ = 0.4F;
				}
				
				if(te.coords.containsKey(ForgeDirection.EAST)){
					maxX = 1F;
				}
				
				if(te.coords.containsKey(ForgeDirection.WEST)){
					minX = 0.4F;
				}
			}
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TilePotionTube();
	}
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "core_side");
	}
	
	@Override
    public int getRenderType(){
        return RenderingHelper.render_cable_potion;
    }
	
	@Override
    public boolean renderAsNormalBlock(){
        return false;
    }
	
	@Override
    public boolean isOpaqueCube(){
        return false;
    }
}