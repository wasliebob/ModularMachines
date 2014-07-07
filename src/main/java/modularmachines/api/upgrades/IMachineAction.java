package modularmachines.api.upgrades;

import modularmachines.api.classes.TileMachineBase;

public interface IMachineAction {
	public void onUpdate(TileMachineBase base);
	public void drainFuel(TileMachineBase base);
	public void addFuel(TileMachineBase base);
	public int getTimeLeft(TileMachineBase base);
	public int getStartTime(TileMachineBase base);
}