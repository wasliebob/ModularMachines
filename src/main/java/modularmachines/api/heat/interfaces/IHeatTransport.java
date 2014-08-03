/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.interfaces;

import modularmachines.api.heat.HeatStorage;

public interface IHeatTransport {
	public HeatStorage getHeatStorage();
	public boolean canAdd(int energy);
	public int calculateSending(IHeatTransport te);
	public int calculateSending(IHeatedMachine te);
}