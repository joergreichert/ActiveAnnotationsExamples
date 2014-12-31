package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.Location;
import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteralImpl;
import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteral;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class LocationLiterals {
  private final static LocationLiteralImpl<Location> sharedInstance = new LocationLiteralImpl<Location>(Location.class);;
  
  private LocationLiterals() {
    
  }
  
  public static Property<Location> id() {
    return sharedInstance.id();
  }
  
  public static Property<Location> timestamp() {
    return sharedInstance.timestamp();
  }
  
  public static Property<Location> url() {
    return sharedInstance.url();
  }
  
  public static LocationLiteral<Location> parentLocation() {
    return sharedInstance.parentLocation();
  }
  
  public static LocationLiteral<Location> aggregatedLocations() {
    return sharedInstance.aggregatedLocations();
  }
  
  public static UnitLiteral<Location> units() {
    return sharedInstance.units();
  }
}
