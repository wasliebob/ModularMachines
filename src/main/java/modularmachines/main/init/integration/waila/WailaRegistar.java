/**
* This file is created by wasliebob.
* Created on: 7 aug. 2014, at 09:58:17
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.main.init.integration.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import modularmachines.api.heat.interfaces.IHeatGenerator;
import modularmachines.api.heat.interfaces.IHeatTransport;
import modularmachines.api.heat.interfaces.IHeatedMachine;
import modularmachines.api.misc.interfaces.IPotionStorage;
import modularmachines.api.misc.interfaces.IPotionTransport;
import modularmachines.blocks.MMBlockCore;
import modularmachines.blocks.MMBlockInteracting;
import modularmachines.blocks.MMBlockRouter;

public class WailaRegistar {
    public static void callbackRegister(IWailaRegistrar r){
        r.registerBodyProvider(new IPotionStorageDataProvider(), IPotionStorage.class);
        r.registerBodyProvider(new IPotionTransportDataProvider(), IPotionTransport.class);
        r.registerBodyProvider(new IHeatTransportDataProvider(), IHeatTransport.class);
        r.registerBodyProvider(new IHeatedMachineDataProvider(), IHeatedMachine.class);
        r.registerBodyProvider(new IHeatGeneratorDataProvider(), IHeatGenerator.class);
        r.registerBodyProvider(new InteractingCoreDataProvider(), MMBlockInteracting.class);
        r.registerBodyProvider(new MachineCoreDataProvider(), MMBlockCore.class);
        r.registerBodyProvider(new RouterDataProvider(), MMBlockRouter.class);
    }
}