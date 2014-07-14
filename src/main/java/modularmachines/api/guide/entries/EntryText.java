package modularmachines.api.guide.entries;

import java.awt.Color;

import modularmachines.api.guide.IEntry;
import net.minecraft.client.Minecraft;

public class EntryText implements IEntry{
	public EntryText(String[] text){
		this.text = text;
	}
	public String[] text;

	@Override
	public void draw(int width, int height, int left, int top) {
		int x, y;
		
		int j = 0;
		for(int i = 0; i < text.length; i++){
			String s = text[i];
			x = left + width / 2 - 50;
			y = (top + 15) + (9*i) + j;
			
			if(s.length() <= 20){
				Minecraft.getMinecraft().fontRenderer.drawString(s, x, y, new Color(55, 55, 55).getRGB());
			}else if(s.length() <= 40){
				String ss = s.substring(0, 19);
				Minecraft.getMinecraft().fontRenderer.drawString(ss, x, y, new Color(55, 55, 55).getRGB());

				String sj = s.substring(19, s.length());
				Minecraft.getMinecraft().fontRenderer.drawString(sj, x, y + 9, new Color(55, 55, 55).getRGB());
				j = j + 9;
			}else if(s.length() <= 60){
				String ss = s.substring(0, 19);
				Minecraft.getMinecraft().fontRenderer.drawString(ss, x, y, new Color(55, 55, 55).getRGB());

				String sj = s.substring(19, 39);
				Minecraft.getMinecraft().fontRenderer.drawString(sj, x, y + 9, new Color(55, 55, 55).getRGB());
				
				String sp = s.substring(39, s.length());
				Minecraft.getMinecraft().fontRenderer.drawString(sp, x, y + 18, new Color(55, 55, 55).getRGB());
				
				j = j + 18;
			}
		}
	}
}