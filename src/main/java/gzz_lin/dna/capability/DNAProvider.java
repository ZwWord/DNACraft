package gzz_lin.dna.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/**
 * DNA能力提供者
 * @author Gzz_lin
 *
 */
public class DNAProvider implements ICapabilitySerializable<NBTTagCompound>{

	private IDNACapability dna=new DNACapability();
	private IStorage<IDNACapability> storage=CapablilityLoader.dna.getStorage();
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return CapablilityLoader.dna.equals(capability);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		return  CapablilityLoader.dna.equals(capability)?(T)dna:null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag=new NBTTagCompound();
		tag.setTag("dna", storage.writeNBT(CapablilityLoader.dna, dna, null));
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		storage.readNBT(CapablilityLoader.dna, dna, null, nbt.getTag("dna"));
	}


}
