/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;
import wasliecore.helpers.Utils;
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
			int side = Utils.getTargetBlockSide(player);
			ForgeDirection dir = ForgeDirection.getOrientation(side);
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
						if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN){
							for(int xX = -1; xX <= 1; xX++){
								for(int zZ = -1; zZ <= 1; zZ++){
									Block b = world.getBlock(x + xX, y, z + zZ);
									if(b != null && b != Blocks.bedrock && !(b instanceof BlockLiquid)){
										for(ItemStack stack : b.getDrops(world, x - 1, y, z, e.blockMetadata, 0))
											e.drops.add(stack);
										world.setBlockToAir(x + xX, y, z + zZ);
									}
								}
							}
						}else if(dir == ForgeDirection.EAST || dir == ForgeDirection.WEST){
							for(int yY = -1; yY <= 1; yY++){
								for(int zZ = -1; zZ <= 1; zZ++){
									Block b = world.getBlock(x, y + yY, z + zZ);
									if(b != null && b != Blocks.bedrock && !(b instanceof BlockLiquid)){
										for(ItemStack stack : b.getDrops(world, x - 1, y, z, e.blockMetadata, 0))
											e.drops.add(stack);
										world.setBlockToAir(x, y + yY, z + zZ);
									}
								}
							}
						}else if(dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH){
							for(int xX = -1; xX <= 1; xX++){
								for(int yY = -1; yY <= 1; yY++){
									Block b = world.getBlock(x + xX, y + yY, z);
									if(b != null && b != Blocks.bedrock && !(b instanceof BlockLiquid)){
										for(ItemStack stack : b.getDrops(world, x - 1, y, z, e.blockMetadata, 0))
											e.drops.add(stack);
										world.setBlockToAir(x + xX, y + yY, z);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}