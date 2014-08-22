/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import java.util.ArrayList;
import java.util.Random;

import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.helpers.Utils;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockCore extends BlockContainer implements IWrenchable, IGuided{

	public MMBlockCore(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);

		this.name = name;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	IIcon side;
	IIcon front;
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileMachineBase();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileMachineBase base = (TileMachineBase)world.getTileEntity(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(side);

		if(base != null && player.getHeldItem() != null && player.getHeldItem().getItem() != MMItems.wrench && player.getHeldItem().getItem() != MMItems.guide){
			Item heldItem = player.getHeldItem().getItem();
			if(!player.isSneaking() && heldItem == MMItems.input && base.input == null && base.output != dir && base.expension != dir){
				base.input = dir;
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
			}else if(!player.isSneaking() && heldItem == MMItems.output && base.output == null && base.input != dir && base.expension != dir){
				base.output = dir;
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
			}else if(!player.isSneaking() && heldItem == MMItems.expension && base.expension == null && base.input != dir && base.output != dir){
				base.expension = dir;
				if(player.getHeldItem().stackSize > 1)
					player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
				else
					player.setCurrentItemOrArmor(0, null);
			}else if(base != null && !player.isSneaking() && dir != base.input && dir != base.output && dir != base.expension){
				player.openGui(MM.instance, 1, world, x, y, z);
			}
		}else if(base != null && !player.isSneaking() && dir != base.input && dir != base.output && dir != base.expension){
				player.openGui(MM.instance, 1, world, x, y, z);
		}
		return false;
	}
	
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int side){
    	TileMachineBase base = (TileMachineBase)world.getTileEntity(x, y, z);
    	
    	ArrayList<ItemStack> equiped = new ArrayList<ItemStack>();
    	
    	if(base.input != null)
    		equiped.add(new ItemStack(MMItems.input));
    	if(base.output != null)
    		equiped.add(new ItemStack(MMItems.output));
    	if(base.expension != null)
    		equiped.add(new ItemStack(MMItems.expension));
    	
    	for(ItemStack stack : equiped){
    		Utils.dropBlock(world, x, y, z, stack);
    	}
    	
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
    public void registerBlockIcons(IIconRegister ir) {
		front = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "core_front");
		side = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "core_side");
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		ForgeDirection dir = DirectionHelper.getFace(living);
		world.setBlockMetadataWithNotify(x, y, z, dir.ordinal(), 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int fside, int meta){
		ForgeDirection dir = ForgeDirection.getOrientation(fside);
		
        if(meta == 0 && dir == ForgeDirection.SOUTH)
            return front;
        else if(fside == meta && fside > 1)
            return front;
		else
			return side;
	}
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        int side = target.sideHit;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        TileEntity te = world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        if(te != null && te instanceof TileMachineBase){
        	TileMachineBase tr = (TileMachineBase)te;
        	if(dir == tr.input)
        		return new ItemStack(MMItems.input);
        	else if(dir == tr.output)
        		return new ItemStack(MMItems.output);
        	else if(dir == tr.expension)
        		return new ItemStack(MMItems.expension);
        }
        return super.getPickBlock(target, world, x, y, z);
    }

	@Override
	public String getKey(){
		return "Modular Core";
	}
}