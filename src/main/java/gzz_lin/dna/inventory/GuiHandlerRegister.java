package gzz_lin.dna.inventory;

import gzz_lin.dna.DNACraft;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHandlerRegister {
	public static void registryAll() {
		NetworkRegistry.INSTANCE.registerGuiHandler(DNACraft.instance, new GuiHandler());
	}
}
