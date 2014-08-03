/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.renders;

import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.main.init.MMBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

public class TilePotionTankRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8){
		TilePotionTank tank = (TilePotionTank)tile;
		renderPotion(tank, x,  y, z);
	}
	
	public void renderPotion(TilePotionTank te, double x, double y, double z){
		Block block = te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord);
		if(te != null && te.tank.getPotion() != null){
			this.field_147501_a.field_147553_e.bindTexture(TextureMap.locationBlocksTexture);
			IIcon icon = MMBlocks.tank_potion.icon_fluid;
			
			OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
	                
			Tessellator tessellator = Tessellator.instance;
			GL11.glPushMatrix();
			GL11.glDisable(2896);
			GL11.glTranslated(x, y, z);
					
			RenderBlocks renderBlocks = new RenderBlocks();
					
			double maxYY = 0.975D; 
			double maxY;
			double am = te.tank.capacity;

			if(maxYY / (am/te.tank.getAmount()) <= maxYY)
				maxY = maxYY/(am/te.tank.getAmount());
			else
				maxY = maxYY;

			renderBlocks.setRenderBounds(0.025D, 0.025D, 0.025D, 0.975D, maxY, 0.975D);
			tessellator.startDrawingQuads();
			
			tessellator.setColorOpaque_I(te.tank.getPotion().getLiquidColor());
			int bright = 200;
			if (te.getWorldObj() != null) {
				bright = Math.max(200, block.getMixedBrightnessForBlock(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord));
			}
			tessellator.setBrightness(bright);

			renderBlocks.renderFaceYNeg(block, 0D, 0.0D, 0.0D, icon);
			renderBlocks.renderFaceYPos(block, 0D, 0.0D, 0.0D, icon);
			renderBlocks.renderFaceZNeg(block, 0D, 0.0D, 0.0D, icon);
			renderBlocks.renderFaceZPos(block, 0D, 0.0D, 0.0D, icon);
			renderBlocks.renderFaceXNeg(block, 0D, 0.0D, 0.0D, icon);
			renderBlocks.renderFaceXPos(block, 0D, 0.0D, 0.0D, icon);
					
					
			tessellator.draw();

			GL11.glEnable(2896);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}
}