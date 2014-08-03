/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.guide.entries;

import java.util.List;

import modularmachines.api.classes.guis.GuiEntry;
import modularmachines.api.guide.IEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public class EntryInfo implements IEntry{
	public EntryInfo(int type, int heat){
		if(type == 0)
			this.type = "Modular";
		else if(type == 1)
			this.type = "Interacting";
		else if(type == 2)
			this.type = "Generator";
		
		this.heat = heat;
	}
	public String type;
	public int heat;
	
	@Override
	public void draw(GuiEntry entry, int width, int height, int left, int top, EntityPlayer player, String key, int page, int mX, int mY){
		int x, y;
		x = left + width / 2 - 58;
		y = (top + 15);
		Minecraft.getMinecraft().fontRenderer.drawString("Type: " + this.type, x, y, 0);
		
		x = left + width / 2 - 58;
		y = (top + 15) + 9;
		Minecraft.getMinecraft().fontRenderer.drawString("Heat Required: " + this.heat, x, y, 0);	

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