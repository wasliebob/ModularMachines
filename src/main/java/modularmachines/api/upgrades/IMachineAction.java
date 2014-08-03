/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.upgrades;

import modularmachines.api.classes.TileMachineBase;

public interface IMachineAction {
	/**
	 * Gets called on updateEntity.
	 * @param base
	 */
	public void onUpdate(TileMachineBase base);
		
	public void drainFuel(TileMachineBase base);
	public void addFuel(TileMachineBase base);
	
	public int getTimeLeft(TileMachineBase base);
	public int getStartTime(TileMachineBase base);
	public boolean hasRequired(TileMachineBase base);
}