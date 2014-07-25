package modularmachines.blocks.tiles;

import java.awt.Color;

import modularmachines.api.misc.PotionStorage;
import modularmachines.api.misc.interfaces.IColorable;
import modularmachines.api.misc.interfaces.IPotionStorage;
import modularmachines.api.misc.interfaces.ITransportable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;

public class TilePotionTank extends TileEntity implements IPotionStorage, ITransportable, IColorable{
	public TilePotionTank(){
		tank = new PotionStorage(1000, 10);
		this.color = new Color(255, 255, 255).getRGB();
	}
	public PotionStorage tank;
	public int color;
	
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
		
		nbt.setInteger("COLOR", this.getColor().getRGB());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.tank.setPotion(Potion.potionTypes[nbt.getInteger("POTION")]);
		this.tank.setAmount(nbt.getInteger("AMOUNT"));
		this.setColor(new Color(nbt.getInteger("COLOR")));
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
		
		tag.setInteger("COLOR", this.getColor().getRGB());

		return tag;
	}

	@Override
	public void readNBT(NBTTagCompound tag) {
		this.tank.setPotion(Potion.potionTypes[tag.getInteger("POTION")]);
		this.tank.setAmount(tag.getInteger("AMOUNT"));
		this.setColor(new Color(tag.getInteger("COLOR")));
	}

	@Override
	public Color getColor() {
		return new Color(this.color);
	}

	@Override
	public void setColor(Color color) {
		this.color = color.getRGB();
	}
}