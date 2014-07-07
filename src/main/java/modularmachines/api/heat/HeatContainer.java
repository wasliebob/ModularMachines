package modularmachines.api.heat;

import modularmachines.api.heat.enums.EnumHeatTiers;

public class HeatContainer {
	public HeatContainer(EnumHeatTiers capacity){
		this.capacity = capacity;
	}
	public int heat;
	public EnumHeatTiers capacity;
	
	public int getMaxHeat(){
		return this.capacity.getValue();
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