package modularmachines.blocks.guis.guide.entries;

import java.util.List;

import modularmachines.api.guide.IEntry;
import modularmachines.helpers.IconHelper;
import modularmachines.main.MM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class EntryTitle implements IEntry{
	public EntryTitle(String resource, int iconWidth, int iconHeight){
		this.resource = new ResourceLocation(resource);
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}
	public ResourceLocation resource;
	public int iconWidth;
	public int iconHeight;
	
	@Override
	public void draw(int width, int height, int left, int top, EntityPlayer player){
		int x = left + 32;
		int y = top + 10;
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.resource);

		IconHelper.drawIconWithoutColor(x, y, this.iconWidth, this.iconHeight, 0);
		
		Minecraft.getMinecraft().fontRenderer.drawString("Version: " + MM.version, x + 12, y + this.iconHeight, 0);
		Minecraft.getMinecraft().fontRenderer.drawString("Created by Wasliebob", x + 12, y + this.iconHeight + 9, 0);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void initGui(int width, int height, int left, int top,
			EntityPlayer player, List buttonList) {
		
	}

	@Override
	public void actionPerformed(GuiButton button) {		
	}
}