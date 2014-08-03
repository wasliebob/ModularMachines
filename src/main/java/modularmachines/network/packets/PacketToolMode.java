/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import modularmachines.api.heat.interfaces.IHeatedTool;
import modularmachines.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketToolMode extends AbstractPacket{
	
	public PacketToolMode(){}
	
	@Override
	public void encodeInto(ChannelHandlerContext chc, ByteBuf bb){}

	@Override
	public void decodeInto(ChannelHandlerContext chc, ByteBuf bb){}

	@Override
	public void handleClientSide(EntityPlayer player){}

	@Override
	public void handleServerSide(EntityPlayer player){
		ItemStack held = player.getHeldItem();
		if(held != null){
			held.setItemDamage(getNextMode(held));
		}
	}
	
	public int getNextMode(ItemStack stack){
		int mode = stack.getItemDamage();
		if(mode < ((IHeatedTool)stack.getItem()).getMaxModes())
			return mode + 1;
		else
			return 0;
	}
}