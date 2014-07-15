package modularmachines.api.guide;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public interface IEntry {
	/**
	 * This get's called in GuiEntry, you can do whatever you want here (images, recipes, icons, text, combination of them)
	 * @param width
	 * @param height
	 * @param left
	 * @param top
	 * @param player
	 * The player who has the book open
	 */
	public void draw(int width, int height, int left, int top, EntityPlayer player, String key, int page);
	
	@SuppressWarnings("rawtypes")
	public void initGui(int width, int height, int left, int top, EntityPlayer player, List buttonList);

	public void actionPerformed(GuiButton button);
}