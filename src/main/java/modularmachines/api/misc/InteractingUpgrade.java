package modularmachines.api.misc;

import modularmachines.api.upgrades.IInteractingAction;

public class InteractingUpgrade {
	public InteractingUpgrade(IInteractingAction action){
		this.action = action;
	}
	public IInteractingAction action;
}