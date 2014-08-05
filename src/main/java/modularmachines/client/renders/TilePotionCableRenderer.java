/**
* This file is created by wasliebob.
* Created on: 4 aug. 2014, at 09:34:49
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.renders;

import modularmachines.blocks.tiles.TilePotionTube;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TilePotionCableRenderer extends TileEntitySpecialRenderer{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float ticks){
		TilePotionTube te = (TilePotionTube)tile;
		initRenderer(te, x, y, z, ticks);
	}
	
	public void initRenderer(TilePotionTube te, double x, double y, double z, float ticks){
		if(te.coords.isEmpty()){
			renderDefault(te, x, y, z, ticks);
		}else{
			if(te.coords.containsKey(ForgeDirection.UP)){
				renderTop(te, x, y, z, ticks);
			}
			
			if(te.coords.containsKey(ForgeDirection.DOWN)){
				renderBottom(te, x, y, z, ticks);
			}
			
			if(te.coords.containsKey(ForgeDirection.SOUTH)){
				renderFront(te, x, y, z, ticks);
			}
			
			if(te.coords.containsKey(ForgeDirection.NORTH)){
				renderBack(te, x, y, z, ticks);
			}
			
			if(te.coords.containsKey(ForgeDirection.EAST)){
				renderRight(te, x, y, z, ticks);
			}
			
			if(te.coords.containsKey(ForgeDirection.WEST)){
				renderLeft(te, x, y, z, ticks);
			}
		}
	}
	
	public void renderDefault(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 0.6D;
		
		double minX = 0.4D;
		double maxX = 0.6D;
		
		double minZ = 0.4D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();

		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderTop(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 1D;
		
		double minX = 0.4D;
		double maxX = 0.6D;
		
		double minZ = 0.4D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderBottom(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0D;
		double maxY = 0.6D;
		
		double minX = 0.4D;
		double maxX = 0.6D;
		
		double minZ = 0.4D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderFront(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 0.6D;
		
		double minX = 0.4D;
		double maxX = 0.6D;
		
		double minZ = 0.4D;
		double maxZ = 1D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderBack(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 0.6D;
		
		double minX = 0.4D;
		double maxX = 0.6D;
		
		double minZ = 0D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderRight(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 0.6D;
		
		double minX = 0.4D;
		double maxX = 1D;
		
		double minZ = 0.4D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderLeft(TilePotionTube te, double x, double y, double z, float ticks){
		double minY = 0.4D;
		double maxY = 0.6D;
		
		double minX = 0D;
		double maxX = 0.6D;
		
		double minZ = 0.4D;
		double maxZ = 0.6D;
		
		GL11.glPushMatrix();
		Entity rv = Minecraft.getMinecraft().renderViewEntity;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * ticks;
		double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * ticks;
		double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * ticks;

		
		GL11.glTranslated(-pX, -pY, -pZ);
		GL11.glPushMatrix();
		
		GL11.glLineWidth(5F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		
		// Main
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Finishing
		
		//Right
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Left
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		//Front
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + minZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + minZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + minZ);
		
		//Back
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + minY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + minY, te.zCoord + maxZ);
		
		GL11.glVertex3d(te.xCoord + minX, te.yCoord + maxY, te.zCoord + maxZ);
		GL11.glVertex3d(te.xCoord + maxX, te.yCoord + maxY, te.zCoord + maxZ);
		
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}