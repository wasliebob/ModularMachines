package modularmachines.helpers;

import net.minecraft.potion.Potion;

public class PotionHelper {
	public static Potion getPotionFromID(int id){
		return Potion.potionTypes[id];
	}
	
	public static int getIDFromPotion(Potion potion){
		return potion.getId();
	}
}