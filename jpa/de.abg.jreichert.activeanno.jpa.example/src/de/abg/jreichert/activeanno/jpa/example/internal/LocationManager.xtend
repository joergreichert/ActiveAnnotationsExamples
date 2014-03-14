package de.abg.jreichert.activeanno.jpa.example.internal

import de.abg.jreichert.activeanno.jpa.CustomJpaHibFindByConditionAccessImpl
import java.util.List
import java.util.Set
import org.hibernate.Transaction
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder

class LocationManager {

	def getById(Long id) {
		val session = SessionManager::currentSession
		session.get(Location, id) as Location
	}

	def saveLocation(Location location) {
		val session = SessionManager::currentSession
		var Transaction tx = null
		var Object result = null
		try {
			tx = session.beginTransaction
			val id = session.save(location)
			tx.commit
			result = getById((id as Long))
		} catch (Exception e) {
			if(tx !== null) tx.rollback
			throw e;
		}
		(result as Location)
	}

	def deleteLocation(Location location) {
		val session = SessionManager::currentSession
		var Transaction tx = null
		try {
			tx = session.beginTransaction
			session.delete(location)
			tx.commit
		} catch (Exception e) {
			if(tx !== null) tx.rollback
			throw new RuntimeException(e);
		}
	}

	def Set<String> getLocationURLsContainingUnitWithVersion(String unit, String version) {
		val urls = <String>newHashSet
		val session = SessionManager::currentSession
		val unitFindByCondition = new CustomJpaHibFindByConditionAccessImpl(Unit, session)
		var unitCriteriaRoot = ConditionalCriteriaBuilder.criteriaFor(Unit)
		unitCriteriaRoot = unitCriteriaRoot.withProperty(
			UnitLiterals.name()).eq(unit).and().withProperty(UnitLiterals.versions().name()).eq(
			version)
		unitFindByCondition.addCondition(unitCriteriaRoot.buildSingle())
		unitFindByCondition.performExecute
		val unitIds = unitFindByCondition.getResult().map[id]
		val locationFindByCondition = new CustomJpaHibFindByConditionAccessImpl(Location, session)
		var locationCriteriaRoot = ConditionalCriteriaBuilder.criteriaFor(Location)
		locationCriteriaRoot = locationCriteriaRoot.withProperty(
			LocationLiterals.units().id()).in(unitIds)
		locationFindByCondition.addCondition(locationCriteriaRoot.buildSingle())
		locationFindByCondition.performExecute
		val result = toLocationList(locationFindByCondition.getResult())
		result.forEach[urls.add(url)]
		urls
	}

	private def toLocationList(List<?> result) {
		result.filter(Location).toSet
	}
}
