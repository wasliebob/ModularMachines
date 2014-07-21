package modularmachines.network.packets.newpackets;

import io.netty.buffer.ByteBuf;

import java.awt.Color;

import modularmachines.blocks.tiles.TilePotionTank;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketColorTank extends TilePacket<TilePotionTank> implements IMessageHandler<PacketColorTank, IMessage>{
	public PacketColorTank(){
		super();
	}
	
	public PacketColorTank(TilePotionTank te, Color color){
		super(te);
		this.color = color.getRGB();
	}
	int color;
	
	@Override
	public void toBytes(ByteBuf bf) {
		super.toBytes(bf);
		bf.writeInt(color);
	}

	@Override
	public void fromBytes(ByteBuf bf) {
		super.fromBytes(bf);
		color = bf.readInt();
	}
	
	@Override
	public IMessage onMessage(PacketColorTank message, MessageContext ctx){
		super.onMessage(message, ctx);
		if (!ctx.side.isServer())
			return null;
		
		message.tile.setColor(new Color(color));
		return null;
	}
}