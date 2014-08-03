package modularmachines.blocks.guis;

import modularmachines.api.misc.Bug;
import modularmachines.helpers.BugHelper;
import modularmachines.helpers.GuiHelper;
import modularmachines.main.MM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public class GuiBugReport extends GuiScreen{
	public GuiBugReport(GuiScreen parent){
		this.parent = parent;
	}
    private GuiScreen parent;
    private GuiTextField text;
    private GuiTextField name;
        
    @SuppressWarnings("unchecked")
	@Override
    public void initGui(){
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        text = new GuiTextField(Minecraft.getMinecraft().fontRenderer, (this.width / 2) - 175, (this.height - 184), 350, 20);
        text.setFocused(true);
        text.setText("Bug");
        text.setMaxStringLength(1000);

        name = new GuiTextField(Minecraft.getMinecraft().fontRenderer, (this.width / 2) - 175, (this.height - 204), 350, 20);
        name.setFocused(false);
        name.setText("File Name");
        
        this.buttonList.add(new GuiButton(1, (this.width / 2) - 175, (this.height - 44), 350, 20, "Save"));

        /** Exit Button */
        this.buttonList.add(new GuiButton(0, (this.width / 2) - 175, (this.height - 24), 350, 20, "Back"));
    }
    
    @Override
    public void keyTyped(char c, int i){
    	super.keyTyped(c, i);
    	
    	if(text.isFocused())
    		text.textboxKeyTyped(c, i);
    	
    	if(name.isFocused())
    		name.textboxKeyTyped(c, i);
    }

    @Override
    public void actionPerformed(GuiButton button){
    	if (button.id == 0)
    		if(this.parent != null)
    			this.mc.displayGuiScreen(this.parent);
    		else
    			this.onGuiClosed();
    	else if(button.id == 1)
    		BugHelper.createBug(name.getText(), new Bug(text.getText(), MM.version, MM.mcVersion, MM.modName));
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks){
        this.drawDefaultBackground();
        text.drawTextBox();
        name.drawTextBox();
        super.drawScreen(x, y, renderPartialTicks);
    }
    
    public void mouseClicked(int mX, int mY, int k){
    	super.mouseClicked(mX, mY, k);
    	
    	if(GuiHelper.isMouseBetween(mX, mY, name.xPosition, name.yPosition, name.width, name.height)){
    		text.setFocused(false);
    		name.setFocused(true);
    	}
    	
    	
       	if(GuiHelper.isMouseBetween(mX, mY, text.xPosition, text.yPosition, text.width, text.height)){
    		name.setFocused(false);
    		text.setFocused(true);
    	}    
    }
    
    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    }
}