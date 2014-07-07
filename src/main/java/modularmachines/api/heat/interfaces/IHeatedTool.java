package modularmachines.api.heat.interfaces;

import java.util.HashMap;

public interface IHeatedTool{
	public int getMaxModes();
	public HashMap<Integer, String> getModes();
}