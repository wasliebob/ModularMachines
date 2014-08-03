/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.guis;

import java.awt.Color;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.main.MMUpgrades;
import modularmachines.blocks.containers.ContainerMachineBase;
import modularmachines.blocks.guis.elements.ElementHeat;
import modularmachines.blocks.guis.elements.ElementInfo;
import modularmachines.blocks.guis.elements.ElementToggle;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMachineBase extends GuiContainer{
	public GuiMachineBase(TileMachineBase tile, InventoryPlayer inventory) {
		super(new ContainerMachineBase(tile, inventory));
		this.tile = tile;
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/core.png");
	TileMachineBase tile;
	int x, y;
	ElementHeat heat;
	ElementInfo info;
	ElementInfo status;
	ElementToggle toggle;
	
	@Override
	public void initGui() {
		super.initGui();
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;

		heat = new ElementHeat(tile.heat, x + 5, y + 15, 20, 56);		
		
		String[] string;
		info = new ElementInfo(tile.heat.getHeat() + "/" + tile.heat.getMaxHeat(), tile, x - 20, y + 15, 20, 20, x - 75, y + 15, 75, 20, new Color(0, 155, 0).getRGB());
		
		string = new String[]{
				"Upgrade: " + getUpgrade(),
				"Input: " + (tile.input != null),
				"Output: " + (tile.output != null),
		};
		
		status = new ElementInfo(string, tile, x - 20, y + 35, 20, 20,
				x - 75, y + 35, 75, 35, new Color(155, 0, 0).getRGB());
		
		toggle = new ElementToggle(tile, x + 176, y + 15, 20, 20);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mX, int mY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		GL11.glColor3f(1F, 1F, 1F);
		
		heat.drawElement();
		toggle.drawElement();
		
		if(!status.open)
			info.drawElement();
		
		if(!info.open)
			status.drawElement();
		
		if(heat.isMouseInElement(mX, mY))
			heat.onMouseEnter(mX, mY);
		
		if(toggle.isMouseInElement(mX, mY))
			toggle.onMouseEnter(mX, mY);
	}  
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
		
		if(info.isMouseInElement(mX, mY) && !status.open)
			info.onMouseClick(mX, mY, type);
		
		if(status.isMouseInElement(mX, mY) && !info.open)
			status.onMouseClick(mX, mY, type);
		
		if(toggle.isMouseInElement(mX, mY)){
			toggle.onMouseClick(mX, mY, type);
		}
	}
	
	public boolean getUpgrade(){
		if(tile.getStackInSlot(3) != null && MMUpgrades.containsItem(tile.getStackInSlot(3).getItem()))
			return true;
		return false;
	}
}