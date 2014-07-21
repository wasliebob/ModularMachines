package modularmachines.api.upgrades;

import modularmachines.api.classes.TileInteracting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IInteractingAction {
	public void onUpdate(TileInteracting ti);
	
	/** Only when clicked on the upgrade side */
	public void onActivate(TileInteracting ti, EntityPlayer player);
	
	/** Ignores side */
	public void onActivateClick(TileInteracting ti, EntityPlayer player);
	
	public void onActivateWithItem(TileInteracting ti, ItemStack held, EntityPlayer player);
	
	public void onActivateWithProgrammer(TileInteracting ti, EntityPlayer player);
	
	public void increaseHeat(TileInteracting ti, int heat);
	
	public void decreaseHeat(TileInteracting ti, int heat);
	
	public boolean hasRequired(TileInteracting ti);
}