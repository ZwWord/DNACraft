package gzz_lin.dna.util;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.TerrainGen;

public class OreGenUtil {
	public static void generate(World worldIn, Random rand, BlockPos position, IBlockState blockState, int blockCount,
			int times, int minY, int maxY, Biome... biomes) {
		WorldGenerator gen = getWorldGenerator(blockState, blockCount);
		if (TerrainGen.generateOre(worldIn, rand, gen, position, GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < times; i++) {
				int x = position.getX() + rand.nextInt(16);
				int z = position.getZ() + rand.nextInt(16);
				int y = minY + rand.nextInt(maxY - minY);
				BlockPos pos = new BlockPos(x, y, z);
				ArrayList<Biome> biomeList = new ArrayList<>();
				if (biomes != null) {
					for (Biome b : biomes) {
						biomeList.add(b);
					}
				}
				if (biomes == null || biomeList.indexOf(worldIn.getBiome(pos)) >= 0) {
					System.out.println(pos);
					boolean flag=gen.generate(worldIn, rand, pos);
					System.out.println(flag);
				}
			}
		}
	}

	private static WorldGenMinable getWorldGenerator(IBlockState state, int blockCount) {
		WorldGenMinable worldgen = new WorldGenMinable(state, blockCount);
		return worldgen;
	}
}
