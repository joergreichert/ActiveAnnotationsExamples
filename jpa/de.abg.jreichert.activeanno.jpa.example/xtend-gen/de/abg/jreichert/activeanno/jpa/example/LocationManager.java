package de.abg.jreichert.activeanno.jpa.example;

import com.google.common.collect.Iterables;
import de.abg.jreichert.activeanno.jpa.CustomJpaHibFindByConditionAccessImpl;
import de.abg.jreichert.activeanno.jpa.example.Location;
import de.abg.jreichert.activeanno.jpa.example.LocationLiterals;
import de.abg.jreichert.activeanno.jpa.example.SessionManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.hibernate.Session;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.domain.Property;

@SuppressWarnings("all")
public class LocationManager {
  public Map<String,Long> getTimestamps(final Map<String,Long> timestampsToFilter) {
    HashMap<String,Long> _xblockexpression = null;
    {
      final HashMap<String,Long> timestamps = new HashMap<String, Long>();
      final Session session = SessionManager.currentSession();
      final CustomJpaHibFindByConditionAccessImpl<Location> findByCondition = new CustomJpaHibFindByConditionAccessImpl<Location>(Location.class, session);
      ConditionalCriteriaBuilder.ConditionRoot<Location> conditionalCriteriaRoot = ConditionalCriteriaBuilder.<Location>criteriaFor(Location.class);
      int _size = timestampsToFilter.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        Property<Location> _url = LocationLiterals.url();
        ConditionalCriteriaBuilder.ConditionProperty<Location> _withProperty = conditionalCriteriaRoot.withProperty(_url);
        Set<String> _keySet = timestampsToFilter.keySet();
        ConditionalCriteriaBuilder.ConditionRoot<Location> _in = _withProperty.in(_keySet);
        conditionalCriteriaRoot = _in;
        ConditionalCriteria _buildSingle = conditionalCriteriaRoot.buildSingle();
        findByCondition.addCondition(_buildSingle);
      }
      findByCondition.performExecute();
      List<Location> _result = findByCondition.getResult();
      final Set<Location> result = this.toLocationList(_result);
      final Procedure1<Location> _function = new Procedure1<Location>() {
        public void apply(final Location it) {
          String _url = it.getUrl();
          String _timestamp = it.getTimestamp();
          Long _valueOf = Long.valueOf(_timestamp);
          timestamps.put(_url, _valueOf);
        }
      };
      IterableExtensions.<Location>forEach(result, _function);
      _xblockexpression = timestamps;
    }
    return _xblockexpression;
  }
  
  private Set<Location> toLocationList(final List<?> result) {
    Iterable<Location> _filter = Iterables.<Location>filter(result, Location.class);
    return IterableExtensions.<Location>toSet(_filter);
  }
}
