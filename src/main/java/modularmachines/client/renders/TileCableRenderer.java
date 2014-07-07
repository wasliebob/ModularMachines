//package modularmachines.client.renders;
//
//import modularmachines.blocks.tiles.TileCable;
//import modularmachines.client.models.ModelCable;
//import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.common.util.ForgeDirection;
//
//import org.lwjgl.opengl.GL11;
//
//public class TileCableRenderer extends TileEntitySpecialRenderer{
//	ModelCable model = new ModelCable();
//	
//	@Override
//	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
//		TileCable cable = (TileCable)tile;
//		renderCable(cable, x, y, z);
//	}
//
//	public void renderCable(TileCable cable, double x, double y, double z){
//		ForgeDirection face = cable.face;
//		if(face == ForgeDirection.NORTH || face == ForgeDirection.SOUTH || face == ForgeDirection.UNKNOWN){
//			GL11.glPushMatrix();
//			this.bindTexture(new ResourceLocation("modularmachines:textures/blocks/core.png"));
//			GL11.glTranslatef((float)x + 1.5F, (float)y + 0.6F, (float)z + 0.5F);
//			GL11.glScalef(1F, 1F, 1F);
//			GL11.glRotatef(90F, 0F, 0F, 1F);
//			model.render();
//			GL11.glPopMatrix();
//		}else if(face == ForgeDirection.EAST || face == ForgeDirection.WEST){
//			GL11.glPushMatrix();
//			this.bindTexture(new ResourceLocation("modularmachines:textures/blocks/core.png"));
//			GL11.glTranslatef((float)x + 0.5F, (float)y + 0.6F, (float)z - 0.5F);
//			GL11.glScalef(1F, 1F, 1F);
//			GL11.glRotatef(90F, 0F, 0F, 1F);
//			GL11.glRotated(90D, 1D, 0D, 0D);
//
//			model.render();
//			GL11.glPopMatrix();
//		}else if(face == ForgeDirection.UP || face == ForgeDirection.DOWN){
//			GL11.glPushMatrix();
//			this.bindTexture(new ResourceLocation("modularmachines:textures/blocks/core.png"));
//			GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F);
//			GL11.glScalef(1F, 1F, 1F);
//			model.render();
//			GL11.glPopMatrix();
//		}
//	}
//}