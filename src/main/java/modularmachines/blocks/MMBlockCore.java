package modularmachines.blocks;

import java.util.Random;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockCore extends BlockContainer implements IWrenchable{

	public MMBlockCore(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(MM.modName.toLowerCase() + "." + "block" + "." + name);

		this.name = name;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileMachineBase();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) 
	{
            TileMachineBase base = (TileMachineBase)world.getTileEntity(x, y, z);
            if(base != null && player.getHeldItem() != null){
            	Item heldItem = player.getHeldItem().getItem();
        		ForgeDirection dir = ForgeDirection.getOrientation(side);
            	if(!player.isSneaking() && heldItem == MMItems.input && base.output != dir && base.screen != dir){
            		base.input = dir;
            	}else if(!player.isSneaking() && heldItem == MMItems.output && base.input != dir && base.screen != dir){
            		base.output = dir;
            	}else if(!player.isSneaking() && heldItem == MMItems.screen && base.input != dir && base.output != dir){
            		base.screen = dir;
            	}else if(player.isSneaking() && heldItem == MMItems.programmer){
            		base.input = null;
            		base.output = null;
            		base.screen = null;
            	}
            }else{
	            if(base != null && !player.isSneaking() && base.screen != null){
	            	if(base.input != null && side == base.screen.ordinal())
	            		player.openGui(MM.instance, 1, world, x, y, z);
	            }else if(!world.isRemote){
	            	player.addChatMessage(new ChatComponentText(base.heat.getHeat() + "/" + base.heat.getMaxHeat()));
	            }
            }
            return true;
    }
	
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int side){
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, block, side);
    }

    private void dropItems(World world, int x, int y, int z){
            Random rand = new Random();

            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (!(tileEntity instanceof IInventory)) {
                    return;
            }
            IInventory inventory = (IInventory) tileEntity;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    ItemStack item = inventory.getStackInSlot(i);

                    if (item != null && item.stackSize > 0) {
                            float rx = rand.nextFloat() * 0.8F + 0.1F;
                            float ry = rand.nextFloat() * 0.8F + 0.1F;
                            float rz = rand.nextFloat() * 0.8F + 0.1F;

                            EntityItem entityItem = new EntityItem(world,
                                            x + rx, y + ry, z + rz,
                                            new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                            if (item.hasTagCompound()) {
                                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                            }

                            float factor = 0.05F;
                            entityItem.motionX = rand.nextGaussian() * factor;
                            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                            entityItem.motionZ = rand.nextGaussian() * factor;
                            world.spawnEntityInWorld(entityItem);
                            item.stackSize = 0;
                    }
            }
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "core");
	}
}