//package modularmachines.main.init.integration.waila;
//
//import mcp.mobius.waila.api.IWailaRegistrar;
//import modularmachines.api.heat.interfaces.IHeatTransport;
//import modularmachines.api.heat.interfaces.IHeatedMachine;
//import modularmachines.api.misc.IPotionStorage;
//import modularmachines.blocks.MMBlockCore;
//import modularmachines.blocks.MMBlockInteracting;
//import modularmachines.blocks.MMBlockRouter;
//
//public class WailaRegistar {
//    public static void callbackRegister(IWailaRegistrar r){
//        r.registerBodyProvider(new IPotionStorageDataProvider(), IPotionStorage.class);
//        r.registerBodyProvider(new IHeatTransportDataProvider(), IHeatTransport.class);
//        r.registerBodyProvider(new IHeatedMachineDataProvider(), IHeatedMachine.class);
//        r.registerBodyProvider(new InteractingCoreDataProvider(), MMBlockInteracting.class);
//        r.registerBodyProvider(new MachineCoreDataProvider(), MMBlockCore.class);
//        r.registerBodyProvider(new RouterDataProvider(), MMBlockRouter.class);
//    }
//}