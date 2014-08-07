/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.classes;

import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.interfaces.IHeatGenerator;
import modularmachines.api.heat.interfaces.IHeatTransport;
import modularmachines.api.main.MMGeneratorUpgrades;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.interfaces.IScanable;
import modularmachines.api.upgrades.IGeneratorAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileGenerator extends TileEntity implements ISidedInventory, IHeatGenerator, IScanable{    
	public TileGenerator(){
		stacks = new ItemStack[3];
		storage = new HeatStorage(1200, 10);
	}
	public HeatStorage storage;
	public ItemStack[] stacks;
	
	@Override
	public void updateEntity(){
		ItemStack upgrade = getStackInSlot(2);
		if(!worldObj.isRemote && upgrade != null && MMGeneratorUpgrades.containsItem(upgrade.getItem()) && MMGeneratorUpgrades.getUpgrade(upgrade.getItem()).action != null){
			IGeneratorAction action = MMGeneratorUpgrades.getUpgrade(upgrade.getItem()).action;
			increaseHeat(action.generateHeat(this));
			markForUpdate();
			if(getBound() != null){
				IHeatTransport ht = getBound();
				if(ht.canAdd(calculateSending(ht))){
					ht.getHeatStorage().increaseHeat(calculateSending(ht));
					storage.decreaseEnergy(calculateSending(ht));
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		nbt.setInteger("ENERGY", this.storage.heat);
		NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < stacks.length; i++) {
        	ItemStack stack = stacks[i];
        	if (stack != null) {
        		NBTTagCompound tag = new NBTTagCompound();
        		tag.setByte("Slot", (byte) i);
        		stack.writeToNBT(tag);
        		itemList.appendTag(tag);
        	}
        }
        nbt.setTag("Inventory", itemList);
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.storage.setHeat(nbt.getInteger("ENERGY"));
		NBTTagList tagList = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < stacks.length) {
				stacks[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
	    NBTTagCompound tagCompound = new NBTTagCompound();
	    writeToNBT(tagCompound);
	    
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public int getSizeInventory() {
		return stacks.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		   if(i > stacks.length)
	            return stacks[0];
	            else
	            	return stacks[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {	
		stacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
            itemstack.stackSize = getInventoryStackLimit();
        }        
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j){
		if (stacks[i] != null){
            if (stacks[i].stackSize <= j){
                ItemStack itemstack = stacks[i];
                stacks[i] = null;
                return itemstack;
            }else{
                ItemStack itemstack1 = stacks[i].splitStack(j);

                if (stacks[i].stackSize == 0){
                	stacks[i] = null;
                }
                
                return itemstack1;
            }
        }else{
            return null;
        }
	}

    @Override
    public ItemStack getStackInSlotOnClosing(int i){
        if (stacks[i] != null){
            ItemStack itemstack = stacks[i];
            stacks[i] = null;
            return itemstack;
        }else{
            return null;
        }
    }

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		  if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
	                 return false;

	         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	     }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side){
		return new int[]{0, 1};
	}
		
	@Override
	public boolean canInsertItem(int i, ItemStack stack, int side) {
		return isItemValidForSlot(i, stack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack stack, int side) {
		return true;
	}

	@Override
	public String getInventoryName() {
		return "mm.generator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public World getWorldObj(){
		return this.worldObj;
	}
	
	public void markForUpdate(){
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public IHeatTransport getBound(){
		TileEntity up = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP);
		TileEntity down = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.DOWN);

		TileEntity right = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.EAST);
		TileEntity left = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.WEST);

		TileEntity front = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.SOUTH);
		TileEntity back = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.NORTH);

		if(up != null && up instanceof IHeatTransport)
			return (IHeatTransport)up;
		if(down != null && down instanceof IHeatTransport)
			return (IHeatTransport)down;
		
		if(right != null && right instanceof IHeatTransport)
			return (IHeatTransport)right;
		if(left != null && left instanceof IHeatTransport)
			return (IHeatTransport)left;
		
		if(front != null && front instanceof IHeatTransport)
			return (IHeatTransport)front;
		if(back != null && back instanceof IHeatTransport)
			return (IHeatTransport)back;
		
		return null;
	}

	@Override
	public void increaseHeat(int heat){
		if(storage != null && storage.getHeat() + heat <= storage.getMaxHeat()){
			storage.increaseHeat(heat);
		}
	}
	
	public int calculateSending(IHeatTransport transport) {
		if(storage.getHeat() > transport.getHeatStorage().getTransfer())
			return transport.getHeatStorage().getTransfer();
		else if(storage.getHeat() == transport.getHeatStorage().getTransfer())
			return transport.getHeatStorage().getTransfer();
		else if(storage.getHeat() < transport.getHeatStorage().getTransfer())
			return storage.getHeat();
		return 0;
	}

	@Override
	public ItemStack getFuel() {
		return stacks[0];
	}

	@Override
	public NBTTagCompound getInfo(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("name", "Heat Generator");
		tag.setString("heat", "Heat: " + storage.getHeat() + "/" + storage.getMaxHeat());
		tag.setString("transfer", "Transfer: " + storage.getTransfer());
		return tag;
	}
	
	@Override
	public HeatStorage getHeatStorage(){
		return this.storage;
	}
}