package modularmachines.events;

import java.awt.Color;

import modularmachines.api.guide.IGuided;
import modularmachines.items.MMItemGuide;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

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
				String s = "Guide Energy: " + guided.getKey();
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
}