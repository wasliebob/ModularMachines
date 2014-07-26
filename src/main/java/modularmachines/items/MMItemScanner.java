package modularmachines.items;

import modularmachines.api.misc.interfaces.IScanable;
import modularmachines.blocks.guis.GuiScanner;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wasliecore.helpers.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMItemScanner extends Item {
	public MMItemScanner() {
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + "scanner");
		setCreativeTab(MMTabs.tabMain);
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}

	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "scanner");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(player.isSneaking()){
			TileEntity te = Utils.getTargetTile(player);
			if(te != null && te instanceof IScanable)
				Minecraft.getMinecraft().displayGuiScreen(new GuiScanner((IScanable)te));
		}
		return stack;
    }
}