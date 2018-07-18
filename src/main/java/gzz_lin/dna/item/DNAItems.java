package gzz_lin.dna.item;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.annotation.RegisterItem;
import net.minecraft.item.Item;

public class DNAItems {


	@RegisterItem(registryName="bio_microcapsule_null",meta= {0,1,2},modleRes= {"iron_bio_microcapsule_null","gold_bio_microcapsule_null","diamond_bio_microcapsule_null"})
	public static final Item BIO_MICROCAPSULE_NULL=new ItemBioMicrocapsuleNull();
	@RegisterItem(registryName="bio_microcapsule_nutrient",meta= {0,1,2,3},modleRes= {"iron_bio_microcapsule_nutrient","gold_bio_microcapsule_nutrient","diamond_bio_microcapsule_nutrient","fly_bio_microcapsule"})
	public static final Item BIO_MICROCAPSULE_NUTRIENT=new ItemBioMicrocapsuleNutrient();
	@RegisterItem(registryName="bio_microcapsule_capture",meta= {0,1,2},modleRes= {"iron_bio_microcapsule_capture","gold_bio_microcapsule_capture","diamond_bio_microcapsule_capture"})
	public static final Item BIO_MICROCAPSULE_CAPTURE=new ItemBioMicrocapsuleCapture();
	@RegisterItem(registryName="sulphur_dust",oreDictionaries= {"dustSulphur"})
	public static final Item SULPHUR_DUST=new Item().setUnlocalizedName("SulphurDust").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="sulfur_carbon_dust",oreDictionaries= {"dustSulfurCarbon"})
	public static final Item SULFUR_CARBON_DUST=new Item().setUnlocalizedName("SulfurCarbonDust").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="copper_ingot")
	public static final Item COPPER_INGOT=new Item().setUnlocalizedName("CopperIngot").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="copper_pane")
	public static final Item COPPER_PANE=new Item().setUnlocalizedName("CopperPane").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="copper_clad_pane")
	public static final Item COPPER_CLAD_PANE=new Item().setUnlocalizedName("CopperCladPane").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="resin")
	public static final Item RESIN=new Item().setUnlocalizedName("Resin").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="corrosive")
	public static final Item CORROSIVE=new Item().setUnlocalizedName("Corrosive").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="semiconductor_pane")
	public static final Item SEMICONDUCTOR_PANE=new Item().setUnlocalizedName("SemiconductorPane").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="semiconductor")
	public static final Item SEMICONDUCTOR=new Item().setUnlocalizedName("Semiconductor").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="ic")
	public static final Item IC=new Item().setUnlocalizedName("IC").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="iron_pane")
	public static final Item IRON_PANE=new Item().setUnlocalizedName("IronPane").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="gold_pane")
	public static final Item GOlD_PANE=new Item().setUnlocalizedName("GoldPane").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="copper_coil")
	public static final Item COPPER_COIL=new Item().setUnlocalizedName("CopperCoil").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="rotor")
	public static final Item ROTOR=new Item().setUnlocalizedName("Rotor").setCreativeTab(DNACraft.TAB);
	@RegisterItem(registryName="motor")
	public static final Item MOTOR=new Item().setUnlocalizedName("Motor").setCreativeTab(DNACraft.TAB);
	
}
