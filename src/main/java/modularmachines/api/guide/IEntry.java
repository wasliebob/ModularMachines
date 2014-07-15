package modularmachines.api.guide;

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
	public void draw(int width, int height, int left, int top, EntityPlayer player);
}