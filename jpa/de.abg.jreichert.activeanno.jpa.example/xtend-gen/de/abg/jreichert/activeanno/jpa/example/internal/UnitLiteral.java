package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteralImpl;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class UnitLiteral<T> extends UnitLiteralImpl<T> implements Property<T> {
  private final static long serialVersionUID = 1L;
  
  public UnitLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath, owningClass);
  }
}
