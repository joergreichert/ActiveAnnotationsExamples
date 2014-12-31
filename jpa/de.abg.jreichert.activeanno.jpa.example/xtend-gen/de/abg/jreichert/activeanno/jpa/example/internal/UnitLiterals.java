package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.Unit;
import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteralImpl;
import de.abg.jreichert.activeanno.jpa.example.internal.VersionLiteral;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class UnitLiterals {
  private final static UnitLiteralImpl<Unit> sharedInstance = new UnitLiteralImpl<Unit>(Unit.class);;
  
  private UnitLiterals() {
    
  }
  
  public static Property<Unit> id() {
    return sharedInstance.id();
  }
  
  public static LocationLiteral<Unit> location() {
    return sharedInstance.location();
  }
  
  public static Property<Unit> name() {
    return sharedInstance.name();
  }
  
  public static VersionLiteral<Unit> versions() {
    return sharedInstance.versions();
  }
}
