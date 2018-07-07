package gzz_lin.dna.entity;

import gzz_lin.dna.item.DNAItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;

public class RenderBioMicrocapsule extends RenderSnowball<EntityBioMicrocapsule>{

	public RenderBioMicrocapsule(RenderManager renderManagerIn) {
		super(renderManagerIn, DNAItems.BIO_MICROCAPSULE_NUTRIENT, Minecraft.getMinecraft().getRenderItem());
		
	}

	@Override
	public ItemStack getStackToRender(EntityBioMicrocapsule entityIn) {
		
		return new ItemStack(this.item,1,3);
	}

	

	
	


	

}
