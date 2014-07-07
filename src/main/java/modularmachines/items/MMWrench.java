package modularmachines.items;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.misc.helpers.Utils;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.helpers.misc.WrenchHelper;
import wasliecore.interfaces.IWrench;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMWrench extends Item implements IWrench{

	public MMWrench(String name) {
		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMain);
		this.name = name;		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;

	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "wrench");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote && player.isSneaking()){
			Block b = Utils.getTargetBlock(player);
			int meta = Utils.getTargetBlockMeta(player);
			ItemStack s = null;
			if(b != null && (WrenchHelper.isWrenchable(b, 0) || b instanceof IWrenchable)){
				s = new ItemStack(b, 1, meta);
				if(s != null){
					int x = Utils.getTargetX(player);
					int y = Utils.getTargetY(player);
					int z = Utils.getTargetZ(player);
					
					Utils.dropBlock(world, x, y, z, s);
					Utils.dropContent(world, x, y, z);
					world.setBlock(x, y, z, Blocks.air);
					
					b.breakBlock(world, x, y, z, b, 0);
					return stack;
				}
			}
		}else if(!player.isSneaking()){
			Block b = Utils.getTargetBlock(player);
			int side = Utils.getTargetBlockSide(player);
			TileEntity te = Utils.getTargetTile(player);
			if(b != null && (WrenchHelper.isWrenchable(b, 0) || b instanceof IWrenchable) && te != null){
				int x = Utils.getTargetX(player);
				int y = Utils.getTargetY(player);
				int z = Utils.getTargetZ(player);
				ForgeDirection dir = ForgeDirection.getOrientation(side);
				if(te instanceof TileInteracting){
					TileInteracting ti = (TileInteracting)te;
					if(ti.upgradeSide == dir){
						if(!world.isRemote)
							Utils.dropBlock(world, x, y, z, new ItemStack(ti.upgrade));
						ti.upgradeSide = null;
						ti.upgrade = null;
					}
					
					if(ti.input == dir){
						ti.input = null;
						if(!world.isRemote)
							Utils.dropBlock(world, x, y, z, new ItemStack(MMItems.input));
					}
					
					if(ti.output == dir){
						ti.output = null;
						if(!world.isRemote)
							Utils.dropBlock(world, x, y, z, new ItemStack(MMItems.output));
					}
				}
				return stack;
			}
		}
		return stack;
    }
}