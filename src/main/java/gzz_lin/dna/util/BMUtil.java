package gzz_lin.dna.util;

import gzz_lin.dna.DNACraft;
import gzz_lin.dna.item.ItemBioMicrocapsuleCapture;
import gzz_lin.dna.model.DNA;
import gzz_lin.dna.model.DNAValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderTooltipEvent;

public class BMUtil {
	private static Minecraft mc = Minecraft.getMinecraft();
	private static ResourceLocation texture = new ResourceLocation(DNACraft.MODID + ":textures/gui/particulars.png");
	public static EntityLiving getEntity(ItemStack stack, World worldIn) {
		EntityLiving entity = null;
		if (stack.hasTagCompound()) {
			NBTTagCompound entityTag = (NBTTagCompound) stack.getTagCompound().getTag("entity");
			entity = (EntityLiving) EntityList.createEntityFromNBT(entityTag, worldIn);
			float max = stack.getTagCompound().getFloat("maxHealth");
			float h = stack.getTagCompound().getFloat("health");
			entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(max);
			entity.setHealth(h);
		}
		return entity;

	}

	public static void renderTooltip(RenderTooltipEvent.Pre event) {
		ItemStack stack = event.getStack();
		if (stack.getItem() instanceof ItemBioMicrocapsuleCapture && stack.hasTagCompound()) {
			int mouseX = event.getX();
			int mouseY = event.getY();
			int bgW = 117;
			int bgH = 97;
			int startX = mouseX;
			int startY = mouseY;
			int screenWidth = event.getScreenWidth();
			int screenHeight = event.getScreenHeight();

			if (screenWidth - mouseX < bgW) {
				startX = mouseX - bgW;
			}
			if (screenHeight - mouseY < bgH) {
				startY = mouseY - bgH;
			}

			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			GlStateManager.enableAlpha();
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			EntityLivingBase entity = getEntity(stack, mc.world);

			mc.getTextureManager().bindTexture(texture);
			mc.currentScreen.drawTexturedModalRect(startX, startY, 0, 0, bgW, bgH);
			// -------------抄袭
			//比例约束
			GlStateManager.enableColorMaterial();
			GlStateManager.pushMatrix();
			float eV=(float) (Math.pow(entity.width*16, 2)*entity.height*16);
			float oneV=(float) (Math.pow(16, 2)*16);
			
			 float scale=(float) (15.0f/(Math.pow(eV/oneV,1.0/3.0)));
			 
			GlStateManager.translate((float) startX + bgW / 2 ,
					(float) startY + bgH / 2, 273.0F);
		
			GlStateManager.scale( -scale, scale, scale);
			
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
			RenderHelper.enableStandardItemLighting();
			GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, 0.0F, 0.0F);
			RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			
			//转动视角
			GlStateManager.rotate(30.0F, 1.0F, 0.0F, 0.0F);
			float r=(mc.getSystemTime()/10)%360.0f-180;
			GlStateManager.rotate(r, 0.0F, 1.0F, 0.0F);
			GlStateManager.enableDepth();
			rendermanager.setRenderShadow(false);
			rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, true);
			rendermanager.setRenderShadow(true);
			GlStateManager.disableDepth();
			GlStateManager.rotate(-r, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.popMatrix();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.disableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			// -------------抄袭结束

			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			GlStateManager.enableAlpha();
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			
			//绘制dna图标
			DNAValue[] dnaValues = DNAManager.getDNAValuesFormClass(entity.getClass());
			int dnaX=startX+10;
			int dnaY=startY+18;
			for(int i=0;i<dnaValues.length;i++) {
				if(i%2==0) {
					dnaX=startX+10;
					
				}else {
					dnaX=startX+bgW-22;
					
				}
				if((i+1)%3==0) {
					dnaY+=16;
				}
				DNAValue dnaValue=dnaValues[i];
				DNA dna=dnaValue.getDna();
				int dnaMax=dna.getMax();
				int min=dnaValue.getMin();
				int max=dnaValue.getMax();
				mc.getTextureManager().bindTexture(dna.getIcon());
				mc.currentScreen.drawTexturedModalRect(dnaX, dnaY, dna.getIconOffstX(), dna.getIconOffstY(), 12, 12);
				mc.getTextureManager().bindTexture(texture);
				mc.currentScreen.drawTexturedModalRect(dnaX-4, dnaY+13, 0, 107, 20, 2);
				float w=(float)max/(float)dnaMax*20.0f;
				mc.currentScreen.drawTexturedModalRect(dnaX-4, dnaY+13, 0, 109, (int) w, 2);
				w=(float)min/(float)dnaMax*20.0f;
				mc.currentScreen.drawTexturedModalRect(dnaX-4, dnaY+13, 0, 111, (int) w, 2);
				
				
			}
			
			
			mc.getTextureManager().bindTexture(texture);
			// 绘制血条
			float w = (entity.getMaxHealth() - entity.getHealth()) / entity.getMaxHealth() * 109;
			mc.currentScreen.drawTexturedModalRect(startX + 4, startY + 85, 0, 102, 109, 5);
			mc.currentScreen.drawTexturedModalRect(startX + 4, startY + 85, 0, 97, (int) w, 5);
			// 绘制文本
			FontRenderer fontRenderer = event.getFontRenderer();
			int textW = fontRenderer.getStringWidth(stack.getDisplayName());
			mc.currentScreen.drawString(fontRenderer, stack.getDisplayName(), startX + bgW / 2 - textW / 2, startY + 5,
					0xFFFFFF);
			textW = fontRenderer.getStringWidth(entity.getDisplayName().getFormattedText());
			mc.currentScreen.drawString(fontRenderer, entity.getDisplayName().getFormattedText(),
					startX + bgW / 2 - textW / 2, startY + 70, 0x586987);
			event.setCanceled(true);
		}
	}
}
