/**
* This file is created by Wesley.
* Created on: 21 aug. 2014, at 21:09:54
* This file is part of Modular Machines (created by wasliebob)
 */
package modularmachines.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class MMBlockStairs extends BlockStairs{
	public MMBlockStairs(Block type, int meta){
		super(type, meta);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + type.getLocalizedName() + " stairs");
		setCreativeTab(MMTabs.tabDecoration);
		
		this.type = type;
		this.meta = meta;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	public Block type;
	public int meta;
	
	@Override
    public IIcon getIcon(int side, int meta){
        return this.type.getIcon(side, this.meta);
    }
}