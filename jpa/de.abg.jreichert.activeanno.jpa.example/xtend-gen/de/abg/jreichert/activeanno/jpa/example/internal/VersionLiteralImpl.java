package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteral;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class VersionLiteralImpl<T> extends PropertiesCollection {
  private final static long serialVersionUID = 1L;
  
  private Class<T> owningClass;
  
  public VersionLiteralImpl(final Class<T> owningClass) {
    super(null);
    this.owningClass = owningClass;
  }
  
  public VersionLiteralImpl(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath);
    this.owningClass = owningClass;
  }
  
  public Property<T> id() {
    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
  }
  
  public UnitLiteral<T> unit() {
    return new UnitLiteral<T>(getParentPath(), "unit", owningClass);
  }
  
  public Property<T> name() {
    return new LeafProperty<T>(getParentPath(), "name", false, owningClass);
  }
}
