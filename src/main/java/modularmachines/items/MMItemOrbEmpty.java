/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 16:47:25
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items;

import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMItemOrbEmpty extends Item{
	public MMItemOrbEmpty(){
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + "empty orb");
		setCreativeTab(MMTabs.tabPotionOrbs);
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@Override
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "orb");
	}
}