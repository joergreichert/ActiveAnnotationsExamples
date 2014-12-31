package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteralImpl;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class LocationLiteral<T> extends LocationLiteralImpl<T> implements Property<T> {
  private final static long serialVersionUID = 1L;
  
  public LocationLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath, owningClass);
  }
}
