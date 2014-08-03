package modularmachines.api.classes.guis;

import modularmachines.api.classes.guis.buttons.ButtonNext;
import modularmachines.api.guide.EntryHelper;
import modularmachines.api.guide.IEntry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiEntry extends GuiScreen{
	public GuiEntry(String key, EntityPlayer player, int prevPage){
		this.key = key;
		this.player = player;
		this.prevPage = prevPage;
	}
    private static final ResourceLocation gui = new ResourceLocation("modularmachines:textures/gui/guide.png");
	int gwidth = 192;
	int gheight = 192;
	int prevPage;
	int left, top;
	String key;
	int currPage = 0;
	GuiButton next, prev, back;
	EntityPlayer player;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui(){
		super.initGui();
		left = (this.width/2) - (gwidth/2);
		top = (this.height/2) - (gheight/2);
		this.buttonList.clear();
		int k = (this.width - this.gwidth) / 2;
		
        this.buttonList.add(next = new ButtonNext(500, k + 120, top + 160, true));
        this.buttonList.add(prev = new ButtonNext(501, k + 38,  top + 160, false));
		
        IEntry entry = EntryHelper.entries.get(key)[currPage];
		if(entry != null){
			entry.initGui(gwidth, gheight, left, top, player, this.buttonList);
		}
	}
	
	@Override
	public void drawScreen(int mX, int mY, float f1){
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(left, top, 0, 0, gwidth, gheight);
		
		/** Title */
		String str = this.key;
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		
		/** Current Page */
		this.drawCenteredString(fontRendererObj, (currPage + 1) + "/" + ((EntryHelper.entries.get(key).length - 1) + 1), this.left + gwidth / 2, top + 160, 0x336666);

		IEntry entry = EntryHelper.entries.get(key)[currPage];
		if(entry != null){
			entry.draw(this, gwidth, gheight, left, top, player, key, currPage, mX, mY);
		}
		super.drawScreen(mX, mY, f1);
	}
	
	@Override
	public void mouseClicked(int mX, int mY, int type){
		super.mouseClicked(mX, mY, type);
		
		if(type  == 1)
			mc.displayGuiScreen(new GuiGuide(player, prevPage));
	}
	
    @Override
    public void keyTyped(char c, int i){
    	super.keyTyped(c, i);
    	
    	if(Keyboard.getEventKeyState()){
    		if(i == 14){
    			mc.displayGuiScreen(new GuiGuide(player, prevPage));
            	
    			return;
    		}
    	}
    }
    
	@Override
    public boolean doesGuiPauseGame(){
        return false;
    }
	
	@Override
	protected void actionPerformed(GuiButton button){
		int id = button.id;
		int maxPages = EntryHelper.entries.get(key).length - 1;

		if(id == 500){
			if(currPage < maxPages){
				currPage++;
				initGui();
			}
		}else if(id == 501){
			if(currPage > 0){
				currPage--;
				initGui();
			}
		}else{
			EntryHelper.entries.get(key)[currPage].actionPerformed(button);
		}
	}
	
	@Override
    public void onGuiClosed(){
		
	}
}