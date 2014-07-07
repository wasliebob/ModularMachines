package modularmachines.api.heat.interfaces;

import modularmachines.api.heat.HeatStorage;

public interface IHeatTransport {
	public HeatStorage getHeatStorage();
	public boolean canAdd(int energy);
	public int calculateSending(IHeatTransport te);
	public int calculateSending(IHeatedMachine te);
}