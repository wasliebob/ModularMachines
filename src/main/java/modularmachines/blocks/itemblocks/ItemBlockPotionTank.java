package modularmachines.blocks.itemblocks;

import java.util.List;

import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.helpers.PotionHelper;
import modularmachines.main.init.MMBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBlockPotionTank extends ItemBlock
{
	public ItemBlockPotionTank(Block block){
		super(block);
	}
    
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, 
            float hitX, float hitY, float hitZ, int metadata){
    	if(!world.setBlock(x, y, z, MMBlocks.tank_potion))
    		return false;
    	
    	if(world.getBlock(x, y, z) == MMBlocks.tank_potion){
    		if(world.getTileEntity(x, y, z) != null){
    			TileEntity tile = world.getTileEntity(x, y, z);
    			if(tile instanceof TilePotionTank){
    				TilePotionTank te = (TilePotionTank)tile;
    				if(stack.hasTagCompound()){
    					te.tank.setPotion(PotionHelper.getPotionFromID(stack.getTagCompound().getInteger("potion")));
    					te.tank.setAmount(stack.getTagCompound().getInteger("amount"));
    				}
    			}
    		}
    	}
    	return true;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{		
		if(stack.hasTagCompound()){
			list.add(StatCollector.translateToLocal(PotionHelper.getPotionFromID(stack.getTagCompound().getInteger("potion")).getName()) + ": " + stack.getTagCompound().getInteger("amount") +  "/" + stack.getTagCompound().getInteger("capacity"));
		}
	}
}