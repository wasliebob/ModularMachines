/**
* This file is created by Wesley.
* Created on: 22 aug. 2014, at 09:27:30
* This file is part of Modular Machines (created by wasliebob)
 */
package modularmachines.blocks;

import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockSlab extends BlockSlab{
	public MMBlockSlab(Block type, int meta){
		super(false, type.getMaterial());
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + type.getLocalizedName() + " slab");
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

	@Override
	public String func_150002_b(int f1) {
		return null;
	}
}