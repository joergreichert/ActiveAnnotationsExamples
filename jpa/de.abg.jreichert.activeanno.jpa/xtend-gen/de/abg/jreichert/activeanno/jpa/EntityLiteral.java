package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.jpa.EntityLiteralProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(EntityLiteralProcessor.class)
public @interface EntityLiteral {
}
