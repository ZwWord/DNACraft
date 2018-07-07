package gzz_lin.dna.entity;

import gzz_lin.dna.DNACraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityLoader {
	private static int id=0;

	public EntityLoader() {
		EntityRegistry.registerModEntity(new ResourceLocation(DNACraft.MODID+":BioMicrocapsule"), EntityBioMicrocapsule.class, "BioMicrocapsule", id++, DNACraft.instance, 10, 64, true);
	}
	
	

}
