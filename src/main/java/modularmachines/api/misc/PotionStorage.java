package modularmachines.api.misc;

import net.minecraft.potion.Potion;

public class PotionStorage {
	public PotionStorage(int capacity, int transfer){
		this.capacity = capacity;
		this.transfer = transfer;
	}
	public int capacity;
	public Potion potion;
	public int amount;
	public int transfer;
	
	public void setPotion(Potion potion){
		this.potion = potion;
	}
	
	public Potion getPotion(){
		return this.potion;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public void increaseAmount(int amount){
		this.amount = this.amount + amount;
	}
	
	public void decreaseAmount(int amount){
		this.amount = this.amount - amount;
	}
	
	public int getTransfer(){
		return this.transfer;
	}
}