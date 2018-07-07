package gzz_lin.dna.capability;

import java.util.HashMap;
import java.util.Map.Entry;

import gzz_lin.dna.model.DNA;
import gzz_lin.dna.util.DNAManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * dna数据存储器
 * @author Gzz_lin
 *
 */
public class DNAStorage implements Capability.IStorage<IDNACapability>{

	@Override
	public NBTBase writeNBT(Capability<IDNACapability> capability, IDNACapability instance, EnumFacing side) {
		NBTTagList list = new NBTTagList();
		HashMap<DNA, Integer> map=instance.getDNA();
		for(Entry<DNA, Integer> di:map.entrySet()) {
			NBTTagCompound tag=new NBTTagCompound();
			tag.setString("name",di.getKey().getName());
			tag.setInteger("value", di.getValue());
			list.appendTag(tag);
		}
		return list;
	}

	@Override
	public void readNBT(Capability<IDNACapability> capability, IDNACapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagList list =(NBTTagList)nbt;
		HashMap<DNA, Integer> map=new HashMap<>();
		for(int i=0;i<list.tagCount();i++) {
			NBTTagCompound tag=list.getCompoundTagAt(i);
			DNA dna=DNAManager.getDNAFromName(tag.getString("name"));
			int value=tag.getInteger("value");
			map.put(dna, value);
		}
		instance.setDNA(map);
	}

}
