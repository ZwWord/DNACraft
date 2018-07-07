package gzz_lin.dna.item;

import gzz_lin.dna.annotation.RegisterItem;
import net.minecraft.item.Item;

public class DNAItems {


	@RegisterItem(registryName="bio_microcapsule_null",meta= {0,1,2},modleRes= {"iron_bio_microcapsule_null","gold_bio_microcapsule_null","diamond_bio_microcapsule_null"})
	public static final Item BIO_MICROCAPSULE_NULL=new ItemBioMicrocapsuleNull();
	@RegisterItem(registryName="bio_microcapsule_nutrient",meta= {0,1,2,3},modleRes= {"iron_bio_microcapsule_nutrient","gold_bio_microcapsule_nutrient","diamond_bio_microcapsule_nutrient","fly_bio_microcapsule"})
	public static final Item BIO_MICROCAPSULE_NUTRIENT=new ItemBioMicrocapsuleNutrient();
	@RegisterItem(registryName="bio_microcapsule_capture",meta= {0,1,2},modleRes= {"iron_bio_microcapsule_capture","gold_bio_microcapsule_capture","diamond_bio_microcapsule_capture"})
	public static final Item BIO_MICROCAPSULE_CAPTURE=new ItemBioMicrocapsuleCapture();
}
