package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.Entity;
import de.abg.jreichert.activeanno.jpa.Property;
import de.abg.jreichert.activeanno.jpa.example.Unit;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.eclipse.xtend2.lib.StringConcatenation;

@Entity
@javax.persistence.Entity
@SuppressWarnings("all")
public class Version {
  public Version(final Unit unit) {
    this.unit = unit;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Property
  private Long id = null;
  
  @Property
  @ManyToOne
  @JoinColumn(name = "unit_id")
  private Unit unit;
  
  @Property
  private String name;
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("version ( unitId=");
    Long _id = null;
    if (this.unit!=null) {
      _id=this.unit.getId();
    }
    _builder.append(_id, "");
    _builder.append(", id=");
    _builder.append(this.id, "");
    _builder.append(", name=");
    _builder.append(this.name, "");
    _builder.append(" )");
    return _builder.toString();
  }
  
  public Version() {
  }
  
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
