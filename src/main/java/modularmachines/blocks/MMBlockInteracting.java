/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import java.util.ArrayList;
import java.util.Random;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.guide.IGuided;
import modularmachines.api.main.MMInteractingUpgrades;
import modularmachines.api.upgrades.IInteractingAction;
import modularmachines.api.upgrades.IInteractingUpgrade;
import modularmachines.items.MMProgrammer;
import modularmachines.items.MMWrench;
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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.helpers.Utils;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockInteracting extends BlockContainer implements IWrenchable, IGuided{

	public MMBlockInteracting(String name) {
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
		return new TileInteracting();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
            TileInteracting ti = (TileInteracting)world.getTileEntity(x, y, z);
            if(ti.upgradeSide == ForgeDirection.getOrientation(side) && ti.upgrade != null && MMInteractingUpgrades.containsItem(ti.upgrade)){
            	IInteractingAction action = MMInteractingUpgrades.getUpgrade(ti.upgrade).action;
            	if(action != null){
	            	if(player.getHeldItem() != null && player.getHeldItem().getItem() == MMItems.programmer && side == ti.upgradeSide.ordinal())
	            		action.onActivateWithProgrammer(ti, player);
	            	else if(player.getHeldItem() == null)
	            		action.onActivate(ti, player);
	            	else if(player.getHeldItem() != null)
	            		action.onActivateWithItem(ti, player.getHeldItem(), player);
	            	return false;
            	}
            }else if(ti != null && !player.isSneaking() && player.getHeldItem() != null && player.getHeldItem().getItem() != MMItems.programmer){
            	Item heldItem = player.getHeldItem().getItem();
        		ForgeDirection dir = ForgeDirection.getOrientation(side);
            	if(heldItem == MMItems.input && ti.input == null && ti.output != dir && ti.upgradeSide != dir){
            		ti.input = dir;
            		if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
            		else
						player.setCurrentItemOrArmor(0, null);
            	}else if(heldItem == MMItems.output && ti.output == null && ti.input != dir && ti.upgradeSide != dir){
            		ti.output = dir;
            		if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
            		else
						player.setCurrentItemOrArmor(0, null);
            	}else if(MMInteractingUpgrades.containsItem(heldItem) && (ti.upgradeSide == null && ti.upgrade == null) && ti.input != dir && ti.output != dir){
            		ti.upgradeSide = dir;
            		ti.upgrade = heldItem;
            		ti.color = ((IInteractingUpgrade)heldItem).getColor();
            		if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
            		else
						player.setCurrentItemOrArmor(0, null);
            	}
            }else if(ti != null && player.getHeldItem() == null && ti.upgrade != null){
            	IInteractingAction action = MMInteractingUpgrades.getUpgrade(ti.upgrade).action;
            	action.onActivateClick(ti, player);
            }else if(ti != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer && ti.upgradeSide == ForgeDirection.getOrientation(side) && !(player.getHeldItem().getItem() instanceof MMWrench)){
            	IInteractingAction action = MMInteractingUpgrades.getUpgrade(ti.upgrade).action;
            	action.onActivateWithProgrammer(ti, player);
            }else if(ti != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMProgrammer && ti.upgradeSide != ForgeDirection.getOrientation(side)){
        		player.openGui(MM.instance, 2, world, x, y, z);
            }
            return false;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "core_side");
	}
	
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int side){
    	TileInteracting te = (TileInteracting)world.getTileEntity(x, y, z);
    	
    	ArrayList<ItemStack> equiped = new ArrayList<ItemStack>();
    	
    	if(te.input != null)
    		equiped.add(new ItemStack(MMItems.input));
    	if(te.output != null)
    		equiped.add(new ItemStack(MMItems.output));
    	if(te.upgrade != null)
    		equiped.add(new ItemStack(te.upgrade));
    	
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
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        int side = target.sideHit;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        TileEntity te = world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        if(te != null && te instanceof TileInteracting){
        	TileInteracting tr = (TileInteracting)te;
        	if(dir == tr.input)
        		return new ItemStack(MMItems.input);
        	else if(dir == tr.output)
        		return new ItemStack(MMItems.output);
        	else if(dir == tr.upgradeSide)
        		return new ItemStack(tr.upgrade);
        }
        return super.getPickBlock(target, world, x, y, z);
    }

	@Override
	public String getKey(){
		return "Interacting Core";
	}
}