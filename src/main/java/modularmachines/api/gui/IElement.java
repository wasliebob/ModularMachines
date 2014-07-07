package modularmachines.api.gui;

public interface IElement {
	public void drawElement();
	public boolean isMouseInElement(int mX, int mY);
	public void onMouseEnter(int mX, int mY);
	public void onMouseClick(int mX, int mY, int type);
}