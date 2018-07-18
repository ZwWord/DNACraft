package gzz_lin.dna.block;

import java.util.Random;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.item.DNAItems;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSulphurGravel extends BlockFalling {

	public BlockSulphurGravel() {
		super(Material.SAND);
		this.setCreativeTab(DNACraft.TAB);
		this.setSoundType(SoundType.SAND);
		this.setHardness(0.5f);
		this.setHarvestLevel("shovel", 0);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.YELLOW;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.add(new ItemStack(DNAItems.SULPHUR_DUST,1+(int)(Math.random()*3)));
	}

	
	
}
