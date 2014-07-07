package modularmachines.upgrades.base;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.recipes.MMMaceratorRecipe;
import modularmachines.api.upgrades.IMachineAction;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import wasliecore.helpers.MathHelper;

public class UpgradeMacerator implements IMachineAction{
	public UpgradeMacerator(int cost){
		this.time = MathHelper.secondToTick(5);
		this.cost = cost;
	}
	public int time;
	public int cost;
	
	@Override
	public void onUpdate(TileMachineBase base){		
		if(base.heat.getHeat() >= cost){
			if(time > 0)
				time--;
		
			if(time <= 0){
				smelt(base);
				time = MathHelper.secondToTick(5);
			}
		}
	}

	public void smelt(TileMachineBase base){
		if(Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null){
			ItemStack input = base.getStackInSlot(0);
			ItemStack out = base.getStackInSlot(2);
			if(input != null && MMMaceratorRecipe.containsItem(input.getItem())){
				ItemStack result = MMMaceratorRecipe.getRecipe(input.getItem());
				
				if(out == null){
					base.stacks[2] = result;
				}else if(out != null && out.stackSize < 64 && result.getItem() == out.getItem() && result.getItemDamage() == out.getItemDamage()){
					base.stacks[2].stackSize++;
				}
				
				if(input.stackSize > 1)
					input.stackSize--;
				else
					base.stacks[0] = null;
				
				base.heat.decreaseEnergy(cost);
			}
		}
	}
	
	@Override
	public void drainFuel(TileMachineBase base){
		
	}

	@Override
	public void addFuel(TileMachineBase base){
		
	}
	
	@Override
	public int getTimeLeft(TileMachineBase base){
		return this.time;
	}
	
	@Override
	public int getStartTime(TileMachineBase base){
		return MathHelper.secondToTick(5);
	}
}