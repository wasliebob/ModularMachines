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
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOrb extends Render{
	public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_){
		IIcon iicon = MMItems.orb_empty.getIconFromDamage(0);
		
		if(iicon != null){
			GL11.glPushMatrix();
			GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.bindEntityTexture(entity);
			Tessellator tessellator = Tessellator.instance;	
			
			if(iicon == ItemPotion.func_94589_d("bottle_splash")){
				int i = PotionHelper.func_77915_a(((EntityPotion)entity).getPotionDamage(), false);
				float f2 = (float)(i >> 16 & 255) / 255.0F;
				float f3 = (float)(i >> 8 & 255) / 255.0F;
				float f4 = (float)(i & 255) / 255.0F;
				GL11.glColor3f(f2, f3, f4);
				GL11.glPushMatrix();
				this.func_77026_a(tessellator, ItemPotion.func_94589_d("overlay"));
				GL11.glPopMatrix();
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
			}
			this.func_77026_a(tessellator, iicon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getEntityTexture(Entity p_110775_1_){
		return TextureMap.locationItemsTexture;
	}

	private void func_77026_a(Tessellator t, IIcon icon){
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