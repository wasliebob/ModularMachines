package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.gui.IElement;
import modularmachines.helpers.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;

public class ElementInfo extends GuiScreen implements IElement{

	public ElementInfo(String[] string, TileEntity tile, int x, int y, int width, int height, int openX, int openY, int openWidth, int openHeight, int color) {
		this.string = string;
		this.tile = tile;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.openX = openX;
		this.openY = openY;
		this.openWidth = openWidth;
		this.openHeight = openHeight;
		
		this.color = color;
	}
	public String[] string;
	public int color;
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public int openX;
	public int openY;
	public int openWidth;
	public int openHeight;
	
	public TileEntity tile;
	public boolean open = false;
	
	public ElementInfo(String s, TileEntity tile, int x, int y, int width, int height, int openX, int openY, int openWidth, int openHeight, int color) {
		this.s = s;
		this.tile = tile;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.openX = openX;
		this.openY = openY;
		this.openWidth = openWidth;
		this.openHeight = openHeight;
		
		this.color = color;
	}
	String s;
	
	@Override
	public void drawElement(){
		if(open){
			if(string != null)
				drawRect(openX, openY, openX + openWidth, openY + (string.length*8) + 3, color);
			else if(string == null && s != null)
				drawRect(openX, openY, openX + openWidth, openY + (2*8) + 3, color);

			if(string != null && string.length > 0){
				for(int i = 0; i < string.length; i++){
					String s = string[i];
					if(s != null)
						Minecraft.getMinecraft().fontRenderer.drawString(s, openX, openY + (8*i), Color.white.getRGB());
				}
			}else if(string == null && s != null){
				Minecraft.getMinecraft().fontRenderer.drawString(s, openX, openY + (8*1), Color.white.getRGB());
			}
		}else{
			drawRect(x, y, x + width, y + height, color);
		}
	}
	
	@Override
	public boolean isMouseInElement(int mX, int mY) {
		if(this.open)
			if(string != null)
				return GuiHelper.isMouseBetween(mX, mY, openX, openY, openWidth, (string.length*8) + 3);
			else if(string == null && s != null)
				return GuiHelper.isMouseBetween(mX, mY, openX, openY, openWidth, (2*8) + 3);
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {}

	@Override
	public void onMouseClick(int mX, int mY, int type){
		if(!this.open)
			this.open = true;
		else
			this.open = false;
	}
}