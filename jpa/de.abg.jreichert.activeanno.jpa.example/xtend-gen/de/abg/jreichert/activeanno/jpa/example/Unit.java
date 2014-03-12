package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.example.Location;
import de.abg.jreichert.activeanno.jpa.example.Version;
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
public class Unit {
  public Unit(final Location location) {
    this.setLocation(location);
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
  
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location _location;
  
  public Location getLocation() {
    return this._location;
  }
  
  public void setLocation(final Location location) {
    this._location = location;
  }
  
  private String _name;
  
  public String getName() {
    return this._name;
  }
  
  public void setName(final String name) {
    this._name = name;
  }
  
  @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Version> _versions /* Skipped initializer because of errors */;
  
  public Set<Version> getVersions() {
    return this._versions;
  }
  
  public void setVersions(final Set<Version> versions) {
    this._versions = versions;
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("unit (");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("locationId=");
    Location _location = this.getLocation();
    Long _id = null;
    if (_location!=null) {
      _id=_location.getId();
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
    _builder.append("name=");
    String _name = this.getName();
    _builder.append(_name, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("versions (");
    _builder.newLine();
    {
      Set<Version> _versions = this.getVersions();
      final Function1<Object,Long> _function = new Function1<Object,Long>() {
        public Long apply(final Object it) {
          return Unit.this.getId();
        }
      };
      List _sortBy = IterableExtensions.<Object, Comparable>sortBy(_versions, _function);
      for(final Object version : _sortBy) {
        _builder.append("\t\t");
        String _string = version.toString();
        _builder.append(_string, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append(") ");
    _builder.newLine();
    _builder.append(")");
    _builder.newLine();
    return _builder.toString();
  }
}
