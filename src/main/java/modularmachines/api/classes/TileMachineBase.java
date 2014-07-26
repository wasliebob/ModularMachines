package modularmachines.api.classes;

import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.MMHeatFuels;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.main.MMUpgrades;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.interfaces.IScanable;
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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileMachineBase extends TileEntity implements ISidedInventory, IHeatedMachine, IFluidHandler, IScanable{    
	public TileMachineBase(){
		stacks = new ItemStack[6];
		heat = new HeatStorage(1200, 10);
		tank_info = new FluidTankInfo(tank.getFluid(), tank.getCapacity());
		input = null;
		output = null;
		screen = null;
		expension = null;
		enabled = true;
	}
	public ItemStack[] stacks;
	public String type;
	public HeatStorage heat;
	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	public FluidTankInfo tank_info;
	public ForgeDirection input;
	public ForgeDirection output;
	public ForgeDirection screen;
	public ForgeDirection expension;
	public boolean enabled;

	@Override
	public void updateEntity(){		
		if(!worldObj.isRemote && enabled == true){
			ItemStack fuel = getStackInSlot(1);
			ItemStack upgrade = getStackInSlot(3);
			if(fuel != null && MMHeatFuels.containsItem(fuel.getItem()) && heat != null && heat.getHeat() + MMHeatFuels.getFuel(fuel.getItem()) <= heat.getMaxHeat())
				addHeat();
			
			if(upgrade != null && MMUpgrades.containsItem(upgrade.getItem()) && MMUpgrades.getUpgrade(upgrade.getItem()).action.hasRequired(this))
				MMUpgrades.getUpgrade(upgrade.getItem()).action.onUpdate(this);
		}
	}
	
	public void addHeat(){
		if(getStackInSlot(1).stackSize > 1){
			heat.increaseHeat(MMHeatFuels.getFuel(getStackInSlot(1).getItem()));
			stacks[1].stackSize--;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}else{
			heat.increaseHeat(MMHeatFuels.getFuel(getStackInSlot(1).getItem()));
			stacks[1] = null;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("ENERGY", this.heat.heat);
		nbt.setBoolean("ENABLED", this.enabled);
		
		if(this.input != null)
			nbt.setInteger("INPUT", this.input.ordinal());
		
		if(this.output != null)
			nbt.setInteger("OUTPUT", this.output.ordinal());
		
		if(this.screen != null)
			nbt.setInteger("SCREEN", this.screen.ordinal());
		
		if(this.expension != null)
			nbt.setInteger("EXPENSION", this.expension.ordinal());
		
		if(this.tank.getFluid() != null)
			nbt.setInteger("FLUID", this.tank.getFluid().getFluid().getID());
		
		nbt.setInteger("AMOUNT", this.tank.getFluidAmount());
		
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
		this.enabled = nbt.getBoolean("ENABLED");
		this.heat.setHeat(nbt.getInteger("ENERGY"));
		
		if(nbt.getInteger("INPUT") != 0)
			this.input = ForgeDirection.getOrientation(nbt.getInteger("INPUT"));
		
		if(nbt.getInteger("OUTPUT") != 0)
			this.output = ForgeDirection.getOrientation(nbt.getInteger("OUTPUT"));
		
		if(nbt.getInteger("SCREEN") != 0)
			this.screen = ForgeDirection.getOrientation(nbt.getInteger("SCREEN"));

		if(nbt.getInteger("EXPENSION") != 0)
			this.expension = ForgeDirection.getOrientation(nbt.getInteger("EXPENSION"));

	
		if(FluidRegistry.getFluid(nbt.getInteger("FLUID")) != null)
			this.tank.setFluid(new FluidStack(FluidRegistry.getFluid(nbt.getInteger("FLUID")), nbt.getInteger("AMOUNT")));
		
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
		if(MMHeatFuels.containsItem(stack.getItem()) && i == 1)
			return true;
		else if(MMUpgrades.containsItem(stack.getItem()) && (i == 3))
			return true;
		else if(i == 2)
			return false;
		else 
			return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side){
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		if(dir == this.input)
			return new int[]{0, 1, 3};
		else if(dir == this.output)
			return new int[]{2};
		return null;
	}
		
	@Override
	public boolean canInsertItem(int i, ItemStack stack, int side) {
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		if(dir == this.input)
			return isItemValidForSlot(i, stack);
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack stack, int side) {
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		if(dir == this.output)
			return true;
		return false;
	}

	@Override
	public String getInventoryName() {
		return "mm.base";
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
	public HeatStorage getHeatStorage() {
		return this.heat;
	}

	@Override
	public boolean canAdd(int energy) {
		return heat.getHeat() + energy <= heat.getMaxHeat();
	}

	@Override
	public ForgeDirection getInput() {
		return this.input;
	}
	
	@Override
	public ForgeDirection getOutput() {
		return this.output;
	}
	
	@Override
	public int xCoord(){
		return this.xCoord;
	}
	
	@Override
	public int yCoord(){
		return this.yCoord;
	}
	
	@Override
	public int zCoord(){
		return this.zCoord;
	}
	
	@Override
	public World getWorldObj(){
		return this.worldObj;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{this.tank_info};
	}
	
	public TileEntity getExpansion(){
		TileEntity te = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, expension);
		if(te != null)
			return te;
		return null;
	}
	
	public void markForUpdate(){
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public NBTTagCompound getInfo(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("name", "Interacting Core");
		tag.setString("heat", "Heat: " + heat.getHeat() + "/" + heat.getMaxHeat());
		tag.setString("transfer", "Transfer: " + heat.getTransfer());
		return tag;
	}
}