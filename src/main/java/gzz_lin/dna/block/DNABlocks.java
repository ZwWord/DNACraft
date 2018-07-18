package gzz_lin.dna.block;

import gzz_lin.dna.annotation.RegisterBlock;
import net.minecraft.block.Block;

public class DNABlocks {
	//@RegisterBlock(registryName="bio_energy_generator",unlocalizedName="BioEnergyGenerator",registryItemBlock=true)
	//public static final Block BIO_ENERGY_GENERATOR=new BlockBioEnergyGenerator();
	@RegisterBlock(registryName="sulphur_gravel",unlocalizedName="SulphurGravel",registryItemBlock=true)
	public static final Block SULPHUR_GRAVEL=new BlockSulphurGravel();
	@RegisterBlock(registryName="copper_ore",unlocalizedName="CopperOre",registryItemBlock=true)
	public static final Block COPPER_ORE=new BlockCopperOre();
	@RegisterBlock(registryName="copper_block",unlocalizedName="CopperBlock",registryItemBlock=true)
	public static final Block COPPER_BLOCK=new BlockCopperBlock();
	@RegisterBlock(registryName="thermal_power",unlocalizedName="ThermalPower",registryItemBlock=true)
	public static final Block THERMAL_POWER=new BlockThermalPower();
	
}
