package gzz_lin.dna.item;

import gzz_lin.dna.util.BMUtil;
import gzz_lin.dna.util.DNASoundEvents;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * 微型生物胶囊
 * 
 * @author Gzz_lin
 *
 */
public class ItemBioMicrocapsuleCapture extends Item {

	public ItemBioMicrocapsuleCapture() {
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		if (meta < 3)
			return "item.BioMicrocapsuleCapture." + BMMaterial.values()[meta];
		return null;
	}

	


	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStack=playerIn.getHeldItem(handIn);
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, DNASoundEvents.BIO_MICROCAPSULE_OPEN, SoundCategory.PLAYERS, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!worldIn.isRemote) {
			EntityLiving entity=BMUtil.getEntity(itemStack, worldIn);
			entity.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
			playerIn.setHeldItem(handIn, new ItemStack(DNAItems.BIO_MICROCAPSULE_NULL,1,itemStack.getMetadata()));
			worldIn.spawnEntity(entity);
		}
		ActionResult<ItemStack> result = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
		return result;
	}
	

}
