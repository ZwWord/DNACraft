package gzz_lin.dna.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gzz_lin.dna.DNACraft;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterBlock {
 String registryName();
 boolean registryItemBlock() default false;
 String[] oreDictionaries() default {};
 String modid() default DNACraft.MODID;
 String unlocalizedName();
}
