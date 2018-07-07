package gzz_lin.dna.item;

import gzz_lin.dna.DNACraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * 微型生物胶囊
 * 
 * @author Gzz_lin
 *
 */
public class ItemBioMicrocapsuleNull extends Item {

	public ItemBioMicrocapsuleNull() {
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		if (meta < 3)
			return "item.BioMicrocapsuleNull." + BMMaterial.values()[meta];
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

}
