/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init;

import modularmachines.blocks.MMBlockBrick;
import modularmachines.blocks.MMBlockCore;
import modularmachines.blocks.MMBlockGenerator;
import modularmachines.blocks.MMBlockGlass;
import modularmachines.blocks.MMBlockInteracting;
import modularmachines.blocks.MMBlockOre;
import modularmachines.blocks.MMBlockPotionTank;
import modularmachines.blocks.MMBlockPotionTube;
import modularmachines.blocks.MMBlockRouter;
import modularmachines.blocks.MMBlockSlab;
import modularmachines.blocks.MMBlockStairs;
import modularmachines.blocks.MMBlockTransporter;
import modularmachines.blocks.integration.MMBlockPowerConverterCoFH;
import modularmachines.blocks.integration.MMBlockPowerConverterIC2;
import wasliecore.interfaces.IInitalization;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlocks implements IInitalization{
	@Override
	public void preInit(){
		initBlocks();
		initOres();
	}
	
	@Override
	public void init(){
		initTiles();
	}

	@Override
	public void postInit(){
	}
	
	public void initBlocks(){
		stone_black = new MMBlockBrick("black stone", "stone_black");
		stone_red = new MMBlockBrick("red stone", "stone_red");
		stone_white = new MMBlockBrick("white stone", "stone_white");

		brick_black = new MMBlockBrick("black brick", "brick_black");
		brick_red = new MMBlockBrick("red brick", "brick_red");
		brick_white = new MMBlockBrick("white brick", "brick_white");

		chiseled_brick_black = new MMBlockBrick("chiseled black brick", "brick_black_2");
		chiseled_brick_red = new MMBlockBrick("chiseled red brick", "brick_red_2");
		chiseled_brick_white = new MMBlockBrick("chiseled white brick", "brick_white_2");
		
		stairs_brick_black = new MMBlockStairs(MMBlocks.brick_black, 0);
		stairs_brick_red = new MMBlockStairs(MMBlocks.brick_red, 0);
		stairs_brick_white = new MMBlockStairs(MMBlocks.brick_white, 0);

		slab_brick_black = new MMBlockSlab(MMBlocks.brick_black, 0);
		slab_brick_red = new MMBlockSlab(MMBlocks.brick_red, 0);
		slab_brick_white = new MMBlockSlab(MMBlocks.brick_white, 0);

		glass_default = new MMBlockGlass("glass");
		
		core_machine = new MMBlockCore("core");
		router = new MMBlockRouter("heat router");
		core_interacting = new MMBlockInteracting("interacting machine core");
		tank_potion = new MMBlockPotionTank("potion tank");
		transporter = new MMBlockTransporter("transporter");
		generator = new MMBlockGenerator("generator");
		tube_potion = new MMBlockPotionTube();

		if(Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2")){
			power_converter_ic2 = new MMBlockPowerConverterIC2("ic power converter");}
		
		if(Loader.isModLoaded("CoFHCore")){
			power_converter_cofh = new MMBlockPowerConverterCoFH("rf power converter");}
	}
	public static MMBlockBrick stone_black;
	public static MMBlockBrick stone_red;
	public static MMBlockBrick stone_white;

	public static MMBlockBrick brick_black;
	public static MMBlockBrick brick_red;
	public static MMBlockBrick brick_white;

	public static MMBlockBrick chiseled_brick_black;
	public static MMBlockBrick chiseled_brick_red;
	public static MMBlockBrick chiseled_brick_white;

	public static MMBlockStairs stairs_brick_black;
	public static MMBlockStairs stairs_brick_red;
	public static MMBlockStairs stairs_brick_white;

	public static MMBlockSlab slab_brick_black;
	public static MMBlockSlab slab_brick_red;
	public static MMBlockSlab slab_brick_white;

	public static MMBlockGlass glass_default;

	public static MMBlockCore core_machine;
	public static MMBlockRouter router;
	public static MMBlockInteracting core_interacting;
	public static MMBlockPotionTank tank_potion;
	public static MMBlockTransporter transporter;
	public static MMBlockGenerator generator;
	public static MMBlockPotionTube tube_potion;
	
	public static MMBlockPowerConverterIC2 power_converter_ic2;
	public static MMBlockPowerConverterCoFH power_converter_cofh;

	public void initTiles(){
		GameRegistry.registerTileEntity(modularmachines.api.classes.TileMachineBase.class, "mm_base");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TileRouter.class, "mm_fluid_heater");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TilePotionTank.class, "mm_tank_potion");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TileTransporter.class, "mm_transporter");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TilePotionTube.class, "mm_tube_potion");

		GameRegistry.registerTileEntity(modularmachines.api.classes.TileGenerator.class, "mm_generator");
		GameRegistry.registerTileEntity(modularmachines.api.classes.TileInteracting.class, "mm_interacting");

		if(Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2")){
			GameRegistry.registerTileEntity(modularmachines.blocks.tiles.intergration.TilePowerConverterIC2.class, "mm_power_converter_ic2");}
		if(Loader.isModLoaded("CoFHCore")){
			GameRegistry.registerTileEntity(modularmachines.blocks.tiles.intergration.TilePowerConverterCoFH.class, "mm_power_converter_cofh");}
	}
	
	public void initOres(){
		ore_copper = new MMBlockOre("Copper");
		ore_tin = new MMBlockOre("Tin");
	}
	public static MMBlockOre ore_copper;
	public static MMBlockOre ore_tin;
}