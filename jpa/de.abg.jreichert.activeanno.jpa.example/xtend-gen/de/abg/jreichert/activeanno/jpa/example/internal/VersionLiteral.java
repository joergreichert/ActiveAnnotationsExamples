package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.VersionLiteralImpl;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class VersionLiteral<T> extends VersionLiteralImpl<T> implements Property<T> {
  private final static long serialVersionUID = 1L;
  
  public VersionLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath, owningClass);
  }
}
