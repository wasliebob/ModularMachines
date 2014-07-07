package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.gui.IElement;
import modularmachines.helpers.GuiHelper;
import modularmachines.main.init.MMInit;
import modularmachines.network.packets.newpackets.PacketTransferRate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ElementTransfer extends GuiScreen implements IElement{

	public ElementTransfer(TileInteracting tile, int x, int y, int width, int height) {
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
		drawRect(x, y, x + width, y + height, Color.lightGray.getRGB());
		String s = "Transfer: " + tile.heat.getTransfer();
    	Minecraft.getMinecraft().fontRenderer.drawString(s, x + (width/2) - (s.length()*2) , y + (height /2) - 2, 0);
	}

	@Override
	public boolean isMouseInElement(int mX, int mY) {
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {}

	@Override
	public void onMouseClick(int mX, int mY, int type){
		tile.heat.setTransfer(calcTransfer(type));
		MMInit.network.sendToServer(new PacketTransferRate(tile, type));
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	public int calcTransfer(int type){
		if(type == 0){
			if(tile.heat.getTransfer() < 200)
				return tile.heat.getTransfer() + 10;
		}else if(type == 1){
			if(tile.heat.getTransfer() > 0)
				return tile.heat.getTransfer() - 10;
		}
		return tile.heat.getTransfer();
	}
}