package modularmachines.blocks.containers;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.heat.MMHeatFuels;
import modularmachines.api.main.MMUpgrades;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMachineBase extends Container {
		public ContainerMachineBase(TileMachineBase tile, InventoryPlayer inventory) {
                super();
                this.tile = tile;
                this.addSlotToContainer(new Slot(tile, 0, 56, 17));
                this.addSlotToContainer(new Slot(tile, 1, 56, 53));
                
                /** Output */
                this.addSlotToContainer(new Slot(tile, 2, 116, 35));

                /** Upgrade Slots */
                this.addSlotToContainer(new Slot(tile, 3, 153, 9));
                this.addSlotToContainer(new Slot(tile, 4, 153, 31));
                this.addSlotToContainer(new Slot(tile, 5, 153, 53));

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
		int x, y;
		public TileMachineBase tile;

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
        	if(MMUpgrades.containsItem(stack.getItem()))
        		return 3;
        	else if(MMHeatFuels.containsItem(stack.getItem()))
        		return 1;
        	else
        		return 0;
        }
        
        public int getEndSlot(ItemStack stack)
        {
        	if(MMUpgrades.containsItem(stack.getItem()))
        		return 4;
        	else if(MMHeatFuels.containsItem(stack.getItem()))
        		return 2;
        	else
        		return 1;
        }
        
        public boolean doesItemFitInSlot(ItemStack stack)
        {
        	if(MMUpgrades.containsItem(stack.getItem()))
        		return true;
        	else if(MMHeatFuels.containsItem(stack.getItem()))
        		return true;
        	else
        		return true;
        }
}