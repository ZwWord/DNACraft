package gzz_lin.dna.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gzz_lin.dna.annotation.RegisterBlock;
import gzz_lin.dna.annotation.RegisterItem;
import gzz_lin.dna.block.DNABlocks;
import gzz_lin.dna.item.DNAItems;
import gzz_lin.dna.model.BlockRegInfo;
import gzz_lin.dna.model.ItemRegInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * 注解执行工具
 * 
 * @author Gzz_lin
 *
 */
public class AnnotationUtil {
	private static final List<ItemRegInfo> ITEMS = new ArrayList<>();
	private static final List<BlockRegInfo> BLOCKS = new ArrayList<>();
static {
	try {
		addBlockObj(new DNABlocks());
		addItemsObj(new DNAItems());
		
	} catch (IllegalArgumentException e) {
		
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		
		e.printStackTrace();
	}
}
	public static void addItemsObj(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class clazz = obj.getClass();
		Field[] fields = clazz.getFields();
		for (Field f : fields) {
			Annotation[] annos = f.getAnnotations();
			for (Annotation anno : annos) {
				if (anno.annotationType().equals(RegisterItem.class)) {
					RegisterItem regAnno = (RegisterItem) anno;
					String regName = regAnno.registryName();
					boolean regReander = regAnno.registryRender();
					int[] mates = regAnno.meta();
					String[] modelRess = regAnno.modleRes();
					String[] oreDic = regAnno.oreDictionaries();
					String modid = regAnno.modid();
					Item item = (Item) f.get(obj);
					Map<Integer, ModelResourceLocation> metaAndModel = new HashMap<>();
					for (int i = 0; i < mates.length; i++) {
						int k = mates[i];
						ModelResourceLocation v;
						if (modelRess.length != mates.length) {
							v = new ModelResourceLocation(modid + ":" + regName);
						} else {
							v = new ModelResourceLocation(modid + ":" + modelRess[i]);
						}
						metaAndModel.put(k, v);
					}
					ItemRegInfo itemRegInfo = new ItemRegInfo(item, new ResourceLocation(modid + ":" + regName),
							regReander, metaAndModel, oreDic);
					ITEMS.add(itemRegInfo);
				}
			}

		}
	}

	public static void addBlockObj(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class clazz = obj.getClass();
		Field[] fields = clazz.getFields();
		for (Field f : fields) {
			Annotation[] annos = f.getAnnotations();
			for (Annotation anno : annos) {
				if (anno.annotationType().equals(RegisterBlock.class)) {
					RegisterBlock regAnno = (RegisterBlock) anno;
					String regName = regAnno.registryName();
					boolean regItemBlock = regAnno.registryItemBlock();
					String[] oreDic = regAnno.oreDictionaries();
					String modid = regAnno.modid();
					Block block = (Block) f.get(obj);
					BlockRegInfo blockRegInfo = new BlockRegInfo(block, new ResourceLocation(modid + ":" + regName),
							regItemBlock, oreDic);
					BLOCKS.add(blockRegInfo);
					if (regItemBlock) {
						HashMap<Integer, ModelResourceLocation> re = new HashMap<>();
						re.put(0, new ModelResourceLocation(modid + ":" + regName));
						ItemRegInfo itemRegInfo = new ItemRegInfo(new ItemBlock(block),
								new ResourceLocation(modid + ":" + regName), true, re, oreDic);
						ITEMS.add(itemRegInfo);
					}
				}
			}
		}
	}

	public static void registryItems(IForgeRegistry<Item> registry) {
		for (ItemRegInfo iri : ITEMS) {
			registry.register(iri.ITEM.setRegistryName(iri.REGISTRY_NAME));
			if (iri.REGISTRY_RENDER) {
				for (Entry<Integer, ModelResourceLocation> kv : iri.META_MODEL.entrySet()) {
					registryItemRender(iri.ITEM, kv.getKey(), kv.getValue());
				}
			}
			for (String oreDic : iri.ORE_DICTIONARIES) {
				OreDictionary.registerOre(oreDic, iri.ITEM);
			}
		}
	}

	public static void registryBlocks(IForgeRegistry<Block> registry) {
		for (BlockRegInfo bri : BLOCKS) {
			registry.register(bri.BLOCK.setRegistryName(bri.REGISTRY_NAME));
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registryItemRender(Item item, int meta, ModelResourceLocation modelRes) {
		ModelLoader.setCustomModelResourceLocation(item, meta, modelRes);
	}
}
