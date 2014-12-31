package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.VersionLiteral;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class UnitLiteralImpl<T> extends PropertiesCollection {
  private final static long serialVersionUID = 1L;
  
  private Class<T> owningClass;
  
  public UnitLiteralImpl(final Class<T> owningClass) {
    super(null);
    this.owningClass = owningClass;
  }
  
  public UnitLiteralImpl(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath);
    this.owningClass = owningClass;
  }
  
  public Property<T> id() {
    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
  }
  
  public LocationLiteral<T> location() {
    return new LocationLiteral<T>(getParentPath(), "location", owningClass);
  }
  
  public Property<T> name() {
    return new LeafProperty<T>(getParentPath(), "name", false, owningClass);
  }
  
  public VersionLiteral<T> versions() {
    return new VersionLiteral<T>(getParentPath(), "versions", owningClass);
  }
}
