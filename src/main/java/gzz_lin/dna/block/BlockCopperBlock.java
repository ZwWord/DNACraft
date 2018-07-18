package gzz_lin.dna.block;

import gzz_lin.dna.DNACraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCopperBlock extends Block{

	public BlockCopperBlock() {
		super(Material.ROCK);
		this.setHardness(5.0f);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(DNACraft.TAB);
	}

}
