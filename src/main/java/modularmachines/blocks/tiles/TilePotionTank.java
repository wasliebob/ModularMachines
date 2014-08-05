/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.tiles;

import java.awt.Color;

import modularmachines.api.misc.PotionStorage;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.interfaces.IColorable;
import modularmachines.api.misc.interfaces.IPotionStorage;
import modularmachines.api.misc.interfaces.IPotionTransport;
import modularmachines.api.misc.interfaces.IScanable;
import modularmachines.api.misc.interfaces.ITransportable;
import modularmachines.helpers.PotionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePotionTank extends TileEntity implements IPotionStorage, ITransportable, IColorable, IScanable{
	public TilePotionTank(){
		tank = new PotionStorage(PotionHelper.potion_volume*10, PotionHelper.potion_volume);
		this.color = new Color(255, 255, 255).getRGB();
		input = null;
		output = null;
	}
	public PotionStorage tank;
	public int color;
	public ForgeDirection input;
	public ForgeDirection output;
	
	@Override
	public void updateEntity(){
		if(canSend())
			send();
	}
	
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
		
		if(this.input != null)
			nbt.setInteger("INPUT", this.input.ordinal());
		
		if(this.output != null)
			nbt.setInteger("OUTPUT", this.output.ordinal());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.tank.setPotion(Potion.potionTypes[nbt.getInteger("POTION")]);
		this.tank.setAmount(nbt.getInteger("AMOUNT"));
		this.setColor(new Color(nbt.getInteger("COLOR")));
		
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
	
	@Override
	public NBTTagCompound getInfo(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("name", "Potion Tank");
		if(tank.getPotion() != null)
			tag.setString("potion", StatCollector.translateToLocal(tank.getPotion().getName()) + ": " + tank.getAmount() + "/" + tank.capacity);
		return tag;
	}

	@Override
	public boolean canSend() {
		if(tank.potion != null && tank.amount > 0 && outputSide() != null){
			TileEntity te = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, outputSide());
			if(te != null && te instanceof IPotionTransport && ((IPotionTransport)te).canReceive(tank.potion, getSending()))
				return true;
		}
		return false;
	}

	@Override
	public void send(){
		if(outputSide() != null){
			TileEntity te = DirectionHelper.getTileEntity(worldObj, xCoord, yCoord, zCoord, outputSide());
			if(te instanceof IPotionTransport){
				IPotionTransport pt = (IPotionTransport)te;
				PotionStorage storage = pt.getPotionStorage();
				if(storage.potion == null){
					storage.setPotion(tank.potion);
					storage.increaseAmount(getSending());
					tank.decreaseAmount(getSending());
					
					if(tank.amount - pt.getSending() <= 0)
						tank.potion = null;
					
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					worldObj.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
				}else if(storage.potion != null){
					storage.increaseAmount(getSending());
					tank.decreaseAmount(getSending());
					
					if(tank.amount - pt.getSending() <= 0)
						tank.potion = null;
					
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					worldObj.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
				}
			}
		}
	}
	
	@Override
	public int getSending(){
		if(tank.amount >= tank.transfer)
			return tank.transfer;
		else if(tank.amount < tank.transfer)
			return tank.amount;
		return 0;
	}
	
	@Override
	public ForgeDirection inputSide() {
		return this.input;
	}

	@Override
	public ForgeDirection outputSide() {
		return this.output;
	}
	
	@Override
	public boolean canReceive(Potion potion, int amount){
		if(tank.potion == null)
			return true;
		else if(potion == tank.potion && amount + tank.amount <= tank.capacity)
			return true;
		return false;
	}
	
	@Override
	public void markUpdate() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean canConnect(ForgeDirection dir) {
		return dir == this.input || dir == this.output;
	}
}