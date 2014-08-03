/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc;

import modularmachines.api.upgrades.IGeneratorAction;

public class GeneratorUpgrade {
	public GeneratorUpgrade(IGeneratorAction action){
		this.action = action;
	}
	public IGeneratorAction action;
}