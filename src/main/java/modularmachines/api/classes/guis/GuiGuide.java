/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.classes.guis;

import modularmachines.api.classes.guis.buttons.ButtonNext;
import modularmachines.api.classes.guis.buttons.ButtonPage;
import modularmachines.api.guide.EntryHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiGuide extends GuiScreen{
	public GuiGuide(EntityPlayer player, int prevPage){
		this.player = player;
		this.currPage = prevPage;
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/guide.png");
    GuiButton prev, next, back;
    
    EntityPlayer player;
	int gwidth = 192;
	int gheight = 192;
	int left, top;
	int currPage = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui(){
		super.initGui();
		left = (this.width/2) - (gwidth/2);
		top = (this.height/2) - (gheight/2);
		this.buttonList.clear();
        
		populate();
		
		int k = (this.width - this.gwidth) / 2;
        this.buttonList.add(next = new ButtonNext(500, k + 120, top + 160, true));
        this.buttonList.add(prev = new ButtonNext(501, k + 38,  top + 160, false));
	}
	
	@SuppressWarnings("unchecked")
	public void populate(){
		this.buttonList.clear();

		int x, y;
		
		int j = 0;
		for(int i = 0; i < EntryHelper.titles.size(); i++){
			if(EntryHelper.titles.get(i) != null && EntryHelper.titles.get(i).page == this.currPage){
				x = this.left + gwidth / 2 - 75;
				y = (top + 15) + (10*j);
				buttonList.add(new ButtonPage(j, x, y, 110, 10, ""));
				j++;
			}
		}
		
		int k = (this.width - this.gwidth) / 2;
		
        this.buttonList.add(next = new ButtonNext(500, k + 120, top + 160, true));
        this.buttonList.add(prev = new ButtonNext(501, k + 38,  top + 160, false));
	}
	
	@Override
	public void drawScreen(int mX, int mY, float f1){
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(left, top, 0, 0, gwidth, gheight);
		
		/** Title */
		String str = "Modular Guide";
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		
		/** Current Page */
		this.drawCenteredString(fontRendererObj, (currPage + 1) + "/" + (EntryHelper.maxPages + 1), this.left + gwidth / 2, top + 160, 0x336666);
		registerButtons();

		super.drawScreen(mX, mY, f1);
	}
	
	public void registerButtons(){
		int j = 0;
		if(!EntryHelper.titles.isEmpty() && EntryHelper.titles.size() > 0){
			for(int i = 0; i < EntryHelper.titles.size(); i++){
				if(EntryHelper.titles.get(i) != null && EntryHelper.titles.get(i).page == this.currPage){
					String s = EntryHelper.titles.get(i).title;
					if(s != null && buttonList.get(j) != null && buttonList.get(j) instanceof ButtonPage){
						ButtonPage button = (ButtonPage) buttonList.get(j);
						button.displayString = s;
						j++;
					}
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
	}
	
    @Override
    public void keyTyped(char c, int i){
    	super.keyTyped(c, i);
    }
    
	@Override
    public boolean doesGuiPauseGame(){
        return false;
    }
	
	@Override
	protected void actionPerformed(GuiButton button){
		int id = button.id;
		if(id == 500){
			if(currPage < EntryHelper.maxPages){
				currPage++;
				populate();
				registerButtons();
			}
		}else if(id == 501){
			if(currPage > 0){
				currPage--;
				populate();
				registerButtons();
			}
		}else{
			mc.displayGuiScreen(new GuiEntry(button.displayString, player, currPage));
		}
	}
}