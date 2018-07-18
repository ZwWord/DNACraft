package gzz_lin.dna.tileentity;

import gzz_lin.dna.DNACraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
 public static void registryAll() {
	 GameRegistry.registerTileEntity(TileEntityThermalPower.class, new ResourceLocation(DNACraft.MODID+":"+"power"));
 }
}
