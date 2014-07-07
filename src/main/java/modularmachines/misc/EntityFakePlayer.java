package modularmachines.misc;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityFakePlayer extends EntityPlayer{
	public EntityFakePlayer(World world, GameProfile profile) {
		super(world, profile);
	}

	@Override
	public void addChatMessage(IChatComponent var1) {}

	@Override
	public boolean canCommandSenderUseCommand(int var1, String var2) {
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return new ChunkCoordinates(0, 0, 0);
	}
}