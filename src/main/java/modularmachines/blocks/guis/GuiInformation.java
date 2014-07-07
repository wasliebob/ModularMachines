package modularmachines.blocks.guis;

import modularmachines.api.classes.TileInteracting;
import modularmachines.blocks.guis.elements.ElementToggleInteracting;
import modularmachines.blocks.guis.elements.ElementTransfer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiInformation extends GuiScreen{
	public GuiInformation(TileInteracting tile){
		this.tile = tile;
	}
	
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/management.png");

	int gwidth = 175;
	int gheight = 222;
	int x, y;
	TileInteracting tile;
	ElementToggleInteracting toggle;
	ElementTransfer transfer;

	@Override
	public void initGui(){
		super.initGui();
		x = (this.width/2) - (gwidth/2);
		y = (this.height/2) - (gheight/2);
		this.buttonList.clear();
		
		toggle = new ElementToggleInteracting(tile, x + 5, y + 5, 165, 24);
		transfer = new ElementTransfer(tile, x + 5, y + 29, 165, 24);

	}
	
	@Override
	public void drawScreen(int mX, int mY, float f1){
		super.drawScreen(mX, mY, f1);

		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(x, y, 0, 0, gwidth, gheight);
		
		/** Title */
		String str = "Configuration Screen";
		this.drawCenteredString(fontRendererObj, str, this.x + gwidth / 2, y - 15, 0x336666);

		toggle.drawElement();
		transfer.drawElement();
	}
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
		
		if(toggle.isMouseInElement(mX, mY))
			toggle.onMouseClick(mX, mY, type);
		
		if(transfer.isMouseInElement(mX, mY))
			transfer.onMouseClick(mX, mY, type);
	}
}