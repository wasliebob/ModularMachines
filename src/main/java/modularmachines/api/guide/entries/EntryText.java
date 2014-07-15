package modularmachines.api.guide.entries;

import java.util.List;

import modularmachines.api.guide.IEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class EntryText implements IEntry{
	@Override
	public void draw(int width, int height, int left, int top, EntityPlayer player, String key, int page){
		int x, y;
		
		String s = StatCollector.translateToLocal("mm.guide.entries." + key + "." + page);
		x = left + width / 2 - 58;
		y = (top + 15);

		Minecraft.getMinecraft().fontRenderer.drawSplitString(s, x, y, 110, 0);	
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void initGui(int width, int height, int left, int top,
			EntityPlayer player, List buttonList){

	}
	
	@Override
	public void actionPerformed(GuiButton button){
		
	}
}