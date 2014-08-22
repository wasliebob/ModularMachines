/**
* This file is created by wasliebob.
* Created on: 6 aug. 2014, at 13:09:46
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.itemblocks;

import modularmachines.api.misc.helpers.DyeHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MMItemBlockGlass extends ItemBlock{
	public MMItemBlockGlass(Block block) {
		super(block);
		this.block = block;
		
		setHasSubtypes(true);
	}
	public Block block;
	
	@Override
    public String getUnlocalizedName(ItemStack stack){
    	return this.block.getUnlocalizedName() + ": " + DyeHelper.getColorName(stack.getItemDamage());
    }
	
	@Override
    public int getMetadata(int side){
		return side;
    }
}