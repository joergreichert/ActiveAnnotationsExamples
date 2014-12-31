package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.Version;
import de.abg.jreichert.activeanno.jpa.example.internal.VersionLiteralImpl;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class VersionLiterals {
  private final static VersionLiteralImpl<Version> sharedInstance = new VersionLiteralImpl<Version>(Version.class);;
  
  private VersionLiterals() {
    
  }
  
  public static Property<Version> id() {
    return sharedInstance.id();
  }
  
  public static UnitLiteral<Version> unit() {
    return sharedInstance.unit();
  }
  
  public static Property<Version> name() {
    return sharedInstance.name();
  }
}
