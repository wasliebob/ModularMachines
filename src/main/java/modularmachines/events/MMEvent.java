package modularmachines.events;

import modularmachines.api.heat.interfaces.IHeatedTool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMEvent {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onBlockMined(BlockEvent.HarvestDropsEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		World world = e.world;
		int x = e.x;
		int y = e.y;
		int z = e.z;
		
		if(player != null){
			ItemStack held = player.getHeldItem();
			if(held != null && held.getItem() instanceof IHeatedTool){
				IHeatedTool tool = (IHeatedTool)held.getItem();
				String mode = tool.getModes().get(held.getItemDamageForDisplay());
				if(mode != null){
					if(mode == "Smelt"){
						ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(e.block, 1, e.blockMetadata));
						if(result != null){
							e.drops.clear();
							e.drops.add(result);
						}
					}else if(mode == "Silk"){
						if(e.block.canSilkHarvest(e.world, player, e.x, e.y, e.z, e.blockMetadata)){
							e.drops.clear();
							e.drops.add(new ItemStack(e.block, 1, e.blockMetadata));
						}
					}else if(mode == "Timber"){
						for(int i = 0; i < 10; i++){
							Block up = world.getBlock(x, y + i, z);
							Block down = world.getBlock(x, y - i, z);
							
							if(up != null && up.isWood(world, x, y + i, z)){
								for(ItemStack stack : up.getDrops(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0))
									e.drops.add(stack);
								world.setBlock(x, y + i, z, Blocks.air);
							}
							
							if(down != null && down.isWood(world, x, y - i, z)){
								for(ItemStack stack : down.getDrops(world, x, y - i, z, world.getBlockMetadata(x, y - i, z), 0))
									e.drops.add(stack);
								world.setBlock(x, y - i, z, Blocks.air);
							}
						}
					}else if(mode == "Area"){
						Block left = world.getBlock(x - 1, y, z);
						Block right = world.getBlock(x + 1, y, z);
						
						Block back = world.getBlock(x, y, z - 1);
						Block front = world.getBlock(x, y, z + 1);
						
						Block lback = world.getBlock(x - 1, y, z - 1);
						Block rback = world.getBlock(x + 1, y, z - 1);
						
						Block lfront = world.getBlock(x - 1, y, z + 1);
						Block rfront = world.getBlock(x + 1, y, z + 1);

						if(left != null && left != Blocks.bedrock && !(left instanceof BlockLiquid)){
							for(ItemStack stack : left.getDrops(world, x - 1, y, z, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x - 1, y, z);
						}
						
						if(right != null && right != Blocks.bedrock && !(right instanceof BlockLiquid)){
							for(ItemStack stack : left.getDrops(world, x + 1, y, z, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x + 1, y, z);
						}
						
						if(back != null && back != Blocks.bedrock && !(back instanceof BlockLiquid)){
							for(ItemStack stack : back.getDrops(world, x, y, z - 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x, y, z - 1);
						}
						
						if(front != null && front != Blocks.bedrock && !(front instanceof BlockLiquid)){
							for(ItemStack stack : front.getDrops(world, x, y, z + 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x, y, z + 1);
						}
						
						if(lback != null && lback != Blocks.bedrock && !(lback instanceof BlockLiquid)){
							for(ItemStack stack : lback.getDrops(world, x - 1, y, z - 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x - 1, y, z - 1);
						}
						
						if(rback != null && rback != Blocks.bedrock && !(rback instanceof BlockLiquid)){
							for(ItemStack stack : rback.getDrops(world, x + 1, y, z - 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x + 1, y, z - 1);
						}
						
						if(lfront != null && lfront != Blocks.bedrock && !(lfront instanceof BlockLiquid)){
							for(ItemStack stack : lfront.getDrops(world, x - 1, y, z + 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x - 1, y, z + 1);
						}
						
						if(rfront != null && rfront != Blocks.bedrock && !(rfront instanceof BlockLiquid)){
							for(ItemStack stack : rfront.getDrops(world, x + 1, y, z + 1, e.blockMetadata, 0))
								e.drops.add(stack);
							world.setBlockToAir(x + 1, y, z + 1);
						}
					}
				}
			}
		}
	}
}