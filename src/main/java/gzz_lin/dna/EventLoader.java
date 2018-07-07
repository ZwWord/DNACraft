package gzz_lin.dna;

import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.capability.DNAProvider;
import gzz_lin.dna.capability.IDNACapability;
import gzz_lin.dna.item.DNAItems;
import gzz_lin.dna.util.AnnotationUtil;
import gzz_lin.dna.util.BMUtil;
import gzz_lin.dna.util.DNAManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * 原版事件监听
 * 
 * @author Gzz_lin
 *
 */
public class EventLoader {
	// 为玩家添加能力
	@SubscribeEvent
	public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getObject();
			ICapabilitySerializable<NBTTagCompound> provider = new DNAProvider();
			event.addCapability(new ResourceLocation(DNACraft.MODID + ":dna"), provider);
		}
	}

	// 玩家重生或者通过传送门同步能力
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		Capability<IDNACapability> capability = CapablilityLoader.dna;
		IStorage<IDNACapability> storage = capability.getStorage();
		if (event.getOriginal().hasCapability(capability, null)
				&& event.getEntityPlayer().hasCapability(capability, null)) {
			NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
			storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
		}
	}

	// 玩家实体进入世界同步数据
	@SubscribeEvent
	public void onEntityJoinWrold(EntityJoinWorldEvent event) {
		if (event.getWorld().isRemote) {
			return;
		}
		if (event.getEntity() instanceof EntityPlayerMP
				&& event.getEntity().hasCapability(CapablilityLoader.dna, null)) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			DNAManager.updata(player);
		}
	}

	// 注册物品
	@SubscribeEvent
	public void onRegistyItem(RegistryEvent.Register<Item> event)
			throws IllegalArgumentException, IllegalAccessException {
		IForgeRegistry<Item> registry = event.getRegistry();
		AnnotationUtil.registerItems(new DNAItems(), registry);
	}

	// 绘制物品详细信息
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onHUDRender(RenderTooltipEvent.Pre event) {
		BMUtil.renderTooltip(event);

	}
}
