package gzz_lin.dna.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gzz_lin.dna.DNACraft;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterItem {
	String registryName();
	boolean registryRender() default true;
	int[] meta() default {0};
	String[] modleRes() default {};
	String[] oreDictionaries() default {};
	String modid() default DNACraft.MODID;
}
