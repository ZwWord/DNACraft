package gzz_lin.dna.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DNAMessage implements IMessage {

	public NBTTagCompound ntb;
	@Override
	public void fromBytes(ByteBuf buf) {
		ntb=ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, ntb);
	}

}
