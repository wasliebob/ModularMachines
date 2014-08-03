/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket {
	  public abstract void encodeInto(ChannelHandlerContext chc, ByteBuf bb);
	  public abstract void decodeInto(ChannelHandlerContext chc, ByteBuf bb);
	  public abstract void handleClientSide(EntityPlayer player);
	  public abstract void handleServerSide(EntityPlayer player);
}