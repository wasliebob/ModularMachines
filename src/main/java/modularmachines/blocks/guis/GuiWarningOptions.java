package modularmachines.blocks.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import wasliecore.helpers.java.WebHelper;

public class GuiWarningOptions extends GuiScreen{
	public GuiWarningOptions(GuiScreen parent){
		this.parent = parent;
	}
    private GuiScreen parent;
    String url_developer = "http://www.wasliebob.nl/about.php";
    String url_download = "http://ci.tterrag.com/view/Admin/job/Modular%20Machines/lastSuccessfulBuild/artifact/src/main/resources/changelog.txt";
    String url_ideas = "https://docs.google.com/document/d/1X2dNhauFYkDwff3gmrcm7x50AdvxVb_Kyc9KR5DwiQM/edit?usp=sharing";
    String url_donate = "http://www.patreon.com/wasliebob";
    String url_changelog = "http://ci.tterrag.com/view/Admin/job/Modular%20Machines/lastSuccessfulBuild/artifact/src/main/resources/changelog.txt";
    
    @SuppressWarnings("unchecked")
	@Override
    public void initGui(){
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        /** Exit Button */
        this.buttonList.add(new GuiButton(0, (this.width / 2) - 175, (this.height - 24), 350, 20, "Back"));
    
        /** Extra Buttons */
        this.buttonList.add(new GuiButton(1, (this.width / 2) - 175, (this.height - 64), 350, 20, "Report a bug"));
        this.buttonList.add(new GuiButton(2, (this.width / 2) - 175, (this.height - 84), 350, 20, "About The Developer"));
        this.buttonList.add(new GuiButton(3, (this.width / 2) - 175, (this.height - 104), 350, 20, "Ideas/Progress"));
        this.buttonList.add(new GuiButton(4, (this.width / 2) - 175, (this.height - 124), 350, 20, "Downloads"));
        this.buttonList.add(new GuiButton(5, (this.width / 2) - 175, (this.height - 144), 350, 20, "Donate/Support"));
        this.buttonList.add(new GuiButton(6, (this.width / 2) - 175, (this.height - 164), 350, 20, "Changelog"));

    }

    @Override
    public void actionPerformed(GuiButton button){
    	if (button.id == 0)
    		this.mc.displayGuiScreen(this.parent);
    	if(button.id == 1)
    		this.mc.displayGuiScreen(new GuiBugReport(this));
    	if(button.id == 2)
    		WebHelper.openURL(this.url_developer);
    	if(button.id == 3)
    		WebHelper.openURL(this.url_ideas);
    	if(button.id == 4)
    		WebHelper.openURL(this.url_download);
    	if(button.id == 5)
    		WebHelper.openURL(this.url_donate);
    	if(button.id == 6)
    		WebHelper.openURL(this.url_changelog);
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