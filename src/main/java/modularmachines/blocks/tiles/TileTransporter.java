package modularmachines.blocks.tiles;

import modularmachines.helpers.DirectionHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileTransporter extends TileEntity{
	public TileTransporter(){
		range = 10;
		input = null;
		output = null;
	}
	public ForgeDirection input;
	public ForgeDirection output;
	public int range;
	
	@Override
	public void updateEntity(){
		if(getInput() != null && getOutput() != null){
			IInventory input = getInput();
			IInventory output = getOutput();
			for(int i = 0; i < input.getSizeInventory(); i++){
				ItemStack stack = input.getStackInSlot(i);
				if(stack != null){
					if(hasFreeSlot(output, stack)){
						addItem(input, output, i, getFreeSlot(output, stack));
					}
				}
			}
		}
	}
	
	public IInventory getOutput(){
		if(this.output != null){
			TileEntity te = DirectionHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, output, range);
			if(te != null && te instanceof IInventory){
				IInventory inv = (IInventory)te;
				return inv;
			}
		}
		return null;
	}
	
	public IInventory getInput(){
		if(this.input != null){
			TileEntity te = DirectionHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, input, range);
			if(te != null && te instanceof IInventory){
				IInventory inv = (IInventory)te;
				return inv;
			}
		}
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		if(this.input != null)
			nbt.setInteger("INPUT", this.input.ordinal());
		
		if(this.output != null)
			nbt.setInteger("OUTPUT", this.output.ordinal());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if(nbt.getInteger("INPUT") != 0)
			this.input = ForgeDirection.getOrientation(nbt.getInteger("INPUT"));
		
		if(nbt.getInteger("OUTPUT") != 0)
			this.output = ForgeDirection.getOrientation(nbt.getInteger("OUTPUT"));
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

	public boolean hasFreeSlot(IInventory inv, ItemStack stack){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack slot = inv.getStackInSlot(i);
			if(slot == null)
				return true;
			else if(slot != null && (slot.getItem() == stack.getItem() && slot.getItemDamage() == stack.getItemDamage()) && slot.stackSize + 1 <= slot.getMaxStackSize())
				return true;
		}
		return false;
	}
	
	public int getFreeSlot(IInventory inv, ItemStack stack){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack slot = inv.getStackInSlot(i);
			if(slot == null)
				return i;
			else if(slot != null && (slot.getItem() == stack.getItem() && slot.getItemDamage() == stack.getItemDamage()) && slot.stackSize + 1 <= slot.getMaxStackSize())
				return i;
		}
		return 0;
	}
	
	public void addItem(IInventory input, IInventory output, int inputSlot, int outputSlot){
		ItemStack in = input.getStackInSlot(inputSlot);
		ItemStack out = output.getStackInSlot(outputSlot);
		if(in != null){
			if(out == null){
				output.setInventorySlotContents(outputSlot, new ItemStack(in.getItem(), 1, in.getItemDamage()));
				
				if(in.stackSize > 1)
					in.stackSize = in.stackSize - 1;
				else
					input.setInventorySlotContents(inputSlot, null);
				
			}else if(out != null){
				output.setInventorySlotContents(outputSlot, new ItemStack(in.getItem(), out.stackSize + 1, in.getItemDamage()));
			
				if(in.stackSize > 1)
					in.stackSize = in.stackSize - 1;
				else
					input.setInventorySlotContents(inputSlot, null);
			}
		}
	}
}