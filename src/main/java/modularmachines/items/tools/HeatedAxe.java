/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.items.tools;

import java.util.HashMap;
import java.util.List;

import modularmachines.api.heat.interfaces.IHeatContainer;
import modularmachines.api.heat.interfaces.IHeatedTool;
import modularmachines.api.misc.helpers.HeatContainerHelper;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class HeatedAxe extends ItemAxe implements IHeatContainer, IHeatedTool{

	public HeatedAxe(String name){
		super(MMItems.heated);
		setHarvestLevel("axe", 4);

		setMaxStackSize(1);
		setUnlocalizedName(MM.modName.toLowerCase() + "." + "item" + "." + name.toLowerCase());
		setCreativeTab(MMTabs.tabMain);
		
		modes.put(0, "Normal");
		modes.put(1, "Smelt");
		modes.put(2, "Timber");
		cost.put(0, 1);
		cost.put(1, 6);
		cost.put(2, 11);

		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	HashMap<Integer, Integer> cost = new HashMap<Integer, Integer>();
	HashMap<Integer, String> modes = new HashMap<Integer, String>();
	int maxModes = 2;
	
    @Override
    public boolean isItemTool(ItemStack stack) {
            return true;
    }
    
	@Override
    public void registerIcons(IIconRegister ir){
        itemIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "axe_heated");
	}
	
	public int calculateCost(ItemStack stack){
		return cost.get(stack.getItemDamage());
	}
	
	@Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living){
		if(living instanceof EntityPlayer && ((EntityPlayer)living).capabilities.isCreativeMode)
			return true;
		
		if(getHeatStored(stack) >= calculateCost(stack)){
			decreaseHeatStored(stack, calculateCost(stack));
		}
		return true;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
		String name = this.modes.get(stack.getItemDamage());
		if(stack.hasTagCompound())
			list.add("Heat: " + stack.getTagCompound().getInteger("HEAT") + "/" + this.getCapacity(stack));
		list.add("Mode: " + name);
	}
	
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
    	super.onUpdate(stack, world, entity, par4, par5);
		if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
		
		if(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
			this.setHeatStored(stack, this.getCapacity(stack));
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
    	super.onCreated(stack, world, player);
    	if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    }

	@Override
	public int getHeatStored(ItemStack stack) {
		return HeatContainerHelper.getHeatStored(stack);
	}

	@Override
	public void setHeatStored(ItemStack stack, int heat) {
		HeatContainerHelper.setHeatStored(stack, heat);
	}

	@Override
	public void increaseHeatStored(ItemStack stack, int heat) {
		HeatContainerHelper.increaseHeatStored(stack, heat);
	}

	@Override
	public void decreaseHeatStored(ItemStack stack, int heat) {
		HeatContainerHelper.decreaseHeatStored(stack, heat);
	}

	@Override
	public int getMaxModes(){
		return this.maxModes;
	}
	
	@Override
	public HashMap<Integer, String> getModes(){
		return this.modes;
	}
	
	@Override
	public int getCapacity(ItemStack stack){
		return 1000;
	}
	
	 @Override
	 public int getHarvestLevel(ItemStack stack, String toolClass){
		 if(this.getHeatStored(stack) - this.calculateCost(stack) < 0)
			 return 0;
		 else
			return super.getHarvestLevel(stack, toolClass);
	 }

	 @Override
	 public float getDigSpeed(ItemStack stack, Block block, int meta){
		 if(this.getHeatStored(stack) - this.calculateCost(stack) < 0)
			 return 0F;
		 else
			 return super.getDigSpeed(stack, block, meta);
	 }
}