package modularmachines.blocks.guis.elements;

import java.awt.Color;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.helpers.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IIcon;
import wasliecore.interfaces.IElement;

public class ElementFluid extends GuiScreen implements IElement{

	public ElementFluid(TileMachineBase tile, int x, int y, int width, int height) {
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
	public void drawElement(){
		drawRect(x, y, x + width, y + height, Color.gray.getRGB());
		
		if(tile.tank.getFluid() != null && tile.tank.getFluidAmount() != 0){
			IIcon icon = tile.tank.getFluid().getFluid().getIcon();

			int stored = tile.tank.getFluidAmount();

			if(stored > 0 && stored < 250)
				drawTexturedModelRectFromIcon(x, (y + height), icon, x + width, (y + height) - 12);
			else if(stored >= 250 && stored < 500)
				drawTexturedModelRectFromIcon(x, (y + height), icon, x + width, (y + height) - 24);
			else if(stored >= 500 && stored < 750)
				drawTexturedModelRectFromIcon(x, (y + height), icon, x + width, (y + height) - 32);
			else if(stored >= 750 && stored < 1200)
				drawTexturedModelRectFromIcon(x, (y + height), icon, x + width, (y + height) - 44);
			else if(stored == tile.tank.getCapacity())
				drawTexturedModelRectFromIcon(x, (y + height), icon, x + width, (y + height) - 56);
		}
	}
	
	@Override
	public boolean isMouseInElement(int mX, int mY) {
		return GuiHelper.isMouseBetween(mX, mY, x, y, width, height);
	}
	
	@Override
	public void onMouseEnter(int mX, int mY) {
    	Minecraft.getMinecraft().fontRenderer.drawString(tile.tank.getFluidAmount() + "/" + tile.tank.getCapacity(), mX + 6, mY, 0);			
	}

	@Override
	public void onMouseClick(int mX, int mY, int type){}
}