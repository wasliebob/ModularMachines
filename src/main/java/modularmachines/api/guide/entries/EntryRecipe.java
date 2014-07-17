package modularmachines.api.guide.entries;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import modularmachines.api.guide.IEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class EntryRecipe extends GuiScreen implements IEntry{
	public EntryRecipe(IRecipe recipes){		
		this.recipes = recipes;
		populate(recipes);
	}
	public IRecipe recipes;
	
	public ItemStack[] recipe;
	public ArrayList<ItemIcon> icons = new ArrayList<ItemIcon>();
	
	public void populate(IRecipe recipe){
		if(recipe instanceof ShapedRecipes){
			ShapedRecipes rec = (ShapedRecipes)recipe;
			this.recipe = rec.recipeItems;
		}
	}
	@Override
	public void draw(int width, int height, int left, int top, EntityPlayer player, String key, int page, int mX, int mY){

		int x, y;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		for(int i = 0; i < recipe.length; i++){
			if(i <= 2){
				x = (left + width / 2) - (65-32-2);
				y = (height/2 - 18) + (18*i);
			}else if(i > 2 && i <= 5){
				x = left + width / 2 - (65-48-2);
				y = (height/2 - 18) + (18*(i-3));
			}else if(i > 5 && i <= 9){
				x = left + width / 2 - (65-(48+16)-2);
				y = (height/2 - 18) + (18*(i-6));
			}else{
				x = left + width / 2 - (65-32-2);
				y = (height/2 - 18) + (18*i);
			}
			GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), recipe[i], x, y);
			icons.add(new ItemIcon(recipe[i], x, y));
		}
		
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_BLEND);
		
		for(ItemIcon icon : icons){
			if(icon.stack != null)
				icon.onMouseBetween(mX, mY);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void initGui(int width, int height, int left, int top,
			EntityPlayer player, List buttonList){
		
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		
	}
	
	static class ItemIcon extends GuiScreen{
		public ItemIcon(ItemStack stack, int x, int y){
			this.stack = stack;
			this.x = x;
			this.y = y;
		}
		public ItemStack stack;
		public int x, y;
		
		public void onMouseBetween(int mX, int mY){
			int xSize = x + 16;
			int ySize = y + 16;
			
			if(mX > x && mX < xSize && mY > y && mY < ySize){
				if(stack != null && stack.getDisplayName() != null)
					Minecraft.getMinecraft().fontRenderer.drawString(stack.getDisplayName(), mX + 6, mY, Color.black.getRGB());
			}
		}		
	}
}