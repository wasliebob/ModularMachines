package modularmachines.client.renders;

import modularmachines.client.models.ModelCable;
import modularmachines.helpers.RenderingHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCable implements ISimpleBlockRenderingHandler{
	ModelCable model = new ModelCable();
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		
//		GL11.glPushMatrix();
////		GL11.glScalef(1, 1f, 1);
//		GL11.glTranslated(0.5D, 0.2D, 0.5D);
//		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("modularmachines:textures/blocks/core.png"));
//		model.render();
//		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderingHelper.render_cable;
	}
}