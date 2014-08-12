/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 15:56:57
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.renders;

import modularmachines.main.init.MMItems;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOrb extends Render{
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_){
		IIcon iicon = MMItems.orb_empty.getIconFromDamage(0);
		
		if(iicon != null){
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y, (float)z);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.bindEntityTexture(entity);
			Tessellator tessellator = Tessellator.instance;	

			this.draw(tessellator, iicon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getEntityTexture(Entity p_110775_1_){
		return TextureMap.locationItemsTexture;
	}

	private void draw(Tessellator t, IIcon icon){
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		t.addVertexWithUV((double)(0.0F - f5), (double)(0.0F - f6), 0.0D, (double)f, (double)f3);
		t.addVertexWithUV((double)(f4 - f5), (double)(0.0F - f6), 0.0D, (double)f1, (double)f3);
		t.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0D, (double)f1, (double)f2);
		t.addVertexWithUV((double)(0.0F - f5), (double)(f4 - f6), 0.0D, (double)f, (double)f2);
		t.draw();
	}
}