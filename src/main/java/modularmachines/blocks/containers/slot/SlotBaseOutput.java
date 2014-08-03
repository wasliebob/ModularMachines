/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.containers.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBaseOutput extends Slot{

    public SlotBaseOutput(IInventory p_i1813_2_, int p_i1813_3_, int p_i1813_4_, int p_i1813_5_){
        super(p_i1813_2_, p_i1813_3_, p_i1813_4_, p_i1813_5_);
    }
    
    public boolean isItemValid(ItemStack p_75214_1_){
        return false;
    }
}
