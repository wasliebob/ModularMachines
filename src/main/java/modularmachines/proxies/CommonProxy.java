package modularmachines.proxies;

import modularmachines.main.init.MMInit;
import modularmachines.network.packets.newpackets.PacketColorTank;
import modularmachines.network.packets.newpackets.PacketToggle;
import modularmachines.network.packets.newpackets.PacketToggleInteracting;
import modularmachines.network.packets.newpackets.PacketTransferRate;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy{
	public void load(){
		registerPackets();
        registerParticles();
        render();
        bindKeys();
	}
	
	public void registerPackets(){
		MMInit.network.registerMessage(PacketToggle.class, PacketToggle.class, 201, Side.SERVER);
		MMInit.network.registerMessage(PacketToggleInteracting.class, PacketToggleInteracting.class, 202, Side.SERVER);
		MMInit.network.registerMessage(PacketTransferRate.class, PacketTransferRate.class, 203, Side.SERVER);
		MMInit.network.registerMessage(PacketColorTank.class, PacketColorTank.class, 204, Side.SERVER);
	}
	
    public void registerParticles(){}
	public void render(){}
	public void spawnParticle(World world, String particle, int minX, int minY, int minZ, int maxX, int maxY, int maxZ){}
	public void spawnSound(World worldObj, int xCoord, int yCoord, int zCoord, String id){}
	public void bindKeys(){}
	
	public boolean renderView(){
		return false;
	}
}