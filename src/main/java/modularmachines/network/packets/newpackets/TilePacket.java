/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.network.packets.newpackets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public abstract class TilePacket<T extends TileEntity> implements IMessage{
	public TilePacket(){
		
	}
	
	public TilePacket(T tile){
		this.tile = tile;
		
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.dim = tile.getWorldObj().provider.dimensionId;
	}
	protected int x;
	protected int y;
	protected int z;
	protected int dim;
	
	protected EntityPlayer player;
	protected T tile;
	
	@Override
    public void fromBytes(ByteBuf buf){
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		dim = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf){
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(dim);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IMessage onMessage(TilePacket tp, MessageContext con){
		MinecraftServer server = MinecraftServer.getServer();
		if (con.side.isClient())
			tp.player = Minecraft.getMinecraft().thePlayer;
		else
			tp.player = con.getServerHandler().playerEntity;
		
		if(server != null){
			World world = server.worldServerForDimension(tp.dim);
			
			if(world == null)
				return null;
			
			TileEntity tile = world.getTileEntity(tp.x, tp.y, tp.z);
			if(tile != null){
				tp.tile = (T) tile;
			}
		}
		return null;
	}
}