package modularmachines.network.packets.newpackets;

import io.netty.buffer.ByteBuf;
import modularmachines.api.classes.TileInteracting;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleInteracting extends TilePacket<TileInteracting> implements IMessageHandler<PacketToggleInteracting, IMessage>{
	public PacketToggleInteracting(){
		super();
	}
	
	public PacketToggleInteracting(TileInteracting te){
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
	public IMessage onMessage(PacketToggleInteracting message, MessageContext ctx){
		super.onMessage(message, ctx);
		if (!ctx.side.isServer())
			return null;
		
		message.tile.enabled = message.enabled;
		return null;
	}
}