package de.abg.jreichert.activeanno.jpa.example

import de.abg.jreichert.activeanno.jpa.CustomJpaHibFindByConditionAccessImpl
import java.util.HashMap
import java.util.List
import java.util.Map
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder

class LocationManager {

	def Map<String, Long> getTimestamps(Map<String, Long> timestampsToFilter) {
		val timestamps = new HashMap<String, Long>()
		val session = SessionManager::currentSession
		val findByCondition = new CustomJpaHibFindByConditionAccessImpl(Location, session)
		var conditionalCriteriaRoot = ConditionalCriteriaBuilder.criteriaFor(Location)
		if (timestampsToFilter.size > 0) {
			conditionalCriteriaRoot = conditionalCriteriaRoot.withProperty(LocationLiterals.url()).in(
				timestampsToFilter.keySet)
			findByCondition.addCondition(conditionalCriteriaRoot.buildSingle())
		}
		findByCondition.performExecute
		val result = toLocationList(findByCondition.getResult())
		result.forEach[timestamps.put(url, Long::valueOf(timestamp))]
		timestamps
	}

	private def toLocationList(List<?> result) {
		result.filter(Location).toSet
	}
}
