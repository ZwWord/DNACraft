package gzz_lin.dna.block;

import gzz_lin.dna.DNACraft;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSulphurSand extends BlockFalling {

	public BlockSulphurSand() {
		super(Material.SAND);
		this.setUnlocalizedName("SulphurSand");
		this.setCreativeTab(DNACraft.TAB);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.YELLOW;
	}
	
}
