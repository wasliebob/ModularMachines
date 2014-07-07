package modularmachines.network.packets.newpackets;

import io.netty.buffer.ByteBuf;
import modularmachines.api.classes.TileMachineBase;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketToggle extends TilePacket<TileMachineBase> implements IMessageHandler<PacketToggle, IMessage>{
	public PacketToggle(){
		super();
	}
	
	public PacketToggle(TileMachineBase te){
		super(te);
		this.enabled = tile.enabled;
	}
	boolean enabled;
	
	@Override
	public void toBytes(ByteBuf bf) {
		super.toBytes(bf);
		bf.writeBoolean(enabled);
	}

	@Override
	public void fromBytes(ByteBuf bf) {
		super.fromBytes(bf);
		enabled = bf.readBoolean();
	}
	
	@Override
	public IMessage onMessage(PacketToggle message, MessageContext ctx){
		super.onMessage(message, ctx);
		if (!ctx.side.isServer())
			return null;
		
		message.tile.enabled = message.enabled;
		return null;
	}

}