/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc;

import modularmachines.api.upgrades.IInteractingAction;

public class InteractingUpgrade {
	public InteractingUpgrade(IInteractingAction action){
		this.action = action;
	}
	public IInteractingAction action;
}