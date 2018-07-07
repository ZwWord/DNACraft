package gzz_lin.dna.item;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.entity.EntityBioMicrocapsule;
import gzz_lin.dna.util.DNASoundEvents;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * 微型生物胶囊
 * 
 * @author Gzz_lin
 *
 */
public class ItemBioMicrocapsuleNutrient extends Item {

	public ItemBioMicrocapsuleNutrient() {
		this.setHasSubtypes(true);
		this.setMaxDamage(0);

	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		if (meta < 3)
			return "item.BioMicrocapsuleNutrient." + BMMaterial.values()[meta];
		return null;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab == DNACraft.TAB) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		if (!playerIn.isCreative())
        {
			itemStack.shrink(1);
        }
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, DNASoundEvents.BIO_MICROCAPSULE_THROW, SoundCategory.PLAYERS, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isRemote) {
			if (itemStack.getMetadata() < 3) {
				EntityBioMicrocapsule bioMicrocapsule = new EntityBioMicrocapsule(worldIn, playerIn, itemStack.copy());
				bioMicrocapsule.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0f, 1.5f, 1.5f);
				worldIn.spawnEntity(bioMicrocapsule);
			}
			
		}
		playerIn.addStat(StatList.getObjectUseStats(this));
		ActionResult<ItemStack> result = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
		return result;
	}

}
