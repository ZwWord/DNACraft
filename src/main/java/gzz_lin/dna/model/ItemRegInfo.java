package gzz_lin.dna.model;

import java.util.Map;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemRegInfo {
	public final ResourceLocation REGISTRY_NAME;
	public final boolean REGISTRY_RENDER;
	public final Map<Integer, ModelResourceLocation> META_MODEL;
	public final String[] ORE_DICTIONARIES;
	public final Item ITEM;
	public ItemRegInfo(Item item,ResourceLocation registryName, boolean registryRender, Map<Integer, ModelResourceLocation> metaAndModel,
			String[] oreDictionaries) {
		this.ITEM=item;
		this.REGISTRY_NAME = registryName;
		
		this.REGISTRY_RENDER = registryRender;
		this.META_MODEL = metaAndModel;
		this.ORE_DICTIONARIES = oreDictionaries;
	}
	
}
