package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.jpa.EntityProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(EntityProcessor.class)
public @interface Entity {
  public String idGenerationType() default "GenerationType.AUTO";
  public boolean generateLiterals() default true;
}
