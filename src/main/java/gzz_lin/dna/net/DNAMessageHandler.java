package gzz_lin.dna.net;

import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.capability.IDNACapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class DNAMessageHandler implements IMessageHandler<DNAMessage, IMessage> {

	@Override
	public IMessage onMessage(DNAMessage message, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			final NBTBase nbt = message.ntb.getTag("dna");
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				EntityPlayer player = Minecraft.getMinecraft().player;
				
				@Override
				public void run() {
					if(player.hasCapability(CapablilityLoader.dna, null)) {
						IDNACapability idnaCapability=player.getCapability(CapablilityLoader.dna, null);
						IStorage<IDNACapability> storage=CapablilityLoader.dna.getStorage();
						storage.readNBT(CapablilityLoader.dna, idnaCapability, null, nbt);
					}
				}
			});
		}
		return null;
	}

}
