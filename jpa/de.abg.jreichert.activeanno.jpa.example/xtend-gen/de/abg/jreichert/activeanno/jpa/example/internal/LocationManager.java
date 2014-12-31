package de.abg.jreichert.activeanno.jpa.example.internal;

import com.google.common.collect.Iterables;
import de.abg.jreichert.activeanno.jpa.CriteriaOperationExtensions;
import de.abg.jreichert.activeanno.jpa.example.internal.Location;
import de.abg.jreichert.activeanno.jpa.example.internal.LocationLiterals;
import de.abg.jreichert.activeanno.jpa.example.internal.SessionManager;
import de.abg.jreichert.activeanno.jpa.example.internal.Unit;
import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiteral;
import de.abg.jreichert.activeanno.jpa.example.internal.UnitLiterals;
import de.abg.jreichert.activeanno.jpa.example.internal.VersionLiteral;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class LocationManager {
  public Location getById(final Long id) {
    Location _xblockexpression = null;
    {
      final Session session = SessionManager.currentSession();
      Object _get = session.get(Location.class, id);
      _xblockexpression = ((Location) _get);
    }
    return _xblockexpression;
  }
  
  public Location saveLocation(final Location location) {
    try {
      Location _xblockexpression = null;
      {
        final Session session = SessionManager.currentSession();
        Transaction tx = null;
        Object result = null;
        try {
          Transaction _beginTransaction = session.beginTransaction();
          tx = _beginTransaction;
          final Serializable id = session.save(location);
          tx.commit();
          Location _byId = this.getById(((Long) id));
          result = _byId;
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception e = (Exception)_t;
            boolean _tripleNotEquals = (tx != null);
            if (_tripleNotEquals) {
              tx.rollback();
            }
            throw e;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        _xblockexpression = ((Location) result);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void deleteLocation(final Location location) {
    final Session session = SessionManager.currentSession();
    Transaction tx = null;
    try {
      Transaction _beginTransaction = session.beginTransaction();
      tx = _beginTransaction;
      session.delete(location);
      tx.commit();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        boolean _tripleNotEquals = (tx != null);
        if (_tripleNotEquals) {
          tx.rollback();
        }
        throw new RuntimeException(e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public Set<String> getLocationURLsContainingUnitWithVersion(final String unit, final String version) {
    HashSet<String> _xblockexpression = null;
    {
      final HashSet<String> urls = CollectionLiterals.<String>newHashSet();
      final Session session = SessionManager.currentSession();
      final Function1<ConditionalCriteriaBuilder.ConditionRoot<Unit>, ConditionalCriteriaBuilder.ConditionRoot<Unit>> _function = new Function1<ConditionalCriteriaBuilder.ConditionRoot<Unit>, ConditionalCriteriaBuilder.ConditionRoot<Unit>>() {
        public ConditionalCriteriaBuilder.ConditionRoot<Unit> apply(final ConditionalCriteriaBuilder.ConditionRoot<Unit> it) {
          Property<Unit> _name = UnitLiterals.name();
          ConditionalCriteriaBuilder.ConditionProperty<Unit> _doubleArrow = CriteriaOperationExtensions.<Unit>operator_doubleArrow(it, _name);
          ConditionalCriteriaBuilder.ConditionRoot<Unit> _equals = CriteriaOperationExtensions.<Unit>operator_equals(_doubleArrow, unit);
          VersionLiteral<Unit> _versions = UnitLiterals.versions();
          Property<Unit> _name_1 = _versions.name();
          ConditionalCriteriaBuilder.ConditionProperty<Unit> _doubleArrow_1 = CriteriaOperationExtensions.<Unit>operator_doubleArrow(it, _name_1);
          ConditionalCriteriaBuilder.ConditionRoot<Unit> _equals_1 = CriteriaOperationExtensions.<Unit>operator_equals(_doubleArrow_1, version);
          return CriteriaOperationExtensions.<Unit>operator_and(_equals, _equals_1);
        }
      };
      List<Unit> _find = CriteriaOperationExtensions.<Unit>find(session, Unit.class, _function);
      final Function1<Unit, Long> _function_1 = new Function1<Unit, Long>() {
        public Long apply(final Unit it) {
          return it.getId();
        }
      };
      final List<Long> unitIds = ListExtensions.<Unit, Long>map(_find, _function_1);
      final Function1<ConditionalCriteriaBuilder.ConditionRoot<Location>, ConditionalCriteriaBuilder.ConditionRoot<Location>> _function_2 = new Function1<ConditionalCriteriaBuilder.ConditionRoot<Location>, ConditionalCriteriaBuilder.ConditionRoot<Location>>() {
        public ConditionalCriteriaBuilder.ConditionRoot<Location> apply(final ConditionalCriteriaBuilder.ConditionRoot<Location> it) {
          UnitLiteral<Location> _units = LocationLiterals.units();
          Property<Location> _id = _units.id();
          ConditionalCriteriaBuilder.ConditionProperty<Location> _doubleArrow = CriteriaOperationExtensions.<Location>operator_doubleArrow(it, _id);
          return _doubleArrow.in(unitIds);
        }
      };
      List<Location> _find_1 = CriteriaOperationExtensions.<Location>find(session, Location.class, _function_2);
      final Set<Location> result = this.toLocationList(_find_1);
      final Consumer<Location> _function_3 = new Consumer<Location>() {
        public void accept(final Location it) {
          String _url = it.getUrl();
          urls.add(_url);
        }
      };
      result.forEach(_function_3);
      _xblockexpression = urls;
    }
    return _xblockexpression;
  }
  
  private Set<Location> toLocationList(final List<?> result) {
    Iterable<Location> _filter = Iterables.<Location>filter(result, Location.class);
    return IterableExtensions.<Location>toSet(_filter);
  }
}
