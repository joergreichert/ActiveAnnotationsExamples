package de.abg.jreichert.activeanno.jpa.example.internal;

import de.abg.jreichert.activeanno.jpa.Entity;
import de.abg.jreichert.activeanno.jpa.Property;
import de.abg.jreichert.activeanno.jpa.example.internal.Location;
import de.abg.jreichert.activeanno.jpa.example.internal.Version;
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
public class Unit {
  public Unit(final Location location) {
    this.location = location;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Property
  private Long id = null;
  
  @Property
  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;
  
  @Property
  private String name;
  
  @Property
  @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Sort
  private Set<Version> versions = CollectionLiterals.<Version>newTreeSet(new Comparator<Version>() {
    public int compare(final Version a, final Version b) {
      String _name = a.getName();
      String _name_1 = b.getName();
      return _name.compareTo(_name_1);
    }
  });
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("unit (");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("locationId=");
    Long _id = null;
    if (this.location!=null) {
      _id=this.location.getId();
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
    _builder.append("name=");
    _builder.append(this.name, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("versions (");
    _builder.newLine();
    {
      final Function1<Version, Long> _function = new Function1<Version, Long>() {
        public Long apply(final Version it) {
          return Unit.this.id;
        }
      };
      List<Version> _sortBy = IterableExtensions.<Version, Long>sortBy(this.versions, _function);
      for(final Version version : _sortBy) {
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
  
  public Unit() {
  }
  
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
