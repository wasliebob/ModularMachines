package modularmachines.blocks.containers.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFilter extends Slot{

    public SlotFilter(IInventory inv, int x, int y, int z){
        super(inv, x, y, z);
    }
    
    public boolean isItemValid(ItemStack stack){
        return true;
    }
    
    public boolean canTakeStack(EntityPlayer player){
        return false;
    }
}