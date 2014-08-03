/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.upgrades.base;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.misc.PotionStorage;
import modularmachines.api.recipes.MMRecipeHelper;
import modularmachines.api.upgrades.IMachineAction;
import modularmachines.blocks.tiles.TilePotionTank;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import wasliecore.helpers.MathHelper;

public class UpgradeBrewing implements IMachineAction{
	public UpgradeBrewing(int cost){
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
			if(input != null && MMRecipeHelper.brewing.containsKey(input.getItem())){
				Potion result = MMRecipeHelper.brewing.get(input.getItem());
				
				doPotionStuff(result, base);

			}
		}
	}
	
	public void doPotionStuff(Potion potion, TileMachineBase base){
		ItemStack input = base.getStackInSlot(0);

		TilePotionTank storage = (TilePotionTank)base.getExpansion();
		PotionStorage tank = storage.getPotionStorage();
		if(tank.getPotion() == potion && tank.getAmount() + 30 <= tank.capacity){
			tank.increaseAmount(30);
			storage.getWorldObj().markBlockForUpdate(storage.xCoord, storage.yCoord, storage.zCoord);
			
			if(input.stackSize > 1)
				input.stackSize--;
			else
				base.stacks[0] = null;
			
			base.heat.decreaseEnergy(cost);
		}else if(tank.getPotion() == null){
			tank.setPotion(potion);
			tank.setAmount(30);
			storage.getWorldObj().markBlockForUpdate(storage.xCoord, storage.yCoord, storage.zCoord);	
			
			if(input.stackSize > 1)
				input.stackSize--;
			else
				base.stacks[0] = null;
			
			base.heat.decreaseEnergy(cost);
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

	@Override
	public boolean hasRequired(TileMachineBase base) {
		return base.expension != null && base.getExpansion() instanceof TilePotionTank;
	}
}