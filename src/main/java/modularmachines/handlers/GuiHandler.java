package modularmachines.handlers;

import modularmachines.api.classes.TileInteracting;
import modularmachines.api.classes.TileMachineBase;
import modularmachines.blocks.containers.ContainerMachineBase;
import modularmachines.blocks.guis.GuiInformation;
import modularmachines.blocks.guis.GuiMachineBase;
import modularmachines.blocks.guis.guide.GuiGuide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
                TileEntity tile = world.getTileEntity(x, y, z);
                switch(id){
                case 1: return new ContainerMachineBase((TileMachineBase)tile, player.inventory);
                case 2: return null;
                case 3: return null;
                default: return false;
                }
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
                TileEntity tile = world.getTileEntity(x, y, z);
                switch(id){
                case 1: return new GuiMachineBase((TileMachineBase)tile, player.inventory);
                case 2: return new GuiInformation((TileInteracting)tile);
                case 3: return new GuiGuide(player, 0);
                default: return false;
                }

        }
}