package gzz_lin.dna.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactory <E extends Entity> implements IRenderFactory<E>{

	private Class<? extends Render<E>> renderClass;
	public RenderFactory(Class<? extends Render<E>> renderClass) {
		this.renderClass=renderClass;
	}

	@Override
	public Render<? super E> createRenderFor(RenderManager manager) {
		try {
			//这里运用了反射，通过renderClass（Class类）的getConstructor获取一个参数为RenderManager类型的构造函数，通过这个构造函数传入manager创建一个新的实例。
			return renderClass.getConstructor(RenderManager.class).newInstance(manager);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
