package modularmachines.client.renders;

import java.awt.Color;

import modularmachines.blocks.tiles.TileRouter;
import modularmachines.helpers.IconHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileRouterRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
		TileRouter base = (TileRouter)tile;
		renderInput(base, x, y, z);
		renderOutput(base, x, y, z);
	}
	
	public void renderInput(TileRouter base, double x, double y, double z){
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/misc/indicator.png"));
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tessellator t;
		Color c;
		if(base.input == ForgeDirection.EAST){
			t = Tessellator.instance;
			c = Color.cyan;
			
			GL11.glPushMatrix();
			GL11.glTranslated((x + 1D) + 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, -1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.input == ForgeDirection.WEST){
			t = Tessellator.instance;
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x - 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.input == ForgeDirection.NORTH){
			t = Tessellator.instance;
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.01D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.input == ForgeDirection.SOUTH){
			t = Tessellator.instance;	
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, (z + 1D) + 0.01D);
			GL11.glRotated(180D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.input == ForgeDirection.UP){
			t = Tessellator.instance;	
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 1.001D, z + 0.5D);
			GL11.glRotated(90D, 1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.input == ForgeDirection.DOWN){
			t = Tessellator.instance;	
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y - 0.01D, z + 0.5D);
			GL11.glRotated(90D, -1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}
	}
	
	public void renderOutput(TileRouter base, double x, double y, double z){
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/misc/indicator.png"));
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tessellator t;
		Color c;
		if(base.output == ForgeDirection.EAST){
			t = Tessellator.instance;
			c = Color.red;
			
			GL11.glPushMatrix();
			GL11.glTranslated((x + 1D) + 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, -1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.output == ForgeDirection.WEST){
			t = Tessellator.instance;
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x - 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.output == ForgeDirection.NORTH){
			t = Tessellator.instance;
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.01D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.output == ForgeDirection.SOUTH){
			t = Tessellator.instance;	
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, (z + 1D) + 0.01D);
			GL11.glRotated(180D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.output == ForgeDirection.UP){
			t = Tessellator.instance;	
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 1.001D, z + 0.5D);
			GL11.glRotated(90D, 1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(base.output == ForgeDirection.DOWN){
			t = Tessellator.instance;	
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y - 0.01D, z + 0.5D);
			GL11.glRotated(90D, -1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}
	}
}