/**
* This file is created by wasliebob.
* Created on: 4 aug. 2014, at 09:38:48
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.renders;

import modularmachines.helpers.RenderingHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderPotionCable implements ISimpleBlockRenderingHandler{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
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
		return RenderingHelper.render_cable_potion;
	}
}