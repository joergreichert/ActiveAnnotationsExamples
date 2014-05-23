package de.abg.jreichert.activeanno.jpa

import java.util.List
import org.hibernate.Session
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder.ConditionRoot
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder.ConditionProperty
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder

class CriteriaOperationExtensions {

	def static <T> List<T> find(Session session, Class<T> clazz, (ConditionRoot<T>) => ConditionRoot<T> criteriaRoot) {
		val extension findByCondition = new CustomJpaHibFindByConditionAccessImpl(clazz, session)
		criteriaRoot.apply(ConditionalCriteriaBuilder.criteriaFor(clazz)).buildSingle.addCondition
		performExecute
		getResult
	}
	
	def static <T> ConditionRoot<T> == (ConditionProperty<T> property, Object value) {
		property.eq(value)
	}

	def static <T> ConditionRoot<T> && (ConditionRoot<T> root, ConditionRoot<T> dummy) {
		root.and()
	}
	
	def static <T> ConditionProperty<T> => (ConditionRoot<T> root, org.sculptor.framework.domain.Property<T> property) {
		root.withProperty(property)
	}
}