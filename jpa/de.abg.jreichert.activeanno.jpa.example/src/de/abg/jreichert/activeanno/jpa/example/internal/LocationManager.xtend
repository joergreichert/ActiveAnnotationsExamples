package de.abg.jreichert.activeanno.jpa.example.internal

import de.abg.jreichert.activeanno.jpa.CustomJpaHibFindByConditionAccessImpl
import java.util.List
import java.util.Set
import org.hibernate.Session
import org.hibernate.Transaction
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder.ConditionProperty
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder.ConditionRoot
import org.sculptor.framework.domain.Property

import static de.abg.jreichert.activeanno.jpa.example.internal.LocationLiterals.*
import static de.abg.jreichert.activeanno.jpa.example.internal.UnitLiterals.*
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
		val unitIds = session.find(Unit) [ it => name == unit && it => versions.name() == version ].map[id]
		val result = session.find(Location) [ (it => units.id).in(unitIds) ].toLocationList
		result.forEach[urls.add(url)]
		urls
	}
	
	def <T> List<T> find(Session session, Class<T> clazz, (ConditionRoot<T>) => ConditionRoot<T> criteriaRoot) {
		val extension findByCondition = new CustomJpaHibFindByConditionAccessImpl(clazz, session)
		criteriaRoot.apply(ConditionalCriteriaBuilder.criteriaFor(clazz)).buildSingle.addCondition
		performExecute
		getResult
	}
	
	def <T> ConditionRoot<T> operator_equals(ConditionProperty<T> property, Object value) {
		property.eq(value)
	}

	def <T> ConditionRoot<T> operator_and(ConditionRoot<T> root, ConditionRoot<T> dummy) {
		root.and()
	}
	
	def <T> ConditionProperty<T> operator_doubleArrow(ConditionRoot<T> root, Property<T> property) {
		root.withProperty(property)
	}

	private def toLocationList(List<?> result) {
		result.filter(Location).toSet
	}
}
