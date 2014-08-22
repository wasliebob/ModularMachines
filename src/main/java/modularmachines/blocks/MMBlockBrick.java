/**
* This file is created by wasliebob.
* Created on: 6 aug. 2014, at 13:09:46
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class MMBlockBrick extends Block{
	public MMBlockBrick(String name, String texture) {
		super(Material.rock);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabDecoration);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);

		this.texture = texture;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String texture;
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + texture);
	}
}