/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.tiles.intergration;

import ic2.api.energy.prefab.BasicSink;
import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.enums.EnumHeatConverting;
import modularmachines.api.heat.interfaces.IHeatTransport;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.heat.interfaces.IHeatedTile;
import modularmachines.api.misc.helpers.DirectionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePowerConverterIC2 extends TileEntity implements IHeatedTile{
	public TilePowerConverterIC2(){
		heat = new HeatStorage(1200, 100);
	}
	public HeatStorage heat;
	public BasicSink sink = new BasicSink(this, 100000, 4);
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			sink.updateEntity();
			if(sink.getEnergyStored() >= EnumHeatConverting.EU.getValue() && heat != null && heat.getHeat() + this.calculateRate() <= heat.getMaxHeat()){
				heat.increaseHeat(this.calculateRate());
				sink.useEnergy(this.calculateRate());
			}
			if(this.getTransporter() != null){
				IHeatTransport ht = getTransporter();
				if(ht.canAdd(this.calculateSending(ht))){
					ht.getHeatStorage().increaseHeat(this.calculateSending(ht));
					heat.decreaseEnergy(this.calculateSending(ht));
				}
			}
		}
	}
	
	public IHeatTransport getTransporter(){
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
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("HEAT", this.heat.getHeat());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.heat.setHeat(nbt.getInteger("HEAT"));
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
	public void addHeat(IHeatTransport te, int heat) {
		
	}

	@Override
	public int getHeat(IHeatTransport te) {
		return heat.getHeat();
	}

	@Override
	public int getMaxHeat(IHeatTransport te) {
		return heat.getMaxHeat();
	}

	@Override
	public boolean isFull(IHeatTransport te, int heat, int maxHeat) {
		return this.heat.getHeat() >= this.heat.getMaxHeat();
	}

	@Override
	public boolean canReceive(IHeatTransport te, int heat) {
		return false;
	}

	@Override
	public int calculateSending(IHeatTransport transport) {
		if(heat.getHeat() > transport.getHeatStorage().getTransfer())
			return transport.getHeatStorage().getTransfer();
		else if(heat.getHeat() == transport.getHeatStorage().getTransfer())
			return transport.getHeatStorage().getTransfer();
		else if(heat.getHeat() < transport.getHeatStorage().getTransfer())
			return heat.getHeat();
		return 0;
	}
	
	@Override
	public int calculateSending(IHeatedMachine machine) {
		if(heat.getHeat() > machine.getHeatStorage().getTransfer())
			return machine.getHeatStorage().getTransfer();
		else if(heat.getHeat() == machine.getHeatStorage().getTransfer())
			return machine.getHeatStorage().getTransfer();
		else if(heat.getHeat() < machine.getHeatStorage().getTransfer())
			return heat.getHeat();
		return 0;
	}
	
	public int calculateRate(){
		if(sink.getEnergyStored() > heat.getTransfer())
			return heat.getTransfer();
		else if(sink.getEnergyStored() == heat.getTransfer())
			return heat.getTransfer();
		else if(sink.getEnergyStored() < heat.getTransfer())
			return (int) sink.getEnergyStored();
		return 0;
	}
	
	@Override
	public void invalidate(){
		sink.invalidate();
		super.invalidate();
	}

	@Override
	public void onChunkUnload(){
		sink.onChunkUnload();
	}
}