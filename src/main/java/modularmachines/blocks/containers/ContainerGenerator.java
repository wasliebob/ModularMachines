/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.containers;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.main.MMGeneratorUpgrades;
import modularmachines.blocks.containers.slot.SlotBaseOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGenerator extends Container {
		public ContainerGenerator(TileGenerator tile, InventoryPlayer inventory) {
                super();
                this.tile = tile;
                this.addSlotToContainer(new Slot(tile, 0, 56, 35));
                
                /** Output */
                this.addSlotToContainer(new SlotBaseOutput(tile, 1, 116, 35));

                /** Upgrade Slots */
                this.addSlotToContainer(new Slot(tile, 2, 153, 35));

                int i;

                for (i = 0; i < 3; ++i)
                {
                    for (int j = 0; j < 9; ++j)
                    {
                        this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                    }
                }

                for (i = 0; i < 9; ++i)
                {
                    this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
                }
        }
		public TileGenerator tile;

        @Override
        public boolean canInteractWith(EntityPlayer entityplayer) {
                return tile.isUseableByPlayer(entityplayer);
        }
        
        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
                ItemStack stack = null;
                Slot slotObject = (Slot) inventorySlots.get(slot);

                if (slotObject != null && slotObject.getHasStack()) 
                {
                        ItemStack stackInSlot = slotObject.getStack();
                        stack = stackInSlot.copy();

                        if (slot < 6)
                        {
                                if (!this.mergeItemStack(stackInSlot, 3, 37, true)) 
                                {
                                        return null;
                                }
                        }
                        else if(doesItemFitInSlot(stackInSlot) && !this.mergeItemStack(stackInSlot, getStartSlot(stackInSlot), getEndSlot(stackInSlot), false))
                        {
                                return null;
                        }
                        
                        if (stackInSlot.stackSize == 0) 
                        {
                                slotObject.putStack(null);
                        }
                        else 
                        {
                                slotObject.onSlotChanged();
                        }

                        if (stackInSlot.stackSize == stack.stackSize) 
                        {
                                return null;
                        }
                        slotObject.onPickupFromSlot(player, stackInSlot);
                }
                return stack;
        }
        
        public int getStartSlot(ItemStack stack)
        {
        	if(MMGeneratorUpgrades.containsItem(stack.getItem()))
        		return 2;
        	else
        		return 0;
        }
        
        public int getEndSlot(ItemStack stack)
        {
        	if(MMGeneratorUpgrades.containsItem(stack.getItem()))
        		return 3;
        	else
        		return 1;
        }
        
        public boolean doesItemFitInSlot(ItemStack stack){
        	return true;
        }
}