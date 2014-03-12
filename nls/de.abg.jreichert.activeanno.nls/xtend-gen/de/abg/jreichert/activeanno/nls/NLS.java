package de.abg.jreichert.activeanno.nls;

import de.abg.jreichert.activeanno.nls.NLSProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(NLSProcessor.class)
public @interface NLS {
  public String propertyFileName();
}
