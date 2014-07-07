package modularmachines.api.misc;

import modularmachines.api.upgrades.IMachineAction;

public class Upgrade {
	public Upgrade(IMachineAction action){
		this.action = action;
	}
	public IMachineAction action;
}