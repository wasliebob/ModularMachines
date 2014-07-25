package modularmachines.blocks;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.guide.IGuided;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockGenerator extends BlockContainer implements IWrenchable, IGuided{
	public MMBlockGenerator(String name) {
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
		return new TileGenerator();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) 
	{
		player.openGui(MM.instance, 5, world, x, y, z);
		return true;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "core");
	}

	@Override
	public String getKey() {
		return "Heat Generator";
	}
}