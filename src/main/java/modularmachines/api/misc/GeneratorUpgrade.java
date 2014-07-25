package modularmachines.api.misc;

import modularmachines.api.upgrades.IGeneratorAction;

public class GeneratorUpgrade {
	public GeneratorUpgrade(IGeneratorAction action){
		this.action = action;
	}
	public IGeneratorAction action;
}