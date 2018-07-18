package gzz_lin.dna.inventory;

import gzz_lin.dna.gui.inventory.GuiThermalPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	public static final int THERMAL_POWER=1;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case THERMAL_POWER:
			return new ContainerThermalPower(player,world.getTileEntity(new BlockPos(x, y, z)));
		default:
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case THERMAL_POWER:
			return new GuiThermalPower(new ContainerThermalPower(player,world.getTileEntity(new BlockPos(x, y, z))));
		default:
			break;
		}
		return null;
	}

}
