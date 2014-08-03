/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.renders;

import modularmachines.client.models.ModelFluidHeater;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileFluidHeaterRenderer extends TileEntitySpecialRenderer{
	ModelFluidHeater model = new ModelFluidHeater();
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("modularmachines:textures/blocks/core.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 2.247F, (float)z + 0.5F);
		GL11.glScaled(1, 1.5d, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();

	}
}