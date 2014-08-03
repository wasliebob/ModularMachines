/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class MMPacket implements IMessage {
	public abstract void handleClient(EntityPlayer player);
	
	public abstract void handleServer(EntityPlayer player);
}