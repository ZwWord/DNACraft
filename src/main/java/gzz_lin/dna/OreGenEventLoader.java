package gzz_lin.dna;

import gzz_lin.dna.block.DNABlocks;
import gzz_lin.dna.util.OreGenUtil;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenEventLoader {
	private static BlockPos pos;

	@SubscribeEvent
	public void onOreGenPost(OreGenEvent.Post event) {
		if (pos != event.getPos()) {
			pos = event.getPos();
			OreGenUtil.generate(event.getWorld(), event.getRand(), pos, DNABlocks.SULPHUR_SAND.getDefaultState(), 16, 4, 50, 100, null);
			
		}
	}
}
