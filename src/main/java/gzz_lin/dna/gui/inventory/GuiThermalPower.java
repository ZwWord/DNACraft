package gzz_lin.dna.gui.inventory;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.block.DNABlocks;
import gzz_lin.dna.inventory.ContainerThermalPower;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiThermalPower extends GuiContainer {
	public static final ResourceLocation THERMAL_POWER_GUI = new ResourceLocation(
			DNACraft.MODID + ":textures/gui/thermal_power_gui.png");
	private ContainerThermalPower container;

	public GuiThermalPower(ContainerThermalPower containerThermalPower) {
		super(containerThermalPower);
		this.xSize = 176;
		this.ySize = 166;
		this.container = containerThermalPower;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(THERMAL_POWER_GUI);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		int x = this.width / 2 - this.xSize / 2;
		int y = this.height / 2 - this.ySize / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

		int burnInitTime = this.container.getBurnInitTime();
		int burnTime = this.container.getBurnTime();
		int electricity = this.container.getElectricity();

		//System.out.println(burnInitTime + ":" + burnTime + ":" + tile);
		int h = 0;
		if (burnInitTime != 0) {
			double d = (double)burnTime / (double)burnInitTime;
			h = (int) (d * 12.0d);
		}
		this.drawTexturedModalRect(x + 85, y + 48 - h, 176, 12 - h, 12, h);
		
			double d = (double)electricity/(double)this.container.getTileEntity().getMax();
			h = (int)(d*38.0d);
			//System.out.println(h + "," + d);
		
		this.drawTexturedModalRect(x + 112, y + 55 - h, 176, 37 - h+15, 24, h);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String text=this.container.getElectricity()+"/"+this.container.getTileEntity().getMax()+"(EU)";
		int w=this.fontRenderer.getStringWidth(text);
		this.fontRenderer.drawString(text, 125-w/2, 60, 0x00000);
		text=I18n.format(DNABlocks.THERMAL_POWER.getUnlocalizedName()+".name");
		w=this.fontRenderer.getStringWidth(text);
		this.fontRenderer.drawString(text, 5, 5, 0x00000);
	}

}
