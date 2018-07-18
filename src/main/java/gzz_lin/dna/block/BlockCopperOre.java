package gzz_lin.dna.block;

import gzz_lin.dna.DNACraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCopperOre extends Block{

	public BlockCopperOre() {
		super(Material.ROCK);
		this.setHardness(1.5f);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(DNACraft.TAB);
	}

}
