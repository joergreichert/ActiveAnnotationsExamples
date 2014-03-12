package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.example.Unit;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.eclipse.xtend2.lib.StringConcatenation;

/* @Entity
 */@SuppressWarnings("all")
public class Version {
  public Version(final Unit unit) {
    this.setUnit(unit);
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
  @JoinColumn(name = "unit_id")
  private Unit _unit;
  
  public Unit getUnit() {
    return this._unit;
  }
  
  public void setUnit(final Unit unit) {
    this._unit = unit;
  }
  
  private String _name;
  
  public String getName() {
    return this._name;
  }
  
  public void setName(final String name) {
    this._name = name;
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("version ( unitId=");
    Unit _unit = this.getUnit();
    Long _id = null;
    if (_unit!=null) {
      _id=_unit.getId();
    }
    _builder.append(_id, "");
    _builder.append(", id=");
    Long _id_1 = this.getId();
    _builder.append(_id_1, "");
    _builder.append(", name=");
    String _name = this.getName();
    _builder.append(_name, "");
    _builder.append(" )");
    return _builder.toString();
  }
}
