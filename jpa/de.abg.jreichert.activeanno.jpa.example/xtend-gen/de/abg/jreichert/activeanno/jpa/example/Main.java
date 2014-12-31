package de.abg.jreichert.activeanno.jpa.example;

import de.abg.jreichert.activeanno.jpa.example.internal.Location;
import de.abg.jreichert.activeanno.jpa.example.internal.LocationManager;
import de.abg.jreichert.activeanno.jpa.example.internal.Unit;
import de.abg.jreichert.activeanno.jpa.example.internal.Version;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Main {
  public static void main(final String[] args) {
    final Location location = Main.locationTestdata();
    final LocationManager locationManager = new LocationManager();
    final Location savedLocation = locationManager.saveLocation(location);
    final Set<String> urls = locationManager.getLocationURLsContainingUnitWithVersion("cde.feature", "0.0.1.201304242052");
    final Consumer<String> _function = new Consumer<String>() {
      public void accept(final String it) {
        System.err.println(it);
      }
    };
    urls.forEach(_function);
    locationManager.deleteLocation(savedLocation);
  }
  
  private static Location locationTestdata() {
    Location _location = new Location();
    final Procedure1<Location> _function = new Procedure1<Location>() {
      public void apply(final Location it) {
        it.setTimestamp("1367912476285");
        it.setUrl("file://testdata/updatesite/complex");
        it.setParentLocation(null);
        final Comparator<Location> _function = new Comparator<Location>() {
          public int compare(final Location a, final Location b) {
            String _url = a.getUrl();
            String _url_1 = b.getUrl();
            return _url.compareTo(_url_1);
          }
        };
        Location _location = new Location(it);
        final Procedure1<Location> _function_1 = new Procedure1<Location>() {
          public void apply(final Location it) {
            it.setTimestamp("1367932476293");
            it.setUrl("file://testdata/updatesite/complex/cdefgh");
            final Comparator<Unit> _function = new Comparator<Unit>() {
              public int compare(final Unit a, final Unit b) {
                String _name = a.getName();
                String _name_1 = b.getName();
                return _name.compareTo(_name_1);
              }
            };
            Unit _unit = new Unit(it);
            final Procedure1<Unit> _function_1 = new Procedure1<Unit>() {
              public void apply(final Unit it) {
                it.setName("cde.feature");
                final Comparator<Version> _function = new Comparator<Version>() {
                  public int compare(final Version a, final Version b) {
                    String _name = a.getName();
                    String _name_1 = b.getName();
                    return _name.compareTo(_name_1);
                  }
                };
                Version _version = new Version(it);
                final Procedure1<Version> _function_1 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.1.201304242052");
                  }
                };
                Version _doubleArrow = ObjectExtensions.<Version>operator_doubleArrow(_version, _function_1);
                Version _version_1 = new Version(it);
                final Procedure1<Version> _function_2 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.2.201305242052");
                  }
                };
                Version _doubleArrow_1 = ObjectExtensions.<Version>operator_doubleArrow(_version_1, _function_2);
                TreeSet<Version> _newTreeSet = CollectionLiterals.<Version>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
                it.setVersions(_newTreeSet);
              }
            };
            Unit _doubleArrow = ObjectExtensions.<Unit>operator_doubleArrow(_unit, _function_1);
            Unit _unit_1 = new Unit(it);
            final Procedure1<Unit> _function_2 = new Procedure1<Unit>() {
              public void apply(final Unit it) {
                it.setName("fgh.feature");
                final Comparator<Version> _function = new Comparator<Version>() {
                  public int compare(final Version a, final Version b) {
                    String _name = a.getName();
                    String _name_1 = b.getName();
                    return _name.compareTo(_name_1);
                  }
                };
                Version _version = new Version(it);
                final Procedure1<Version> _function_1 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.3.201306242052");
                  }
                };
                Version _doubleArrow = ObjectExtensions.<Version>operator_doubleArrow(_version, _function_1);
                Version _version_1 = new Version(it);
                final Procedure1<Version> _function_2 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.4.201307242052");
                  }
                };
                Version _doubleArrow_1 = ObjectExtensions.<Version>operator_doubleArrow(_version_1, _function_2);
                TreeSet<Version> _newTreeSet = CollectionLiterals.<Version>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
                it.setVersions(_newTreeSet);
              }
            };
            Unit _doubleArrow_1 = ObjectExtensions.<Unit>operator_doubleArrow(_unit_1, _function_2);
            TreeSet<Unit> _newTreeSet = CollectionLiterals.<Unit>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
            it.setUnits(_newTreeSet);
          }
        };
        Location _doubleArrow = ObjectExtensions.<Location>operator_doubleArrow(_location, _function_1);
        Location _location_1 = new Location(it);
        final Procedure1<Location> _function_2 = new Procedure1<Location>() {
          public void apply(final Location it) {
            it.setTimestamp("1367922476291");
            it.setUrl("file://testdata/updatesite/complex/ijklmn");
            final Comparator<Unit> _function = new Comparator<Unit>() {
              public int compare(final Unit a, final Unit b) {
                String _name = a.getName();
                String _name_1 = b.getName();
                return _name.compareTo(_name_1);
              }
            };
            Unit _unit = new Unit(it);
            final Procedure1<Unit> _function_1 = new Procedure1<Unit>() {
              public void apply(final Unit it) {
                it.setName("ijk.feature");
                final Comparator<Version> _function = new Comparator<Version>() {
                  public int compare(final Version a, final Version b) {
                    String _name = a.getName();
                    String _name_1 = b.getName();
                    return _name.compareTo(_name_1);
                  }
                };
                Version _version = new Version(it);
                final Procedure1<Version> _function_1 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.5.201304242052");
                  }
                };
                Version _doubleArrow = ObjectExtensions.<Version>operator_doubleArrow(_version, _function_1);
                Version _version_1 = new Version(it);
                final Procedure1<Version> _function_2 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.6.201305242052");
                  }
                };
                Version _doubleArrow_1 = ObjectExtensions.<Version>operator_doubleArrow(_version_1, _function_2);
                TreeSet<Version> _newTreeSet = CollectionLiterals.<Version>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
                it.setVersions(_newTreeSet);
              }
            };
            Unit _doubleArrow = ObjectExtensions.<Unit>operator_doubleArrow(_unit, _function_1);
            Unit _unit_1 = new Unit(it);
            final Procedure1<Unit> _function_2 = new Procedure1<Unit>() {
              public void apply(final Unit it) {
                it.setName("lmn.feature");
                final Comparator<Version> _function = new Comparator<Version>() {
                  public int compare(final Version a, final Version b) {
                    String _name = a.getName();
                    String _name_1 = b.getName();
                    return _name.compareTo(_name_1);
                  }
                };
                Version _version = new Version(it);
                final Procedure1<Version> _function_1 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.7.201306242052");
                  }
                };
                Version _doubleArrow = ObjectExtensions.<Version>operator_doubleArrow(_version, _function_1);
                Version _version_1 = new Version(it);
                final Procedure1<Version> _function_2 = new Procedure1<Version>() {
                  public void apply(final Version it) {
                    it.setName("0.0.8.201307242052");
                  }
                };
                Version _doubleArrow_1 = ObjectExtensions.<Version>operator_doubleArrow(_version_1, _function_2);
                TreeSet<Version> _newTreeSet = CollectionLiterals.<Version>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
                it.setVersions(_newTreeSet);
              }
            };
            Unit _doubleArrow_1 = ObjectExtensions.<Unit>operator_doubleArrow(_unit_1, _function_2);
            TreeSet<Unit> _newTreeSet = CollectionLiterals.<Unit>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
            it.setUnits(_newTreeSet);
          }
        };
        Location _doubleArrow_1 = ObjectExtensions.<Location>operator_doubleArrow(_location_1, _function_2);
        TreeSet<Location> _newTreeSet = CollectionLiterals.<Location>newTreeSet(_function, _doubleArrow, _doubleArrow_1);
        it.setAggregatedLocations(_newTreeSet);
      }
    };
    return ObjectExtensions.<Location>operator_doubleArrow(_location, _function);
  }
}
