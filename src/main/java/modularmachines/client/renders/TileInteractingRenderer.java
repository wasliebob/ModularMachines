package modularmachines.client.renders;

import java.awt.Color;

import modularmachines.api.classes.TileInteracting;
import modularmachines.helpers.IconHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileInteractingRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
		TileInteracting ti = (TileInteracting)tile;
		renderUpgrade(ti, x, y, z);
		renderInput(ti, x, y, z);
		renderOutput(ti, x, y, z);
	}
	
	public void renderInput(TileInteracting ti, double x, double y, double z){
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/misc/indicator.png"));
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tessellator t;
		Color c;
		if(ti.input == ForgeDirection.EAST){
			t = Tessellator.instance;
			c = Color.cyan;
			
			GL11.glPushMatrix();
			GL11.glTranslated((x + 1D) + 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, -1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.input == ForgeDirection.WEST){
			t = Tessellator.instance;
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x - 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.input == ForgeDirection.NORTH){
			t = Tessellator.instance;
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.01D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.input == ForgeDirection.SOUTH){
			t = Tessellator.instance;	
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, (z + 1D) + 0.01D);
			GL11.glRotated(180D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.input == ForgeDirection.UP){
			t = Tessellator.instance;	
			c = Color.cyan;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 1.001D, z + 0.5D);
			GL11.glRotated(90D, 1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.input == ForgeDirection.DOWN){
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
	
	public void renderOutput(TileInteracting ti, double x, double y, double z){
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/misc/indicator.png"));
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tessellator t;
		Color c;
		if(ti.output == ForgeDirection.EAST){
			t = Tessellator.instance;
			c = Color.red;
			
			GL11.glPushMatrix();
			GL11.glTranslated((x + 1D) + 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, -1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.output == ForgeDirection.WEST){
			t = Tessellator.instance;
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x - 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.output == ForgeDirection.NORTH){
			t = Tessellator.instance;
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.01D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.output == ForgeDirection.SOUTH){
			t = Tessellator.instance;	
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, (z + 1D) + 0.01D);
			GL11.glRotated(180D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.output == ForgeDirection.UP){
			t = Tessellator.instance;	
			c = Color.red;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 1.001D, z + 0.5D);
			GL11.glRotated(90D, 1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.output == ForgeDirection.DOWN){
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
	
	public void renderUpgrade(TileInteracting ti, double x, double y, double z){
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/misc/indicator.png"));
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tessellator t;
		Color c;
		if(ti.upgradeSide == ForgeDirection.EAST){
			t = Tessellator.instance;
			c = ti.color;
			
			GL11.glPushMatrix();
			GL11.glTranslated((x + 1D) + 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, -1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.upgradeSide == ForgeDirection.WEST){
			t = Tessellator.instance;
			c = ti.color;

			GL11.glPushMatrix();
			GL11.glTranslated(x - 0.01D, y + 0.5D, z + 0.5D);
			GL11.glRotated(90D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.upgradeSide == ForgeDirection.NORTH){
			t = Tessellator.instance;
			c = ti.color;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.01D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.upgradeSide == ForgeDirection.SOUTH){
			t = Tessellator.instance;	
			c = ti.color;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 0.5D, (z + 1D) + 0.01D);
			GL11.glRotated(180D, 0D, 1D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.upgradeSide == ForgeDirection.UP){
			t = Tessellator.instance;	
			c = ti.color;

			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5D, y + 1.001D, z + 0.5D);
			GL11.glRotated(90D, 1D, 0D, 0D);
			t.startDrawingQuads();
			IconHelper.drawVertexWithColor(t, c.getRed(), c.getGreen(), c.getBlue());
			t.draw();
			GL11.glPopMatrix();
		}else if(ti.upgradeSide == ForgeDirection.DOWN){
			t = Tessellator.instance;	
			c = ti.color;

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