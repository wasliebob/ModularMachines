package modularmachines.blocks.integration;

import wasliecore.interfaces.IWrenchable;
import modularmachines.blocks.tiles.intergration.TilePowerConverterIC2;
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

public class MMBlockPowerConverterIC2 extends BlockContainer implements IWrenchable{

	public MMBlockPowerConverterIC2(String name) {
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
		return new TilePowerConverterIC2();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) 
	{
		TilePowerConverterIC2 te = (TilePowerConverterIC2)world.getTileEntity(x, y, z);
		
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
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "core");
	}
}