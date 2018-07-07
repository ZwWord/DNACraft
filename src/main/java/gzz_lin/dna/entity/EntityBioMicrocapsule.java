package gzz_lin.dna.entity;

import gzz_lin.dna.item.DNAItems;
import gzz_lin.dna.util.DNASoundEvents;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBioMicrocapsule extends EntityThrowable {

	private ItemStack itemStack = ItemStack.EMPTY;

	public EntityBioMicrocapsule(World worldIn, EntityLivingBase throwerIn, ItemStack itemStack) {

		this(worldIn, throwerIn);
		this.itemStack = itemStack;

	}

	public EntityBioMicrocapsule(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);

	}

	public EntityBioMicrocapsule(World worldIn, EntityLivingBase throwerIn) {

		super(worldIn, throwerIn);

	}

	public EntityBioMicrocapsule(World worldIn) {
		super(worldIn);

	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!this.world.isRemote) {
			if (result.entityHit != null && result.entityHit instanceof EntityLiving) {
				EntityLiving entity = (EntityLiving) result.entityHit;
				float maxHealth = entity.getMaxHealth();
				float health = entity.getHealth();
				int rand = (int) (Math.random() * 100 * (this.itemStack.getMetadata() + 1));
				if (health < maxHealth * 0.8 && rand > 90) {
					ItemStack i = new ItemStack(DNAItems.BIO_MICROCAPSULE_CAPTURE, 1, this.itemStack.getMetadata());
					if (!i.hasTagCompound())
						i.setTagCompound(new NBTTagCompound());
					i.getTagCompound().setTag("entity", entity.serializeNBT());
					i.getTagCompound().setFloat("maxHealth", maxHealth);
					i.getTagCompound().setFloat("health", health);
					EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, i);
					entity.setDead();
					this.world.playSound((EntityPlayer)null, this.posX,this.posY, this.posZ, DNASoundEvents.BIO_MICROCAPSULE_CAPTURE, SoundCategory.PLAYERS, 1.0F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
					this.world.spawnEntity(entityItem);
					this.setDead();
					return;
				}
			}
			this.world.setEntityState(this, (byte)3);
			this.world.playSound((EntityPlayer)null, this.posX,this.posY, this.posZ, DNASoundEvents.BIO_MICROCAPSULE_BROKEN, SoundCategory.PLAYERS, 1.0F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
			this.setDead();
		}

	}

}
