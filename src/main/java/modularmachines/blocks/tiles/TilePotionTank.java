package modularmachines.blocks.tiles;

import modularmachines.api.misc.IPotionStorage;
import modularmachines.api.misc.ITransportable;
import modularmachines.api.misc.PotionStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;

public class TilePotionTank extends TileEntity implements IPotionStorage, ITransportable{
	public TilePotionTank(){
		tank = new PotionStorage(1000, 10);
	}
	public PotionStorage tank;
	
	@Override
	public PotionStorage getPotionStorage() {
		return tank;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(this.tank != null && this.tank.getPotion() != null)
			nbt.setInteger("POTION", this.tank.getPotion().id);
		nbt.setInteger("AMOUNT", this.tank.getAmount());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.tank.setPotion(Potion.potionTypes[nbt.getInteger("POTION")]);
		this.tank.setAmount(nbt.getInteger("AMOUNT"));
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
	public boolean hasTag() {
		return true;
	}

	@Override
	public NBTTagCompound getTag() {
		NBTTagCompound tag = new NBTTagCompound();
		
		if(this.tank != null && this.tank.getPotion() != null)
			tag.setInteger("POTION", this.tank.getPotion().id);
		tag.setInteger("AMOUNT", this.tank.getAmount());
		
		return tag;
	}

	@Override
	public void readNBT(NBTTagCompound tag) {
		this.tank.setPotion(Potion.potionTypes[tag.getInteger("POTION")]);
		this.tank.setAmount(tag.getInteger("AMOUNT"));
	}
}