/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.heat.HeatStorage;
import modularmachines.helpers.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import wasliecore.interfaces.IElement;

public class ElementHeat extends GuiScreen implements IElement{
	public ElementHeat(HeatStorage storage, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.storage = storage;
	}
	public int x;
	public int y;
	public int width;
	public int height;
	public HeatStorage storage;
	
	@Override
	public void drawElement() {
		drawRect(x, y, x + width, y + height, Color.gray.getRGB());
		
		if(storage.getHeat() != 0){
			int stored = storage.getHeat();
			int max = storage.capacity;
			int first = max/5;
			
			if(stored > 0 && stored < first)
				drawRect(x, (y + height), x + width, (y + height) - 16, Color.red.getRGB());
			else if(stored >= first && stored < (first*2))
				drawRect(x, (y + height), x + width, (y + height) - 24, Color.red.getRGB());
			else if(stored >= (first*2) && stored < (first*3))
				drawRect(x, (y + height), x + width, (y + height) - 32, Color.red.getRGB());
			else if(stored >= (first*3) && stored < (first*4))
				drawRect(x, (y + height), x + width, (y + height) - 40, Color.red.getRGB());
			else if(stored >= (first*4) && stored < (first*5))
				drawRect(x, (y + height), x + width, (y + height) - 48, Color.red.getRGB());
			else if(stored == (first*5))
				drawRect(x, (y + height), x + width, (y + height) - 56, Color.red.getRGB());
		}
	}
	
	@Override
	public boolean isMouseInElement(int mX, int mY) {
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {
    	Minecraft.getMinecraft().fontRenderer.drawString(storage.getHeat() + "/" + storage.getMaxHeat(), mX + 6, mY, 0);			
	}

	@Override
	public void onMouseClick(int mX, int mY, int type){}
}