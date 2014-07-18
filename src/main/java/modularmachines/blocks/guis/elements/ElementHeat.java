package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.helpers.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import wasliecore.interfaces.IElement;

public class ElementHeat extends GuiScreen implements IElement{

	public ElementHeat(TileMachineBase tile, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tile = tile;
	}
	public int x;
	public int y;
	public int width;
	public int height;
	public TileMachineBase tile;
	
	@Override
	public void drawElement() {
		drawRect(x, y, x + width, y + height, Color.gray.getRGB());
		
		if(tile.getHeatStorage().getHeat() != 0){
			int stored = tile.getHeatStorage().getHeat();
			

			if(stored > 0 && stored < 250)
				drawRect(x, (y + height), x + width, (y + height) - 12, Color.red.getRGB());
			else if(stored >= 250 && stored < 500)
				drawRect(x, (y + height), x + width, (y + height) - 24, Color.red.getRGB());
			else if(stored >= 500 && stored < 750)
				drawRect(x, (y + height), x + width, (y + height) - 32, Color.red.getRGB());
			else if(stored >= 750 && stored < 1200)
				drawRect(x, (y + height), x + width, (y + height) - 44, Color.red.getRGB());
			else if(stored == 1200)
				drawRect(x, (y + height), x + width, (y + height) - 56, Color.red.getRGB());
		}
	}
	
	@Override
	public boolean isMouseInElement(int mX, int mY) {
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {
    	Minecraft.getMinecraft().fontRenderer.drawString(tile.heat.getHeat() + "/" + tile.heat.getMaxHeat(), mX + 6, mY, 0);			
	}

	@Override
	public void onMouseClick(int mX, int mY, int type){}
}