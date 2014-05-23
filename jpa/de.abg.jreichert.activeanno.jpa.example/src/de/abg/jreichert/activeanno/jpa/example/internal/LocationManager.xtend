package de.abg.jreichert.activeanno.jpa.example.internal

import java.util.List
import java.util.Set
import org.hibernate.Transaction

import static de.abg.jreichert.activeanno.jpa.example.internal.LocationLiterals.*
import static de.abg.jreichert.activeanno.jpa.example.internal.UnitLiterals.*

import static extension de.abg.jreichert.activeanno.jpa.CriteriaOperationExtensions.*

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

	private def toLocationList(List<?> result) {
		result.filter(Location).toSet
	}
}
