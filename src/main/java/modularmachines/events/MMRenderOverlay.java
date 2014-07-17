package modularmachines.events;

import java.awt.Color;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.main.MMInteractingUpgrades;
import modularmachines.api.upgrades.IInteractingAction;
import modularmachines.items.MMItemGuide;
import modularmachines.main.init.MMItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import wasliecore.helpers.ColorHelper;
import wasliecore.helpers.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMRenderOverlay extends Gui{	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	  public void playerRender(RenderGameOverlayEvent event){
		if(Minecraft.getMinecraft().thePlayer != null && event.type == ElementType.HOTBAR && Minecraft.getMinecraft().currentScreen == null){
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = Minecraft.getMinecraft().thePlayer ;
			ItemStack held = player.getHeldItem();
			if(held != null && held.getItem() instanceof MMItemGuide){
				renderIt(event, player, mc);
			}
		}
	}
	
	public void renderIt(RenderGameOverlayEvent event, EntityPlayer player, Minecraft mc){
		if(mc.renderViewEntity != null){
			Block block = Utils.getTargetBlock(player);
			if(block != null && block instanceof IGuided){
				IGuided guided = (IGuided)block;
				ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				int width = res.getScaledWidth();
				int height = res.getScaledHeight();
				FontRenderer font = mc.fontRenderer;
				String s = null;
				
				TileEntity te = Utils.getTargetTile(player);
				if(te != null && te instanceof TileInteracting){
					TileInteracting ti = (TileInteracting)te;
					ForgeDirection dir = ForgeDirection.getOrientation(Utils.getTargetBlockSide(player));
					if(dir == ti.upgradeSide){
						IInteractingAction action = MMInteractingUpgrades.getUpgrade(ti.upgrade).action;
						if(action != null){
							String key = action.getKey();
							if(key != null){
								s = "Guide Entry: " + key;
								drawIcon(event);
							}else{
								s = "Guide Entry: " + guided.getKey();
								drawIcon(event);
							}
						}else{
							s = "Guide Entry: " + guided.getKey();
							drawIcon(event);
						}
					}else{
						s = "Guide Entry: " + guided.getKey();
						drawIcon(event);
					}
				}else{
					s = "Guide Entry: " + guided.getKey();
					drawIcon(event);
				}
				int stringLengthX = (width - font.getStringWidth(s)) / 2;
				int stringLengthY = height - 70;    
				
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}
	}
	
	public void drawIcon(RenderGameOverlayEvent event){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		RenderItem ri = new RenderItem();
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		int x = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 40;
		int y = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 30;

		ri.renderItemIntoGUI(fr, Minecraft.getMinecraft().renderEngine, new ItemStack(MMItems.guide), x, y);
		
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_BLEND);
	}
}