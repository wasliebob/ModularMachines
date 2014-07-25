package modularmachines.blocks.containers;

import modularmachines.api.classes.TileInteracting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFilter extends Container {
		public ContainerFilter(TileInteracting tile, InventoryPlayer inventory) {
                super();
                this.tile = tile;

                /** Filter Slots */
                int k;
                int l;

                for (k = 0; k < 3; ++k){
                    for (l = 0; l < 3; ++l){
                        this.addSlotToContainer(new Slot(tile, l + k * 3, 62 + l * 18, 17 + k * 18));
                    }
                }
                
                /** Player Inventory */
                int i;

                for (i = 0; i < 3; ++i){
                    for (int j = 0; j < 9; ++j){
                        this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                    }
                }

                for (i = 0; i < 9; ++i){
                    this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
                }
        }
		public TileInteracting tile;

		@Override
        public boolean canInteractWith(EntityPlayer entityplayer) {
                return tile.isUseableByPlayer(entityplayer);
        }
        
        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slot){
        	return null;
        }
}