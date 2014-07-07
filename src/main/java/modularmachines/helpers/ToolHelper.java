package modularmachines.helpers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ToolHelper {
	public static Block getBlockPointing(World world){
		Minecraft mc = Minecraft.getMinecraft();
		MovingObjectPosition ray = mc.objectMouseOver;
		if(ray != null){
			int blockX = ray.blockX;
			int blockY = ray.blockY;
			int blockZ = ray.blockZ;
			return world.getBlock(blockX, blockY, blockZ);
		}
		return null;
	}
	
	public static void setBlockToAir(World world){
		Minecraft mc = Minecraft.getMinecraft();
		MovingObjectPosition ray = mc.objectMouseOver;
		if(ray != null){
			int blockX = ray.blockX;
			int blockY = ray.blockY;
			int blockZ = ray.blockZ;
			world.setBlockToAir(blockX, blockY, blockZ);
		}
	}
	
	public static int getBlockMetaPointing(World world){
		Minecraft mc = Minecraft.getMinecraft();
		MovingObjectPosition ray = mc.objectMouseOver;
		if(ray != null){
			int blockX = ray.blockX;
			int blockY = ray.blockY;
			int blockZ = ray.blockZ;
			return world.getBlockMetadata(blockX, blockY, blockZ);
		}
		return 0;
	}
	
	public static void dropItem(ItemStack stack, World world, int x, int y, int z){
		Random rand = new Random();
    	ItemStack drop = stack;
		float rx = rand.nextFloat() * 0.8F + 0.1F;
    	float ry = rand.nextFloat() * 0.8F + 0.1F;
    	float rz = rand.nextFloat() * 0.8F + 0.1F;
    	
        EntityItem ei = new EntityItem(world, x + rx, y + ry, z + rz, drop);
        float factor = 0.05F;
        ei.motionX = rand.nextGaussian() * factor;
        ei.motionY = rand.nextGaussian() * factor + 0.2F;
        ei.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(ei);
	}
}