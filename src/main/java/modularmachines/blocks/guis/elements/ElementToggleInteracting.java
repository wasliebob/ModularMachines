/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.classes.TileInteracting;
import modularmachines.helpers.GuiHelper;
import modularmachines.main.init.MMInit;
import modularmachines.network.packets.newpackets.PacketToggleInteracting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import wasliecore.interfaces.IElement;

public class ElementToggleInteracting extends GuiScreen implements IElement{
	public ElementToggleInteracting(TileInteracting tile, int x, int y, int width, int height) {
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
	public TileInteracting tile;
	
	@Override
	public void drawElement() {
		drawRect(x, y, x + width, y + height, getColor());
		String s = "Enabled: " + tile.enabled;
    	Minecraft.getMinecraft().fontRenderer.drawString(s, x + (width/2) - (s.length()*2) , y + (height /2) - 2, 0);			

	}
	
	public int getColor(){
		if(tile.enabled)
			return new Color(0, 100, 0).getRGB();
		else
			return new Color(100, 0, 0).getRGB();
	}
	
	@Override
	public boolean isMouseInElement(int mX, int mY) {
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {}

	@Override
	public void onMouseClick(int mX, int mY, int type){
		if(tile.enabled)
			tile.enabled = false;
		else
			tile.enabled = true;
		MMInit.network.sendToServer(new PacketToggleInteracting(tile));
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
}