package modularmachines.api.misc.helpers;

import net.minecraft.item.ItemStack;

public class HeatContainerHelper{
	public static int getHeatStored(ItemStack stack){
		int stored = 0;
		if(hasCompound(stack))
			stored = stack.getTagCompound().getInteger("HEAT");
		return stored;
	}

	public static void setHeatStored(ItemStack stack, int heat){
		if(hasCompound(stack)){
			stack.getTagCompound().setInteger("HEAT", heat);
		}
	}

	public static void increaseHeatStored(ItemStack stack, int heat){
		int stored = getHeatStored(stack);
		if(hasCompound(stack)){
			stack.getTagCompound().setInteger("HEAT", stored + heat);
		}		
	}

	public static void decreaseHeatStored(ItemStack stack, int heat){
		int stored = getHeatStored(stack);
		if(hasCompound(stack) && stored  - heat >= 0){
			stack.getTagCompound().setInteger("HEAT", stored - heat);
		}	
	}
	
	public static boolean hasCompound(ItemStack stack){
		return stack.hasTagCompound();
	}
}