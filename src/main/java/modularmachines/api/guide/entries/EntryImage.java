package modularmachines.api.guide.entries;

import modularmachines.api.guide.IEntry;
import modularmachines.helpers.IconHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class EntryImage implements IEntry{
	public EntryImage(String resource, int iconWidth, int iconHeight){
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
	}
}