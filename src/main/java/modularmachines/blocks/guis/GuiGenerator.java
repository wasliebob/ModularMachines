package modularmachines.blocks.guis;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.main.MMGeneratorUpgrades;
import modularmachines.blocks.containers.ContainerGenerator;
import modularmachines.blocks.guis.elements.ElementHeat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiGenerator extends GuiContainer{
	public GuiGenerator(TileGenerator tile, InventoryPlayer inventory) {
		super(new ContainerGenerator(tile, inventory));
		this.tile = tile;
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/generator.png");
	TileGenerator tile;
	ElementHeat heat;
	int x, y;
	
	@Override
	public void initGui() {
		super.initGui();
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
		heat = new ElementHeat(tile.storage, x + 5, y + 15, 20, 56);		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mX, int mY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		GL11.glColor3f(1F, 1F, 1F);
		
		heat.drawElement();
		
		if(heat.isMouseInElement(mX, mY))
			heat.onMouseEnter(mX, mY);
	}  
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
	}

	public boolean getUpgrade(){
		if(tile.getStackInSlot(2) != null && MMGeneratorUpgrades.containsItem(tile.getStackInSlot(2).getItem()))
			return true;
		return false;
	}
}