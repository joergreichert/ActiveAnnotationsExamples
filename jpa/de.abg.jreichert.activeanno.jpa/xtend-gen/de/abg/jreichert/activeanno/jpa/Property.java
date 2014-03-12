package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.jpa.PropertyProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.FIELD)
@Active(PropertyProcessor.class)
public @interface Property {
  public String idGenerationType() default "";
}
