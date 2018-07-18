package gzz_lin.dna.block;

import gzz_lin.dna.DNACraft;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBioEnergyGenerator extends BlockContainer{

	protected BlockBioEnergyGenerator() {
		super(Material.IRON);
		this.setCreativeTab(DNACraft.TAB);
		this.setHardness(5.0f);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return null;
	}

}
