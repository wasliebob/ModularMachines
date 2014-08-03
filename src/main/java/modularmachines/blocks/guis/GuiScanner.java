/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.guis;

import java.awt.Color;

import modularmachines.api.misc.interfaces.IScanable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiScanner extends GuiScreen{
	public GuiScanner(IScanable scan){
		this.scan = scan;
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/management.png");

	int gwidth = 175;
	int gheight = 222;
	int x, y;
	IScanable scan;

	@Override
	public void initGui(){
		super.initGui();
		x = (this.width/2) - (gwidth/2);
		y = (this.height/2) - (gheight/2);
		this.buttonList.clear();
	}
	
	@Override
	public void drawScreen(int mX, int mY, float f1){
		super.drawScreen(mX, mY, f1);
		int fHeight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(x, y, 0, 0, gwidth, gheight);
		
		/** Title */
		String str = "Scanner";
		this.drawCenteredString(fontRendererObj, str, this.x + gwidth / 2, y - 15, 0x336666);
	
		NBTTagCompound tag = scan.getInfo();
		String name = tag.getString("name");
		String heat = tag.getString("heat");
		String transfer = tag.getString("transfer");
		String potion = tag.getString("potion");
		
		int mod = 0;
		if(name != null && name != ""){
			Minecraft.getMinecraft().fontRenderer.drawString(name, x + 5, y + 5 + (fHeight * mod), new Color(139, 137, 137).getRGB());
			mod++;
		}
		if(heat != null && heat != ""){
			Minecraft.getMinecraft().fontRenderer.drawString(heat, x + 5, y + 5 + (fHeight * mod), new Color(139, 137, 137).getRGB());
			mod++;
		}
		
		if(transfer != null && transfer != ""){
			Minecraft.getMinecraft().fontRenderer.drawString(transfer, x + 5, y + 5 + (fHeight * mod), new Color(139, 137, 137).getRGB());
			mod++;
		}
			
		if(potion != null && potion != ""){
			Minecraft.getMinecraft().fontRenderer.drawString(potion, x + 5, y + 5 + (fHeight * mod), new Color(139, 137, 137).getRGB());
			mod++;
		}
	}
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
	}
}