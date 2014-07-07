package modularmachines.api.heat.enums;

public enum EnumHeatConverting {
	EU(100);
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