package modularmachines.network.packets.newpackets;

import io.netty.buffer.ByteBuf;
import modularmachines.api.classes.TileInteracting;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTransferRate extends TilePacket<TileInteracting> implements IMessageHandler<PacketTransferRate, IMessage>{
	public PacketTransferRate(){
		super();
	}
	
	public PacketTransferRate(TileInteracting te, int type){
		super(te);
		this.transfer = te.heat.getTransfer() + calcTransfer(type);
	}
	int transfer;
	
	@Override
	public void toBytes(ByteBuf bf) {
		super.toBytes(bf);
		bf.writeInt(transfer);
	}

	@Override
	public void fromBytes(ByteBuf bf) {
		super.fromBytes(bf);
		transfer = bf.readInt();
	}
	
	@Override
	public IMessage onMessage(PacketTransferRate message, MessageContext ctx){
		super.onMessage(message, ctx);
		if (!ctx.side.isServer())
			return null;
		
		message.tile.heat.setHeat(message.transfer);
		return null;
	}
	
	public int calcTransfer(int type){
		if(type == 0){
			if(tile.heat.getTransfer() < 200)
				return tile.heat.getTransfer() + 10;
		}else if(type == 1){
			if(tile.heat.getTransfer() > 0)
				return tile.heat.getTransfer() - 10;
		}
		return tile.heat.getTransfer();
	}
}