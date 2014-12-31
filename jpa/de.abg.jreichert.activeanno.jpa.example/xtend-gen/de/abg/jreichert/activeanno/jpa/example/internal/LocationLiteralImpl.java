package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteral;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class LocationLiteralImpl<T> extends PropertiesCollection {
  private final static long serialVersionUID = 1L;
  
  private Class<T> owningClass;
  
  public LocationLiteralImpl(final Class<T> owningClass) {
    super(null);
    this.owningClass = owningClass;
  }
  
  public LocationLiteralImpl(final String parentPath, final String additionalPath, final Class<T> owningClass) {
    super(parentPath, additionalPath);
    this.owningClass = owningClass;
  }
  
  public Property<T> id() {
    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
  }
  
  public Property<T> timestamp() {
    return new LeafProperty<T>(getParentPath(), "timestamp", false, owningClass);
  }
  
  public Property<T> url() {
    return new LeafProperty<T>(getParentPath(), "url", false, owningClass);
  }
  
  public LocationLiteral<T> parentLocation() {
    return new LocationLiteral<T>(getParentPath(), "parentLocation", owningClass);
  }
  
  public LocationLiteral<T> aggregatedLocations() {
    return new LocationLiteral<T>(getParentPath(), "aggregatedLocations", owningClass);
  }
  
  public UnitLiteral<T> units() {
    return new UnitLiteral<T>(getParentPath(), "units", owningClass);
  }
}
