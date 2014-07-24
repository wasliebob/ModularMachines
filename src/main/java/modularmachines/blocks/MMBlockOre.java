package modularmachines.blocks;

import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockOre extends Block{
	public MMBlockOre(String name) {
		super(Material.rock);
		setHardness(1.0F);
		setHarvestLevel("pickaxe", 2);
		
		setCreativeTab(MMTabs.tabMaterials);
		setBlockName(MM.modName.toLowerCase() + "." + "ore" + "." + name.toLowerCase());
		
		this.name = name;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
		
		OreDictionary.registerOre("ore" + name, this);
	}
	String name;
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "ore_" + name.toLowerCase());
	}
}