/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.heat.enums;

public enum EnumHeatConverting {
	EU(10), RF(10);
	private int value;
	
    private EnumHeatConverting(int heat) {
        this.setValue(heat);
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}