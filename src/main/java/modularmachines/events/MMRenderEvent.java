package modularmachines.events;

import java.awt.Color;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.heat.interfaces.IHeatedTool;
import modularmachines.api.misc.helpers.Utils;
import modularmachines.blocks.tiles.TileRouter;
import modularmachines.helpers.DirectionHelper;
import modularmachines.items.MMProgrammer;
import modularmachines.main.init.MMItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMRenderEvent {	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void playerRender(RenderGameOverlayEvent.Post e){
		if(Minecraft.getMinecraft().thePlayer != null && e.type == ElementType.ALL && Minecraft.getMinecraft().currentScreen == null){
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.thePlayer;
			ItemStack held = player.getHeldItem();
			if(held != null && held.getItem() instanceof IHeatedTool){
				IHeatedTool tool = (IHeatedTool)held.getItem();
				String cmode = tool.getModes().get(held.getItemDamage());
				ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				
				int width = res.getScaledWidth();
				int height = res.getScaledHeight();
				int lx = (width - mc.fontRenderer.getStringWidth(cmode)) / 2;
				int ly = height - 80;
				
				int color = new Color(255, 255, 255).getRGB();
				mc.fontRenderer.drawStringWithShadow(cmode, lx, ly, color);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderConnection(RenderWorldLastEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.theWorld;
		EntityPlayer player = mc.thePlayer;
		if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer){
			TileEntity point = Utils.getTargetTile(player);
			if(point != null && point instanceof TileRouter){
				TileRouter base = (TileRouter)point;
				IHeatedMachine machine = base.getBoundMachine();
				TileRouter bound = base.getBound();
				if(bound != null){
					Block b = world.getBlock(bound.xCoord, bound.yCoord, bound.zCoord);
					double maxY = b.getBlockBoundsMaxY();
					GL11.glPushMatrix();
					Entity rv = mc.renderViewEntity;
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					
					double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * e.partialTicks;
					double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * e.partialTicks;
					double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * e.partialTicks;

					
					GL11.glTranslated(-pX, -pY, -pZ);
					GL11.glPushMatrix();
					GL11.glLineWidth(5.0F);
					GL11.glBegin(GL11.GL_LINES);
					GL11.glColor3f(255, 255, 255);
					
					/** Main */
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord);
					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord);
					
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord + 1D);
					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord + 1D);
					
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord);
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord + 1D);

					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord);
					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord + 1D);
					
					/** Misc */
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord + 1D);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord + 1D);
					
					GL11.glVertex3d(bound.xCoord, bound.yCoord, bound.zCoord);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord);
					
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord + 1D);
					
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord + 1D);
					
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord);
					
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord, bound.zCoord + 1D);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord + 1D);
					
					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord);
					
					GL11.glVertex3d(bound.xCoord, bound.yCoord + maxY, bound.zCoord + 1D);
					GL11.glVertex3d(bound.xCoord + 1D, bound.yCoord + maxY, bound.zCoord + 1D);
					
					GL11.glEnd();
					
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glPopMatrix();
					GL11.glPopMatrix();
				}else if(bound == null && machine != null){
					Block b = world.getBlock(machine.xCoord(), machine.yCoord(), machine.zCoord());
					double maxY = b.getBlockBoundsMaxY();
					GL11.glPushMatrix();
					Entity rv = mc.renderViewEntity;
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					
					double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * e.partialTicks;
					double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * e.partialTicks;
					double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * e.partialTicks;

					
					GL11.glTranslated(-pX, -pY, -pZ);
					GL11.glPushMatrix();
					GL11.glLineWidth(5.0F);
					GL11.glBegin(GL11.GL_LINES);
					GL11.glColor3f(255, 255, 255);
					
					/** Main */
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord());
					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord());
					
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord() + 1D);
					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord() + 1D);
					
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord());
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord() + 1D);

					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord());
					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord() + 1D);
					
					/** Misc */
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord() + 1D);
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord() + 1D);
					
					GL11.glVertex3d(machine.xCoord(), machine.yCoord(), machine.zCoord());
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord());
					
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord());
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord() + 1D);
					
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord());
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord() + 1D);
					
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord());
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord());
					
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord(), machine.zCoord() + 1D);
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord() + 1D);
					
					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord());
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord());
					
					GL11.glVertex3d(machine.xCoord(), machine.yCoord() + maxY, machine.zCoord() + 1D);
					GL11.glVertex3d(machine.xCoord() + 1D, machine.yCoord() + maxY, machine.zCoord() + 1D);
					GL11.glEnd();
					
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glPopMatrix();
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderFertilizing(RenderWorldLastEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer){
			TileEntity point = Utils.getTargetTile(player);
			if(point != null && point instanceof TileInteracting){
				TileInteracting base = (TileInteracting)point;
				if(base.upgrade == MMItems.interacting_fertilize){
					int x = base.xCoord;
					int y = base.yCoord + base.up;
					int z = base.zCoord;
					double maxY = 1D;
					GL11.glPushMatrix();
					Entity rv = mc.renderViewEntity;
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
						
					double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * e.partialTicks;
					double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * e.partialTicks;
					double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * e.partialTicks;
	
						
					GL11.glTranslated(-pX, -pY, -pZ);
					GL11.glPushMatrix();
					GL11.glLineWidth(5.0F);
					GL11.glBegin(GL11.GL_LINES);
					GL11.glColor3f(255, 0, 0);
						
					/** Main */
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y + maxY, z);
						
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y, z + 1D);
	
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					/** Misc */
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x + 1D, y, z);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x + 1D, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x + 1D, y, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x, y + maxY, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glEnd();
						
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glPopMatrix();
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderInputBlock(RenderWorldLastEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer){
			TileEntity point = Utils.getTargetTile(player);
			if(point != null && point instanceof TileInteracting){
				TileInteracting base = (TileInteracting)point;
				if(base.upgrade == MMItems.interacting_break || base.upgrade == MMItems.interacting_place || base.upgrade == MMItems.interacting_transfer){
					int X = base.xCoord;
					int Y = base.yCoord;
					int Z = base.zCoord;
					
					int x = DirectionHelper.getX(X, base.input);
					int y = DirectionHelper.getY(Y, base.input);
					int z = DirectionHelper.getZ(Z, base.input);
					
					double maxY = 1D;
					GL11.glPushMatrix();
					Entity rv = mc.renderViewEntity;
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
						
					double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * e.partialTicks;
					double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * e.partialTicks;
					double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * e.partialTicks;
	
						
					GL11.glTranslated(-pX, -pY, -pZ);
					GL11.glPushMatrix();
					GL11.glLineWidth(3.0F);
					GL11.glBegin(GL11.GL_LINES);
					GL11.glColor3f(Color.cyan.getRed(), Color.cyan.getGreen(), Color.cyan.getBlue());
						
					/** Main */
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y + maxY, z);
						
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y, z + 1D);
	
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					/** Misc */
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x + 1D, y, z);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x + 1D, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x + 1D, y, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x, y + maxY, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glEnd();
						
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glPopMatrix();
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOutputBlock(RenderWorldLastEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer){
			TileEntity point = Utils.getTargetTile(player);
			if(point != null && point instanceof TileInteracting){
				TileInteracting base = (TileInteracting)point;
				if(base.upgrade == MMItems.interacting_break || base.upgrade == MMItems.interacting_place || base.upgrade == MMItems.interacting_transfer){
					int X = base.xCoord;
					int Y = base.yCoord;
					int Z = base.zCoord;
					
					int x = DirectionHelper.getX(X, base.output);
					int y = DirectionHelper.getY(Y, base.output);
					int z = DirectionHelper.getZ(Z, base.output);
					
					double maxY = 1D;
					GL11.glPushMatrix();
					Entity rv = mc.renderViewEntity;
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
						
					double pX = rv.lastTickPosX + (rv.posX - rv.lastTickPosX) * e.partialTicks;
					double pY = rv.lastTickPosY + (rv.posY - rv.lastTickPosY) * e.partialTicks;
					double pZ = rv.lastTickPosZ + (rv.posZ - rv.lastTickPosZ) * e.partialTicks;
	
						
					GL11.glTranslated(-pX, -pY, -pZ);
					GL11.glPushMatrix();
					GL11.glLineWidth(3.0F);
					GL11.glBegin(GL11.GL_LINES);
					GL11.glColor3f(Color.red.getRed(), Color.red.getGreen(), Color.red.getBlue());
						
					/** Main */
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y + maxY, z);
						
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x, y, z + 1D);
	
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x, y + maxY, z + 1D);
						
					/** Misc */
					GL11.glVertex3d(x, y, z + 1D);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x, y, z);
					GL11.glVertex3d(x + 1D, y, z);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y, z + 1D);
						
					GL11.glVertex3d(x + 1D, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x + 1D, y, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x + 1D, y, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glVertex3d(x, y + maxY, z);
					GL11.glVertex3d(x + 1D, y + maxY, z);
						
					GL11.glVertex3d(x, y + maxY, z + 1D);
					GL11.glVertex3d(x + 1D, y + maxY, z + 1D);
						
					GL11.glEnd();
						
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glPopMatrix();
					GL11.glPopMatrix();
				}
			}
		}
	}
}