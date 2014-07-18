package modularmachines.api.guide.entries;

import java.util.List;

import modularmachines.api.guide.IEntry;
import modularmachines.blocks.guis.guide.GuiEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public class EntryInfo implements IEntry{
	public EntryInfo(int type){
		if(type == 0)
			this.type = "Modular";
		else if(type == 1)
			this.type = "Interacting";
	}
	public String type;
	
	@Override
	public void draw(GuiEntry entry, int width, int height, int left, int top, EntityPlayer player, String key, int page, int mX, int mY){
		int x, y;
		x = left + width / 2 - 58;
		y = (top + 15);

		Minecraft.getMinecraft().fontRenderer.drawString("Type: " + this.type, x, y, 0);	

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