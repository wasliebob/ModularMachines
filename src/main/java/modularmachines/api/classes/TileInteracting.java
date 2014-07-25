package modularmachines.api.classes;

import java.awt.Color;

import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.main.MMInteractingUpgrades;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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
public class TileInteracting extends TileEntity implements IInventory, IHeatedMachine{
	public TileInteracting(){
		filter = new ItemStack[9];
		heat = new HeatStorage(1200, 10);
		upgrade = null;
		upgradeSide = null;
		input = null;
		output = null;
		color = new Color(0);
		meta = -1;
		programmerMode = 0;
		up = 2;
		enabled = true;
		whitelist = true;
		redstone = true;
	}
	public ItemStack[] filter;
 	public HeatStorage heat;
	public Item upgrade;
	public ForgeDirection upgradeSide;
	public ForgeDirection input;
	public ForgeDirection output;
	public Color color;
	public int meta;
	public int programmerMode;
	public int up;
	public boolean enabled;
	public boolean whitelist;
	public boolean redstone;
	
	@Override
	public void updateEntity(){
		setRedstoneInput();
		if(!worldObj.isRemote && upgrade != null && MMInteractingUpgrades.containsItem(upgrade) && MMInteractingUpgrades.getUpgrade(upgrade).action.hasRequired(this) && enabled && redstone){
			MMInteractingUpgrades.getUpgrade(upgrade).action.onUpdate(this);
		}
	}
	
	public void setRedstoneInput(){
		redstone = true;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			int power = worldObj.getIndirectPowerLevelTo(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.ordinal());
			if(power > 0)
				redstone = false;
		}
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		if(this.input != null)
			nbt.setInteger("INPUT", this.input.ordinal());
		
		if(this.output != null)
			nbt.setInteger("OUTPUT", this.output.ordinal());
		
		if(this.upgradeSide != null)
			nbt.setInteger("UPGRADESIDE", this.upgradeSide.ordinal());
	
		if(this.upgrade != null)
			nbt.setInteger("UPGRADE", Item.getIdFromItem(upgrade));
		
		if(this.color != null)
			nbt.setInteger("COLOR", this.color.getRGB());
		
		nbt.setInteger("META", this.meta);
		nbt.setInteger("PROGRAMMER", this.programmerMode);
		
		NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < filter.length; i++) {
        	ItemStack stack = filter[i];
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
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if(nbt.getInteger("INPUT") != 0)
			this.input = ForgeDirection.getOrientation(nbt.getInteger("INPUT"));
		
		if(nbt.getInteger("OUTPUT") != 0)
			this.output = ForgeDirection.getOrientation(nbt.getInteger("OUTPUT"));
		if(nbt.getInteger("UPGRADESIDE") != 0)
			this.upgradeSide = ForgeDirection.getOrientation(nbt.getInteger("UPGRADESIDE"));
		
		this.upgrade = Item.getItemById(nbt.getInteger("UPGRADE"));
		this.color = new Color(nbt.getInteger("COLOR"));
		this.meta = nbt.getInteger("META");
		this.programmerMode = nbt.getInteger("PROGRAMMER");
		
		NBTTagList tagList = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < filter.length) {
				filter[slot] = ItemStack.loadItemStackFromNBT(tag);
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
	public int getSizeInventory() {
		return filter.length;
	}
	
	public void markBlockForUpdate(){
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		   if(i > filter.length)
	            return filter[0];
		   else
			   return filter[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {	
		filter[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
            itemstack.stackSize = getInventoryStackLimit();
        }        
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j){
		if (filter[i] != null){
            if (filter[i].stackSize <= j){
                ItemStack itemstack = filter[i];
                filter[i] = null;
                return itemstack;
            }else{
                ItemStack itemstack1 = filter[i].splitStack(j);

                if (filter[i].stackSize == 0){
                	filter[i] = null;
                }
                
                return itemstack1;
            }
        }else{
            return null;
        }
	}

    @Override
    public ItemStack getStackInSlotOnClosing(int i){
        if (filter[i] != null){
            ItemStack itemstack = filter[i];
            filter[i] = null;
            return itemstack;
        }else{
            return null;
        }
    }

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		  if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
	                 return false;

	         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}

	@Override
	public String getInventoryName() {
		return "mm.filter";
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
}