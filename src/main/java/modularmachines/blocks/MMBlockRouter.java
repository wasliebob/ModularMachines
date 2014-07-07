package modularmachines.blocks;

import modularmachines.blocks.tiles.TileRouter;
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
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockRouter extends BlockContainer implements IWrenchable{

	public MMBlockRouter(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(MM.modName.toLowerCase() + "." + "block" + "." + name);
		this.name = name;
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileRouter();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) 
	{
		TileRouter te = (TileRouter)world.getTileEntity(x, y, z);
		
		if(!world.isRemote && te != null && player.getHeldItem() != null){
			if(player.getHeldItem().getItem() == MMItems.programmer){
				player.addChatComponentMessage(new ChatComponentText("Heat: " + te.heat.getHeat() + "/" + te.heat.getMaxHeat()));
			}else if(player.getHeldItem().getItem() == MMItems.input && te.output != ForgeDirection.getOrientation(side)){
				te.input = ForgeDirection.getOrientation(side);
				world.markBlockForUpdate(x, y, z);
			}else if(player.getHeldItem().getItem() == MMItems.output && te.input != ForgeDirection.getOrientation(side)){
				te.output = ForgeDirection.getOrientation(side);
				world.markBlockForUpdate(x, y, z);
			}
		}
		return true;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "core");
	}
}