package gzz_lin.dna.capability;

import java.util.concurrent.Callable;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * 能力加载
 * @author Gzz_lin
 *
 */
public class CapablilityLoader {
	@CapabilityInject(IDNACapability.class)
	public static Capability<IDNACapability> dna;
	public CapablilityLoader() {
		CapabilityManager.INSTANCE.register(IDNACapability.class, new DNAStorage(), new Callable<IDNACapability>() {
			@Override
			public IDNACapability call() throws Exception {
				return new DNACapability();
			}});		
	}
	

}
