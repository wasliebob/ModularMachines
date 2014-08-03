/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.guis;

import modularmachines.api.classes.TileInteracting;
import modularmachines.blocks.containers.ContainerFilter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiFilter extends GuiContainer{
	public GuiFilter(TileInteracting tile, InventoryPlayer inventory) {
		super(new ContainerFilter(tile, inventory));
		this.tile = tile;
		container = new ContainerFilter(tile, inventory);
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/filter.png");
	TileInteracting tile;
	ContainerFilter container;
	int x, y;
	
	@Override
	public void initGui() {
		super.initGui();
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mX, int mY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		GL11.glColor3f(1F, 1F, 1F);
	}  
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
	}
}