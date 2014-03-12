package de.abg.jreichert.activeanno.jpa.example;

import com.google.common.collect.Iterables;
import de.abg.jreichert.activeanno.jpa.example.Location;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class LocationManager {
  public Map<String,Long> getTimestamps(final Map<String,Long> timestampsToFilter) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field LocationLiterals is undefined for the type LocationManager"
      + "\nThe method or field url is undefined for the type LocationManager"
      + "\nThe method or field timestamp is undefined for the type LocationManager"
      + "\nAmbiguous feature call.\nThe methods\n\tvalueOf(String) in Long and\n\tvalueOf(long) in Long\nboth match."
      + "\nurl cannot be resolved");
  }
  
  private Set toLocationList(final List<?> result) {
    Iterable _filter = Iterables.<Object>filter(result, Location.class);
    return IterableExtensions.<Object>toSet(_filter);
  }
}
