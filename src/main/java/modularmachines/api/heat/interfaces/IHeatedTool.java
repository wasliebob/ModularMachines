/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.interfaces;

import java.util.HashMap;

public interface IHeatedTool{
	public int getMaxModes();
	public HashMap<Integer, String> getModes();
}