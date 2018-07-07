package gzz_lin.dna.net;

import gzz_lin.dna.DNACraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 网络传输
 * @author Gzz_lin
 *
 */
public class NetWorkLoader {
	private int id=0;
	public static final SimpleNetworkWrapper c=new SimpleNetworkWrapper(DNACraft.MODID);
	public NetWorkLoader() {
		registry(DNAMessageHandler.class, DNAMessage.class, id, Side.CLIENT);
	}
	private static <REQ extends IMessage, REPLY extends IMessage> void registry(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, Side side) {
	c.registerMessage(messageHandler, requestMessageType, discriminator, side);
	}	
}
