package modularmachines.api.guide.entries;

import modularmachines.api.guide.IEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class EntryRecipe extends GuiScreen implements IEntry{
	public EntryRecipe(ItemStack[] recipe){
		this.recipe = recipe;
	}
	public ItemStack[] recipe;
	
	@Override
	public void draw(int width, int height, int left, int top, EntityPlayer player){
		int x, y;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		for(int i = 0; i < recipe.length; i++){
			if(i <= 2){
				x = (left + width / 2) - (65-32-2);
				y = (height/2 - 18) + (16*i);
			}else if(i > 2 && i <= 5){
				x = left + width / 2 - (65-48-2);
				y = (height/2 - 18) + (16*(i-3));
			}else if(i > 5 && i <= 9){
				x = left + width / 2 - (65-(48+16)-2);
				y = (height/2 - 18) + (16*(i-6));
			}else{
				x = left + width / 2 - (65-32-2);
				y = (height/2 - 18) + (16*i);
			}
			GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), recipe[i], x, y);
		}
		
		GL11.glDisable(GL11.GL_BLEND);
	}
}
