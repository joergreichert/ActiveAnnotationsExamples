package de.abg.jreichert.activeanno.beanfx

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import javafx.beans.property.StringProperty
import javafx.beans.property.SimpleStringProperty

@Target(ElementType.TYPE)
@Active(FXBeanProcessor)
annotation FXBean {
	
}

class FXBeanProcessor extends AbstractClassProcessor {
	
	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		
		for(field : annotatedClass.declaredFields) {
			annotatedClass.addField(field.simpleName.toFirstLower + "Property") [
				type = StringProperty.newTypeReference
				initializer = [''' new «toJavaCode(SimpleStringProperty.newTypeReference)»(this,"«field.simpleName»");''']
			]
			annotatedClass.addMethod("get" + field.simpleName.toFirstUpper) [
				returnType = string
				body = ['''return this.«field.simpleName»Property.get();''']
			]
			annotatedClass.addMethod("set" + field.simpleName.toFirstUpper) [
				addParameter(field.simpleName.toFirstLower + "2", string)
				body = ['''this.«field.simpleName»Property.set(«field.simpleName»2);''']
			]
			annotatedClass.addMethod(field.simpleName.toFirstLower + "Property") [
				returnType = StringProperty.newTypeReference
				body = ['''return this.«field.simpleName»Property;''']
			]
			field.remove
		}
	}
}