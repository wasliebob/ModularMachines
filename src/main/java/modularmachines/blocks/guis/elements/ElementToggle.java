package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.gui.IElement;
import modularmachines.helpers.GuiHelper;
import modularmachines.main.init.MMInit;
import modularmachines.network.packets.newpackets.PacketToggle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ElementToggle extends GuiScreen implements IElement{

	public ElementToggle(TileMachineBase tile, int x, int y, int width, int height) {
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
		drawRect(x, y, x + width, y + height, getColor());
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
	public void onMouseEnter(int mX, int mY) {
    	Minecraft.getMinecraft().fontRenderer.drawString("Enabled: " + tile.enabled, mX + 6, mY, 0);			
	}

	@Override
	public void onMouseClick(int mX, int mY, int type){
		if(tile.enabled)
			tile.enabled = false;
		else
			tile.enabled = true;
		MMInit.network.sendToServer(new PacketToggle(tile));
	}
}