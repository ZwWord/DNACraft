package gzz_lin.dna.util;

import java.util.HashMap;
import java.util.HashSet;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.capability.IDNACapability;
import gzz_lin.dna.model.DNA;
import gzz_lin.dna.model.DNAHolder;
import gzz_lin.dna.model.DNAValue;
import gzz_lin.dna.net.DNAMessage;
import gzz_lin.dna.net.NetWorkLoader;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public final class DNAManager {
	public static final ResourceLocation DNAICON = new ResourceLocation(DNACraft.MODID + ":textures/gui/dnas.png");
	private static final HashSet<DNA> DNA = new HashSet<>();
	private static final HashMap<Class, DNAHolder> DNA_HOLDER = new HashMap<>();
	public static final DNA TEST = new DNA("test", 100, DNAICON, 0, 0);
	public static final DNA HEALTH = new DNA("health", 4000, DNAICON, 12, 0);//生命
	public static final DNA SKIN = new DNA("skin", 8000, DNAICON, 24, 0);//皮肤
	public static final DNA DIGESTION = new DNA("digestion", 5000, DNAICON, 36, 0);//消化系统
	public static final DNA JUMP = new DNA("jump", 3000, DNAICON, 48, 0);//跳跃
	public static final DNA FIREPROOFING = new DNA("fireproofing", 3000, DNAICON, 60, 0);//火免
	public static final DNA NIGHT_VISION = new DNA("night_vision", 1000, DNAICON, 72, 0);//夜视
	public static final DNA FLY = new DNA("fly", 10000, DNAICON, 84, 0);//飞行
	private static final DNAHolder PING = new DNAHolder(EntityPig.class,new DNAValue(HEALTH, 10, 50), new DNAValue(DIGESTION, 100, 200));
	private static final DNAHolder ZOMBIE = new DNAHolder(EntityZombie.class,new DNAValue(SKIN, 10, 50), new DNAValue(NIGHT_VISION, 100, 200));
	private static final DNAHolder HORSE = new DNAHolder(EntityHorse.class,new DNAValue(HEALTH, 15, 80), new DNAValue(JUMP, 100, 200));
	
	static {
		intitDNA();
		initDNAHolder();
	}
	private static void intitDNA() {
		registryDNA(TEST);
		registryDNA(HEALTH);
		registryDNA(SKIN);
		registryDNA(DIGESTION);
		registryDNA(JUMP);
		registryDNA(FIREPROOFING);
		registryDNA(NIGHT_VISION);
		registryDNA(FLY);
	}
	private  static void initDNAHolder() {
		registryDNAHolder(PING);
		registryDNAHolder(ZOMBIE);
		registryDNAHolder(HORSE);
	}

	public static void registryDNA(DNA dna) {
		DNA.add(dna);
	}

	public static void registryDNAHolder(DNAHolder dnaHolder) {
		DNA_HOLDER.put(dnaHolder.getEnttiyClass(), dnaHolder);
	}

	public static DNA getDNAFromName(String name) {
		for (DNA d : DNA) {
			if (d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}

	public static DNAValue[] getDNAValuesFormClass(Class clazz) {
		DNAHolder dnaHolder = DNA_HOLDER.get(clazz);
		if (dnaHolder == null) {
			return new DNAValue[0];
		}
		HashSet<DNAValue> dnaValues = dnaHolder.getDnaValues();
		DNAValue[] dnas = new DNAValue[dnaValues.size()];
		int i = 0;
		for (DNAValue d : dnaValues) {
			dnas[i] = d;
			i++;
		}
		return dnas;
	}

	public static HashSet<DNA> getDNA() {
		return (HashSet<DNA>) DNA.clone();
	}

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
