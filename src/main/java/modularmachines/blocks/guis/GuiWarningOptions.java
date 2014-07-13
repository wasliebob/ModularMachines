package modularmachines.blocks.guis;

import modularmachines.main.MM;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import wasliecore.helpers.java.WebHelper;

public class GuiWarningOptions extends GuiScreen{
	public GuiWarningOptions(GuiScreen parent){
		this.parent = parent;
	}
    private GuiScreen parent;
    String url_changelog = "https://dl.dropboxusercontent.com/u/46500170/Site/MM/MM_" + MM.version + ".txt";
    String url_developer = "http://www.wasliebob.nl/about.php";
    String url_download = "https://wasliebob.ci.cloudbees.com/job/ModularMachines/";
    
    @SuppressWarnings("unchecked")
	@Override
    public void initGui(){
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        /** Exit Button */
        this.buttonList.add(new GuiButton(0, (this.width / 2) - 175, (this.height - 24), 350, 20, "Back"));
    
        /** Extra Buttons */
        this.buttonList.add(new GuiButton(1, (this.width / 2) - 175, (this.height - 64), 350, 20, "Report a bug"));
        this.buttonList.add(new GuiButton(2, (this.width / 2) - 175, (this.height - 84), 350, 20, "View Changelog"));
        this.buttonList.add(new GuiButton(3, (this.width / 2) - 175, (this.height - 104), 350, 20, "About The Developer"));
        this.buttonList.add(new GuiButton(4, (this.width / 2) - 175, (this.height - 124), 350, 20, "Downloads"));

    }

    @Override
    public void actionPerformed(GuiButton button){
    	if (button.id == 0)
    		this.mc.displayGuiScreen(this.parent);
    	if(button.id == 1)
    		this.mc.displayGuiScreen(new GuiBugReport(this));
    	if(button.id == 2)
    		WebHelper.openURL(this.url_changelog);
    	if(button.id == 3)
    		WebHelper.openURL(this.url_developer);
    	if(button.id == 4)
    		WebHelper.openURL(this.url_download);
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks){
        this.drawDefaultBackground();

        super.drawScreen(x, y, renderPartialTicks);
    }
    
    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    }
}