package modularmachines.blocks.guis;

import java.awt.Color;

import modularmachines.main.MM;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public class GuiWarningScreen extends GuiScreen{
	public GuiWarningScreen(GuiScreen parent){
		this.parent = parent;
	}
    private GuiScreen parent;
    String url_bug = "Not Available";
    
    @SuppressWarnings("unchecked")
	@Override
    public void initGui(){
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, (this.width / 2) - 175, (this.height - 24), 350, 20, "I Agree"));
        this.buttonList.add(new GuiButton(1, (this.width / 2) - 175, (this.height - 44), 350, 20, "Options"));

    }

    @Override
    public void actionPerformed(GuiButton button){
    	if (button.id == 0)
    		this.mc.displayGuiScreen(this.parent);
    	else if(button.id == 1)
    		this.mc.displayGuiScreen(new GuiWarningOptions(this));
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks){
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Modular Machines " + MM.version, this.width / 2, 50, Color.cyan.getRGB());

        this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal("mm.warning.1"), this.width / 2, 82, Color.red.getRGB());
        this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal("mm.warning.2"), this.width / 2, 82 + (8), Color.red.getRGB());

        super.drawScreen(x, y, renderPartialTicks);
    }
    
    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    }
}