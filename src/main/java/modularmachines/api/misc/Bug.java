/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc;

public class Bug {
	public Bug(String bug, double modVersion, String mcVersion, String modName){
		this.bug = bug;
		this.mcVersion = mcVersion;
		this.modName = modName;
		this.modVersion = modVersion;
	}
	public String bug;
	public String modName;
	public double modVersion;
	public String mcVersion;
}