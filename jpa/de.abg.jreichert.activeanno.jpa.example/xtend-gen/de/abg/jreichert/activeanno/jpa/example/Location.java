package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.Entity;
import de.abg.jreichert.activeanno.jpa.Property;
import de.abg.jreichert.activeanno.jpa.example.Unit;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.hibernate.annotations.Sort;

@Entity
@javax.persistence.Entity
@SuppressWarnings("all")
public class Location {
  public Location(final Location parentLocation) {
    this.parentLocation = parentLocation;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Property
  private Long id = null;
  
  @Property
  private String timestamp = null;
  
  @Property
  private String url;
  
  @Property
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location parentLocation;
  
  @Property
  @OneToMany(mappedBy = "parentLocation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Location> aggregatedLocations = CollectionLiterals.<Location>newTreeSet(new Comparator<Location>() {
    public int compare(final Location a, final Location b) {
      return a.url.compareTo(b.url);
    }
  });
  
  @Property
  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Unit> units = CollectionLiterals.<Unit>newTreeSet(new Comparator<Unit>() {
    public int compare(final Unit a, final Unit b) {
      String _name = a.getName();
      String _name_1 = b.getName();
      return _name.compareTo(_name_1);
    }
  });
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("location (");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("parentLocationId=");
    Long _id = null;
    if (this.parentLocation!=null) {
      _id=this.parentLocation.id;
    }
    _builder.append(_id, "\t");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("id=");
    _builder.append(this.id, "\t");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("timestamp=");
    _builder.append(this.timestamp, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("url=");
    _builder.append(this.url, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("units (");
    _builder.newLine();
    {
      final Function1<Unit,Long> _function = new Function1<Unit,Long>() {
        public Long apply(final Unit it) {
          return Location.this.id;
        }
      };
      List<Unit> _sortBy = IterableExtensions.<Unit, Long>sortBy(this.units, _function);
      for(final Unit unit : _sortBy) {
        _builder.append("\t\t");
        String _string = unit.toString();
        _builder.append(_string, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("aggregatedLocations (");
    _builder.newLine();
    {
      final Function1<Location,Long> _function_1 = new Function1<Location,Long>() {
        public Long apply(final Location it) {
          return it.id;
        }
      };
      List<Location> _sortBy_1 = IterableExtensions.<Location, Long>sortBy(this.aggregatedLocations, _function_1);
      for(final Location aggregatedLocation : _sortBy_1) {
        _builder.append("\t\t");
        String _string_1 = aggregatedLocation.toString();
        _builder.append(_string_1, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append(")");
    _builder.newLine();
    return _builder.toString();
  }
  
  public Location() {
  }
  
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
