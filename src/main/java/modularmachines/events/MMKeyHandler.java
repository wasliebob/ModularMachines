package modularmachines.events;

import modularmachines.api.heat.interfaces.IHeatedTool;
import modularmachines.main.init.MMInit;
import modularmachines.network.packets.PacketToolMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class MMKeyHandler{
  public KeyBinding a1 = new KeyBinding("Next Mode", 71, "key.categories.misc");
  
  public MMKeyHandler(){
    ClientRegistry.registerKeyBinding(a1);
  }
 
  @SubscribeEvent
  public void onKeyInput(KeyInputEvent e){
	  if(a1.getIsKeyPressed()){
		  if(FMLClientHandler.instance().getClient().inGameHasFocus){
			  EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			  if(player != null && player.worldObj.isRemote && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IHeatedTool){
				  nextMode(player.getHeldItem());
				  MMInit.packetPipeline.sendToServer(new PacketToolMode());			
			  }
		  }
	  }
  }
  
  public String nextName(ItemStack stack){
	  int mode = stack.getItemDamage();
	  IHeatedTool tool = (IHeatedTool)stack.getItem();
	  if(mode < tool.getMaxModes())
		  return tool.getModes().get(mode + 1);
	  else if(mode == tool.getMaxModes())
		  return tool.getModes().get(0);
	  
	  return null;
  }
  
  public void nextMode(ItemStack stack){
		int mode = stack.getItemDamage();
		if(mode < ((IHeatedTool)stack.getItem()).getMaxModes())
			stack.setItemDamage(mode);
	}
}