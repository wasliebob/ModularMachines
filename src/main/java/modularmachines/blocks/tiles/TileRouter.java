package modularmachines.blocks.tiles;

import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.interfaces.IHeatTransport;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.helpers.DirectionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileRouter extends TileEntity implements IHeatTransport{
	public TileRouter(){
		heat = new HeatStorage(1200, 10);
		range = 10;
		input = null;
		output = null;
	}
	public HeatStorage heat;
	public ForgeDirection input;
	public ForgeDirection output;
	public int range;
	
	@Override
	public void updateEntity(){
		if(getBoundMachine() != null){
			IHeatedMachine machine = getBoundMachine();
			if(machine.canAdd(this.calculateSending(machine))){
				machine.getHeatStorage().increaseHeat(this.calculateSending(machine));
				heat.decreaseEnergy(this.calculateSending(machine));
			}
		}
		
		if(getBound() != null){
			TileRouter bound = getBound();
			if(bound.canAdd(this.calculateSending(bound))){
				bound.getHeatStorage().increaseHeat(this.calculateSending(bound));
				heat.decreaseEnergy(this.calculateSending(bound));
			}
		}
	}
	
	public TileRouter getBound(){
		if(this.output != null){
			TileEntity te = DirectionHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, output, range);
			if(te != null && te instanceof TileRouter){
				TileRouter router = (TileRouter)te;
				if(router.input == DirectionHelper.getOpposite(this.output)){
					return router;
				}
			}
		}
		return null;
	}
	
	public IHeatedMachine getBoundMachine(){
		if(this.output != null){
			TileEntity te = DirectionHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, output, range);
			if(te != null && te instanceof IHeatedMachine){
				IHeatedMachine machine = (IHeatedMachine)te;
				if(machine.getInput() == DirectionHelper.getOpposite(this.output)){
					return machine;
				}
			}
		}
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("HEAT", this.heat.getHeat());
		if(this.input != null)
			nbt.setInteger("INPUT", this.input.ordinal());
		
		if(this.output != null)
			nbt.setInteger("OUTPUT", this.output.ordinal());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.heat.setHeat(nbt.getInteger("HEAT"));
		
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

	@Override
	public HeatStorage getHeatStorage() {
		return this.heat;
	}

	@Override
	public boolean canAdd(int energy) {
		return heat.getHeat() + energy <= heat.getMaxHeat();
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
}