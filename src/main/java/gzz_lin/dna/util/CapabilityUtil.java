package gzz_lin.dna.util;

import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.capability.IDNACapability;
import gzz_lin.dna.net.DNAMessage;
import gzz_lin.dna.net.NetWorkLoader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityUtil {
	public static void updata(EntityPlayerMP player) {
		if (player.hasCapability(CapablilityLoader.dna, null)) {
			DNAMessage message = new DNAMessage();
			IStorage<IDNACapability> storage = CapablilityLoader.dna.getStorage();
			IDNACapability dnaCapability = player.getCapability(CapablilityLoader.dna, null);
			message.ntb = new NBTTagCompound();
			message.ntb.setTag("dna", storage.writeNBT(CapablilityLoader.dna, dnaCapability, null));
			NetWorkLoader.c.sendTo(message, player);
		}
	}
}
