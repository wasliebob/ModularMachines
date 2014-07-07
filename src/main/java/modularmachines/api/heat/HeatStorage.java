package modularmachines.api.heat;

public class HeatStorage {
	public HeatStorage(int capacity){
		this.capacity = capacity;
	}
	
	public HeatStorage(int capacity, int transfer){
		this.capacity = capacity;
		this.transfer = transfer;
	}
	public int heat;
	public int capacity;
	public int transfer;

	public int getTransfer(){
		return this.transfer;
	}
	
	public void setTransfer(int transfer){
		this.transfer = transfer;
	}
	
	public int getMaxHeat(){
		return this.capacity;
	}
	
	public void setMaxHeat(int capacity){
		this.capacity = capacity;
	}
	
	public int getHeat(){
		return this.heat;
	}
	
	public void setHeat(int heat){
		this.heat = heat;
	}
	
	
	public void increaseHeat(int energy){
		this.heat = this.heat + energy;
	}
	
	public void decreaseEnergy(int energy){
		this.heat = this.heat - energy;
	}
}