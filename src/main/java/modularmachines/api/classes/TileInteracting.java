package modularmachines.api.classes;

import java.awt.Color;

import modularmachines.api.heat.HeatStorage;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.main.MMInteractingUpgrades;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileInteracting extends TileEntity implements IHeatedMachine{
	public TileInteracting(){
		heat = new HeatStorage(1200, 10);
		upgrade = null;
		upgradeSide = null;
		input = null;
		output = null;
		color = new Color(0);
		meta = -1;
		programmerMode = 0;
		up = 2;
	}
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
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote && upgrade != null && MMInteractingUpgrades.getUpgrade(upgrade).action.hasRequired(this) && enabled){
			MMInteractingUpgrades.getUpgrade(upgrade).action.onUpdate(this);
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
}