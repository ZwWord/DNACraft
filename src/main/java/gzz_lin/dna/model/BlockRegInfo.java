package gzz_lin.dna.model;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BlockRegInfo {
	public final ResourceLocation REGISTRY_NAME;
	public final boolean REGISTRY_ITME;
	public final String[] ORE_DICTIONARIES;
	public final Block BLOCK;
	public BlockRegInfo(Block block,ResourceLocation registry_name,boolean registry_itme, String[] ore_dictionaries) {
		super();
		this.BLOCK=block;
		this.REGISTRY_NAME = registry_name;
		this.REGISTRY_ITME=registry_itme;
		this.ORE_DICTIONARIES = ore_dictionaries;
	}

	
}
