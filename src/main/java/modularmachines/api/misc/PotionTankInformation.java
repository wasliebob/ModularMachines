/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc;

import net.minecraft.potion.Potion;

public class PotionTankInformation {
	public PotionTankInformation(){
		potion = null;
		amount = 0;
		capacity = 0;
	}
	public Potion potion;
	public int amount;
	public int capacity;
	
	public void setPotion(Potion potion){
		this.potion = potion;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public void increaseAmount(int amount){
		this.amount += amount;
	}
	
	public void decreaseAmount(int amount){
		this.amount -= amount;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
}