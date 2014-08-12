/**
* This file is created by wasliebob.
* Created on: 12 aug. 2014, at 18:53:17
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.world.structures.handlers;

import java.util.List;
import java.util.Random;

import modularmachines.world.structures.components.ComponentVillageEngineer;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class EngineerCreationHandler implements VillagerRegistry.IVillageCreationHandler{

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new StructureVillagePieces.PieceWeight(ComponentVillageEngineer.class, 4, 1);
	}

	@Override
	public Class<?> getComponentClass() {
		return ComponentVillageEngineer.class;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object buildComponent(PieceWeight villagePiece, Start startPiece,
			List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
		return ComponentVillageEngineer.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}

}