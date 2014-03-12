package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.example.Unit;
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
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.hibernate.annotations.Sort;

/* @Entity
 */@SuppressWarnings("all")
public class Location {
  public Location(final Location parentLocation) {
    this.setParentLocation(parentLocation);
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long _id = null;
  
  public Long getId() {
    return this._id;
  }
  
  public void setId(final Long id) {
    this._id = id;
  }
  
  private String _timestamp = null;
  
  public String getTimestamp() {
    return this._timestamp;
  }
  
  public void setTimestamp(final String timestamp) {
    this._timestamp = timestamp;
  }
  
  private String _url;
  
  public String getUrl() {
    return this._url;
  }
  
  public void setUrl(final String url) {
    this._url = url;
  }
  
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location _parentLocation;
  
  public Location getParentLocation() {
    return this._parentLocation;
  }
  
  public void setParentLocation(final Location parentLocation) {
    this._parentLocation = parentLocation;
  }
  
  @OneToMany(mappedBy = "parentLocation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Location> _aggregatedLocations /* Skipped initializer because of errors */;
  
  public Set<Location> getAggregatedLocations() {
    return this._aggregatedLocations;
  }
  
  public void setAggregatedLocations(final Set<Location> aggregatedLocations) {
    this._aggregatedLocations = aggregatedLocations;
  }
  
  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Unit> _units /* Skipped initializer because of errors */;
  
  public Set<Unit> getUnits() {
    return this._units;
  }
  
  public void setUnits(final Set<Unit> units) {
    this._units = units;
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("location (");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("parentLocationId=");
    Location _parentLocation = this.getParentLocation();
    Long _id = null;
    if (_parentLocation!=null) {
      _id=_parentLocation.getId();
    }
    _builder.append(_id, "\t");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("id=");
    Long _id_1 = this.getId();
    _builder.append(_id_1, "\t");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("timestamp=");
    String _timestamp = this.getTimestamp();
    _builder.append(_timestamp, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("url=");
    String _url = this.getUrl();
    _builder.append(_url, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("units (");
    _builder.newLine();
    {
      Set<Unit> _units = this.getUnits();
      final Function1<Object,Long> _function = new Function1<Object,Long>() {
        public Long apply(final Object it) {
          return Location.this.getId();
        }
      };
      List _sortBy = IterableExtensions.<Object, Comparable>sortBy(_units, _function);
      for(final Object unit : _sortBy) {
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
      Set<Location> _aggregatedLocations = this.getAggregatedLocations();
      final Function1<Object,Long> _function_1 = new Function1<Object,Long>() {
        public Long apply(final Object it) {
          return Location.this.getId();
        }
      };
      List _sortBy_1 = IterableExtensions.<Object, Comparable>sortBy(_aggregatedLocations, _function_1);
      for(final Object aggregatedLocation : _sortBy_1) {
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
}
