package de.abg.jreichert.activeanno.jpa

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class LocationTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(Entity)

	@Test def void testEntity() {
		'''
			@de.abg.jreichert.activeanno.jpa.Entity
			class Location {
				
				new(Location parentLocation) {
					this.parentLocation = parentLocation
				}
				
				@de.abg.jreichert.activeanno.jpa.Property
				String timestamp = null
			
				@de.abg.jreichert.activeanno.jpa.Property
				String url
			
				@de.abg.jreichert.activeanno.jpa.Property
				Location parentLocation
			
				@de.abg.jreichert.activeanno.jpa.Property
				java.util.Set<Location> aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]
			
				@de.abg.jreichert.activeanno.jpa.Property
				java.util.Set<Unit> units = <Unit>newTreeSet[a,b|a.name.compareTo(b.name)]
				
				override String toString() { "" }
			}
			
			@de.abg.jreichert.activeanno.jpa.Entity
			class Unit {
				
				new(Location location) {
					this.location = location
				}
				
				@de.abg.jreichert.activeanno.jpa.Property
				Location location
			
				@de.abg.jreichert.activeanno.jpa.Property
				String name
			
				@de.abg.jreichert.activeanno.jpa.Property
				java.util.Set<Version> versions = <Version>newTreeSet[a,b|a.name.compareTo(b.name)]
				
				override String toString() {}
			}
			
			@de.abg.jreichert.activeanno.jpa.Entity
			class Version {
				
				new(Unit unit) {
					this.unit = unit
				}	
				
				@de.abg.jreichert.activeanno.jpa.Property
				Unit unit
			
				@de.abg.jreichert.activeanno.jpa.Property
				String name
				
				override String toString() {
				}
			}
		'''.assertCompilesTo(
			'''
				MULTIPLE FILES WERE GENERATED
				
				File 1 : Location.java
				
				import de.abg.jreichert.activeanno.jpa.Entity;
				import de.abg.jreichert.activeanno.jpa.Property;
				import java.util.Comparator;
				import java.util.Set;
				import javax.persistence.Id;
				import org.eclipse.xtext.xbase.lib.CollectionLiterals;
				
				@Entity
				@javax.persistence.Entity
				@SuppressWarnings("all")
				public class Location {
				  public Location(final Location parentLocation) {
				    this.parentLocation = parentLocation;
				  }
				  
				  @Property
				  private String timestamp = null;
				  
				  @Property
				  private String url;
				  
				  @Property
				  private Location parentLocation;
				  
				  @Property
				  private Set<Location> aggregatedLocations = CollectionLiterals.<Location>newTreeSet(new Comparator<Location>() {
				    public int compare(final Location a, final Location b) {
				      return a.url.compareTo(b.url);
				    }
				  });
				  
				  @Property
				  private Set<Unit> units = CollectionLiterals.<Unit>newTreeSet(new Comparator<Unit>() {
				    public int compare(final Unit a, final Unit b) {
				      String _name = a.getName();
				      String _name_1 = b.getName();
				      return _name.compareTo(_name_1);
				    }
				  });
				  
				  public String toString() {
				    return "";
				  }
				  
				  public Location() {
				  }
				  
				  @Id
				  private Long id = -1L;
				  
				  public Long getId() {
				    return this.id;
				  }
				  
				  public void setId(final Long id) {
				    this.id = id;
				  }
				  
				  public String getTimestamp() {
				    return this.timestamp;
				  }
				  
				  public void setTimestamp(final String timestamp) {
				    this.timestamp = timestamp;
				  }
				  
				  public String getUrl() {
				    return this.url;
				  }
				  
				  public void setUrl(final String url) {
				    this.url = url;
				  }
				  
				  public Location getParentLocation() {
				    return this.parentLocation;
				  }
				  
				  public void setParentLocation(final Location parentLocation) {
				    this.parentLocation = parentLocation;
				  }
				  
				  public Set<Location> getAggregatedLocations() {
				    return this.aggregatedLocations;
				  }
				  
				  public void setAggregatedLocations(final Set<Location> aggregatedLocations) {
				    this.aggregatedLocations = aggregatedLocations;
				  }
				  
				  public Set<Unit> getUnits() {
				    return this.units;
				  }
				  
				  public void setUnits(final Set<Unit> units) {
				    this.units = units;
				  }
				}
				
				File 2 : LocationLiteral.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class LocationLiteral<T> extends LocationLiteralImpl<T> implements Property<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public LocationLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 3 : LocationLiteralImpl.java
				
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
				  
				  public Property<T> id() {
				    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
				  }
				}
				
				File 4 : LocationLiterals.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class LocationLiterals {
				  private final static LocationLiteralImpl<Location> sharedInstance = new LocationLiteralImpl<Location>(Location.class);;
				  
				  private LocationLiterals() {
				    
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
				  
				  public static Property<Location> id() {
				    return sharedInstance.id();
				  }
				}
				
				File 5 : Unit.java
				
				import de.abg.jreichert.activeanno.jpa.Entity;
				import de.abg.jreichert.activeanno.jpa.Property;
				import java.util.Comparator;
				import java.util.Set;
				import javax.persistence.Id;
				import org.eclipse.xtext.xbase.lib.CollectionLiterals;
				
				@Entity
				@javax.persistence.Entity
				@SuppressWarnings("all")
				public class Unit {
				  public Unit(final Location location) {
				    this.location = location;
				  }
				  
				  @Property
				  private Location location;
				  
				  @Property
				  private String name;
				  
				  @Property
				  private Set<Version> versions = CollectionLiterals.<Version>newTreeSet(new Comparator<Version>() {
				    public int compare(final Version a, final Version b) {
				      String _name = a.getName();
				      String _name_1 = b.getName();
				      return _name.compareTo(_name_1);
				    }
				  });
				  
				  public String toString() {
				    return null;
				  }
				  
				  public Unit() {
				  }
				  
				  @Id
				  private Long id = -1L;
				  
				  public Long getId() {
				    return this.id;
				  }
				  
				  public void setId(final Long id) {
				    this.id = id;
				  }
				  
				  public Location getLocation() {
				    return this.location;
				  }
				  
				  public void setLocation(final Location location) {
				    this.location = location;
				  }
				  
				  public String getName() {
				    return this.name;
				  }
				  
				  public void setName(final String name) {
				    this.name = name;
				  }
				  
				  public Set<Version> getVersions() {
				    return this.versions;
				  }
				  
				  public void setVersions(final Set<Version> versions) {
				    this.versions = versions;
				  }
				}
				
				File 6 : UnitLiteral.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class UnitLiteral<T> extends UnitLiteralImpl<T> implements Property<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public UnitLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 7 : UnitLiteralImpl.java
				
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
				  
				  public LocationLiteral<T> location() {
				    return new LocationLiteral<T>(getParentPath(), "location", owningClass);
				  }
				  
				  public Property<T> name() {
				    return new LeafProperty<T>(getParentPath(), "name", false, owningClass);
				  }
				  
				  public VersionLiteral<T> versions() {
				    return new VersionLiteral<T>(getParentPath(), "versions", owningClass);
				  }
				  
				  public Property<T> id() {
				    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
				  }
				}
				
				File 8 : UnitLiterals.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class UnitLiterals {
				  private final static UnitLiteralImpl<Unit> sharedInstance = new UnitLiteralImpl<Unit>(Unit.class);;
				  
				  private UnitLiterals() {
				    
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
				  
				  public static Property<Unit> id() {
				    return sharedInstance.id();
				  }
				}
				
				File 9 : Version.java
				
				import de.abg.jreichert.activeanno.jpa.Entity;
				import de.abg.jreichert.activeanno.jpa.Property;
				import javax.persistence.Id;
				
				@Entity
				@javax.persistence.Entity
				@SuppressWarnings("all")
				public class Version {
				  public Version(final Unit unit) {
				    this.unit = unit;
				  }
				  
				  @Property
				  private Unit unit;
				  
				  @Property
				  private String name;
				  
				  public String toString() {
				    return null;
				  }
				  
				  public Version() {
				  }
				  
				  @Id
				  private Long id = -1L;
				  
				  public Long getId() {
				    return this.id;
				  }
				  
				  public void setId(final Long id) {
				    this.id = id;
				  }
				  
				  public Unit getUnit() {
				    return this.unit;
				  }
				  
				  public void setUnit(final Unit unit) {
				    this.unit = unit;
				  }
				  
				  public String getName() {
				    return this.name;
				  }
				  
				  public void setName(final String name) {
				    this.name = name;
				  }
				}
				
				File 10 : VersionLiteral.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class VersionLiteral<T> extends VersionLiteralImpl<T> implements Property<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public VersionLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 11 : VersionLiteralImpl.java
				
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
				  
				  public UnitLiteral<T> unit() {
				    return new UnitLiteral<T>(getParentPath(), "unit", owningClass);
				  }
				  
				  public Property<T> name() {
				    return new LeafProperty<T>(getParentPath(), "name", false, owningClass);
				  }
				  
				  public Property<T> id() {
				    return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
				  }
				}
				
				File 12 : VersionLiterals.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class VersionLiterals {
				  private final static VersionLiteralImpl<Version> sharedInstance = new VersionLiteralImpl<Version>(Version.class);;
				  
				  private VersionLiterals() {
				    
				  }
				  
				  public static UnitLiteral<Version> unit() {
				    return sharedInstance.unit();
				  }
				  
				  public static Property<Version> name() {
				    return sharedInstance.name();
				  }
				  
				  public static Property<Version> id() {
				    return sharedInstance.id();
				  }
				}
				
			''')
	}
}
