package gzz_lin.dna.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import gzz_lin.dna.annotation.RegisterItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
	/**
	 * 注册物品
	 * 
	 * @param obj
	 * @param registry
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void registerItems(Object obj, IForgeRegistry<Item> registry)
			throws IllegalArgumentException, IllegalAccessException {
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
					String[] oreDic=regAnno.oreDictionaries();
					String modid=regAnno.modid();
					Item item = (Item) f.get(obj);

					registry.register(item.setRegistryName(new ResourceLocation(modid+":"+regName)));
					if(regAnno.registryRender()){
						for(int i=0;i<mates.length;i++) {
							int mate=mates[i];
							String modelRes=null;
							if(modelRess.length!=mates.length) {
								modelRes=regAnno.modid()+":"+regName;
							}else {
								modelRes=regAnno.modid()+":"+modelRess[i];
							}
							registryItemRender(item, mate, modelRes);
						}
					}
					
					for(String d:oreDic) {
						OreDictionary.registerOre(d, item);
					}
				}
			}

		}
	}
	@SideOnly(Side.CLIENT)
	private static void registryItemRender(Item item,int meta,String modelRes) {
		ModelResourceLocation model=new ModelResourceLocation(modelRes);
		ModelLoader.setCustomModelResourceLocation(item, meta, model);
		
	}
}
