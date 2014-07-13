package modularmachines.main.init;

import modularmachines.blocks.MMBlockCore;
import modularmachines.blocks.MMBlockFluidHeater;
import modularmachines.blocks.MMBlockInteracting;
import modularmachines.blocks.MMBlockPotionTank;
import modularmachines.blocks.MMBlockRouter;
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
	}
	
	@Override
	public void init(){
		initTiles();
	}

	@Override
	public void postInit(){
	}
	
	public void initBlocks(){
		core_machine = new MMBlockCore("core");
		heater_fluid = new MMBlockFluidHeater("fluid heater");
		router = new MMBlockRouter("heat router");
		core_interacting = new MMBlockInteracting("interacting machine core");
		tank_potion = new MMBlockPotionTank("potion tank");
		transporter = new MMBlockTransporter("transporter");
		if(Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2")){
			power_converter_ic2 = new MMBlockPowerConverterIC2("ic power converter");}
		
		if(Loader.isModLoaded("CoFHCore")){
			power_converter_cofh = new MMBlockPowerConverterCoFH("rf power converter");}
	}
	public static MMBlockCore core_machine;
	public static MMBlockFluidHeater heater_fluid;
	public static MMBlockRouter router;
	public static MMBlockInteracting core_interacting;
	public static MMBlockPotionTank tank_potion;
	public static MMBlockTransporter transporter;
	
	public static MMBlockPowerConverterIC2 power_converter_ic2;
	public static MMBlockPowerConverterCoFH power_converter_cofh;

	public void initTiles(){
		GameRegistry.registerTileEntity(modularmachines.api.classes.TileMachineBase.class, "mm_base");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TileFluidHeater.class, "mm_router");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TileRouter.class, "mm_fluid_heater");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TilePotionTank.class, "mm_tank_potion");
		GameRegistry.registerTileEntity(modularmachines.blocks.tiles.TileTransporter.class, "mm_transporter");

		GameRegistry.registerTileEntity(modularmachines.api.classes.TileInteracting.class, "mm_interacting");

		if(Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2")){
			GameRegistry.registerTileEntity(modularmachines.blocks.tiles.intergration.TilePowerConverterIC2.class, "mm_power_converter_ic2");}
		if(Loader.isModLoaded("CoFHCore")){
			GameRegistry.registerTileEntity(modularmachines.blocks.tiles.intergration.TilePowerConverterCoFH.class, "mm_power_converter_cofh");}
	}
}