package gzz_lin.dna.block;

import gzz_lin.dna.annotation.RegisterBlock;
import net.minecraft.block.Block;

public class DNABlocks {
	@RegisterBlock(registryName="bio_energy_generator",registryItemBlock=true)
	public static final Block BIO_ENERGY_GENERATOR=new BlockBioEnergyGenerator();
	@RegisterBlock(registryName="sulphur_sand",registryItemBlock=true)
	public static final Block SULPHUR_SAND=new BlockSulphurSand();
	
}
