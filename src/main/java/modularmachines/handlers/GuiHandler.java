/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.handlers;

import modularmachines.api.classes.TileGenerator;
import modularmachines.api.classes.TileInteracting;
import modularmachines.api.classes.TileMachineBase;
import modularmachines.api.classes.guis.GuiEntry;
import modularmachines.api.classes.guis.GuiGuide;
import modularmachines.api.guide.IGuided;
import modularmachines.api.main.MMInteractingUpgrades;
import modularmachines.api.misc.interfaces.IScanable;
import modularmachines.blocks.containers.ContainerFilter;
import modularmachines.blocks.containers.ContainerGenerator;
import modularmachines.blocks.containers.ContainerMachineBase;
import modularmachines.blocks.guis.GuiFilter;
import modularmachines.blocks.guis.GuiGenerator;
import modularmachines.blocks.guis.GuiInformation;
import modularmachines.blocks.guis.GuiMachineBase;
import modularmachines.blocks.guis.GuiScanner;
import net.minecraft.block.Block;
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
                case 4: return new ContainerFilter((TileInteracting)tile, player.inventory);
                case 5: return new ContainerGenerator((TileGenerator)tile, player.inventory);
                case 6: return null;
                case 7: return null;
                case 8: return null;
                default: return false;
                }
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
                TileEntity tile = world.getTileEntity(x, y, z);
                Block block = world.getBlock(x, y, z);
                switch(id){
                case 1: return new GuiMachineBase((TileMachineBase)tile, player.inventory);
                case 2: return new GuiInformation((TileInteracting)tile);
                case 3: return new GuiGuide(player, 0);
                case 4: return new GuiFilter((TileInteracting)tile, player.inventory);
                case 5: return new GuiGenerator((TileGenerator)tile, player.inventory);
                case 6:                 
                if(block instanceof IGuided)
            		return new GuiEntry(((IGuided)block).getKey(), player, 0); 
            	else 
            		return null;
                case 7:
                	if(MMInteractingUpgrades.getUpgrade(((TileInteracting)tile).upgrade).action instanceof IGuided)
                		return new GuiEntry(((IGuided)MMInteractingUpgrades.getUpgrade(((TileInteracting)tile).upgrade).action).getKey(), player, 0); 
                	else 
                		return null;
                case 8: 
                	if(tile instanceof IScanable) 
                		return new GuiScanner((IScanable)tile); 
                	else
                		return null;
                default: return false;
                }

        }
}