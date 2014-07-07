package modularmachines.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class MMPacket implements IMessage {
	public abstract void handleClient(EntityPlayer player);
	
	public abstract void handleServer(EntityPlayer player);
}