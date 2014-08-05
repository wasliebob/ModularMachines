/**
* This file is created by wasliebob.
* Created on: 4 aug. 2014, at 09:34:01
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks.tiles;

import java.util.ArrayList;
import java.util.HashMap;

import modularmachines.api.misc.PotionStorage;
import modularmachines.api.misc.helpers.DirectionHelper;
import modularmachines.api.misc.interfaces.IPotionStorage;
import modularmachines.api.misc.interfaces.IPotionTransport;
import modularmachines.api.misc.interfaces.IScanable;
import modularmachines.helpers.PotionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.misc.infopackets.Coordinates;

public class TilePotionTube extends TileEntity implements IPotionTransport, IScanable{
	public TilePotionTube(){
		tank = new PotionStorage(PotionHelper.potion_volume, PotionHelper.potion_volume);
	}	
	public PotionStorage tank;
	public HashMap<ForgeDirection, Coordinates> coords = new HashMap<ForgeDirection, Coordinates>();
	
	@Override
	public void updateEntity(){
		setupCoordMap();

		send();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(this.tank.getPotion() != null)
			nbt.setInteger("POTION", this.tank.getPotion().id);
		nbt.setInteger("AMOUNT", this.tank.getAmount());
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt){
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
	
	public void setupCoordMap(){
		coords.clear();
		TileEntity top = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
		TileEntity bottom = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
		
		TileEntity right = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
		TileEntity left = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);

		TileEntity front = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
		TileEntity back = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);

		if(top != null && ((top instanceof IPotionStorage && ((IPotionStorage)top).canConnect(DirectionHelper.getOpposite(ForgeDirection.UP))) || top instanceof IPotionTransport))
			coords.put(ForgeDirection.UP, new Coordinates(top.xCoord, top.yCoord, top.zCoord));
		
		if(bottom != null && ((bottom instanceof IPotionStorage && ((IPotionStorage)bottom).canConnect(DirectionHelper.getOpposite(ForgeDirection.DOWN))) || bottom instanceof IPotionTransport))
			coords.put(ForgeDirection.DOWN, new Coordinates(bottom.xCoord, bottom.yCoord, bottom.zCoord));
	
		if(right != null && ((right instanceof IPotionStorage && ((IPotionStorage)right).canConnect(DirectionHelper.getOpposite(ForgeDirection.EAST))) || right instanceof IPotionTransport))
			coords.put(ForgeDirection.EAST, new Coordinates(right.xCoord, right.yCoord, right.zCoord));
		
		if(left != null && ((left instanceof IPotionStorage && ((IPotionStorage)left).canConnect(DirectionHelper.getOpposite(ForgeDirection.WEST))) || left instanceof IPotionTransport))
			coords.put(ForgeDirection.WEST, new Coordinates(left.xCoord, left.yCoord, left.zCoord));
		
		if(front != null && ((front instanceof IPotionStorage && ((IPotionStorage)front).canConnect(DirectionHelper.getOpposite(ForgeDirection.SOUTH))) || front instanceof IPotionTransport))
			coords.put(ForgeDirection.SOUTH, new Coordinates(front.xCoord, front.yCoord, front.zCoord));
		
		if(back != null && ((back instanceof IPotionStorage && ((IPotionStorage)back).canConnect(DirectionHelper.getOpposite(ForgeDirection.NORTH))) || back instanceof IPotionTransport))
			coords.put(ForgeDirection.NORTH, new Coordinates(back.xCoord, back.yCoord, back.zCoord));
	}
	
	public HashMap<ForgeDirection, Coordinates> getCoords(){
		return coords;
	}

	@Override
	public PotionStorage getPotionStorage() {
		return tank;
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
	public void send() {
		for(TileEntity t : getOutput()){
			if(t instanceof IPotionStorage){
				IPotionStorage te = (IPotionStorage)t;
				send(te);
			}else if(t instanceof IPotionTransport){
				IPotionTransport te = (IPotionTransport)t;
				send(te);
			}
		}
	}
	
	public void send(IPotionTransport te){
		PotionStorage storage = te.getPotionStorage();
		if(storage.potion == null){
			storage.setPotion(tank.potion);
			storage.increaseAmount(getSending());
			tank.decreaseAmount(getSending());

			if(tank.amount - te.getSending() <= 0)
				tank.potion = null;
				
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			te.markUpdate();
		}else if(storage.potion != null && te.canReceive(tank.potion, tank.amount)){
			storage.increaseAmount(getSending());
			tank.decreaseAmount(getSending());

			if(tank.amount - te.getSending() <= 0)
				tank.potion = null;
			
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			te.markUpdate();
		}
	}
	
	public void send(IPotionStorage te){
		PotionStorage storage = te.getPotionStorage();
		if(storage.potion == null){
			storage.setPotion(tank.potion);
			storage.increaseAmount(getSending());
			tank.decreaseAmount(getSending());

			if(tank.amount - te.getSending() <= 0)
				tank.potion = null;
				
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			te.markUpdate();
		}else if(storage.potion != null && te.canReceive(tank.potion, tank.amount)){
			storage.increaseAmount(getSending());
			tank.decreaseAmount(getSending());

			if(tank.amount - te.getSending() <= 0)
				tank.potion = null;
			
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			te.markUpdate();
		}
	}
	
	@Override
	public ArrayList<TileEntity> getOutput(){
		ArrayList<TileEntity> tiles = new ArrayList<TileEntity>();
		
		for(ForgeDirection dir : coords.keySet()){
			Coordinates c = coords.get(dir);
			TileEntity te = worldObj.getTileEntity(c.x, c.y, c.z);
			if(te != null){
				if(te instanceof IPotionStorage && ((IPotionStorage)te).inputSide() != null && dir == DirectionHelper.getOpposite(((IPotionStorage)te).inputSide())){
					tiles.add(te);
				}else if(te instanceof IPotionTransport){
					tiles.add(te);
				}
			}
		}
		return tiles;
	}

	@Override
	public NBTTagCompound getInfo(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("name", "Potion Tube");
		if(tank.getPotion() != null)
			tag.setString("potion", StatCollector.translateToLocal(tank.getPotion().getName()) + ": " + tank.getAmount() + "/" + tank.capacity);
		return tag;
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
	public void markUpdate() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}