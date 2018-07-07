package gzz_lin.dna.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderLoader {
	//@SideOnly(Side.CLIENT)
	public RenderLoader(){
		registerEntityRenders();
	}
	@SideOnly(Side.CLIENT)
	private static void registerEntityRenders() {
		registerEntityRender(EntityBioMicrocapsule.class,RenderBioMicrocapsule.class);
	}

	@SideOnly(Side.CLIENT)
	private static <T extends Entity> void registerEntityRender(Class<T> entityClass,
			Class<? extends Render<T>> render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderFactory<T>(render));
	}
}
