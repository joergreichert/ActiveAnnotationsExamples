package de.abg.jreichert.activeanno.testsuite

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import java.util.List
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type
import org.eclipse.xtend.lib.macro.file.Path
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@Target(ElementType::TYPE)
@Active(typeof(TestSuiteProcessor))
annotation TestSuite {
	boolean includeSubPackages = false
	String matchPattern = "*"
}

class TestSuiteProcessor extends AbstractClassProcessor {

	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		val runWithAnnotation = annotatedClass.addAnnotation(RunWith.newTypeReference.type)
		runWithAnnotation.setClassValue("value", Suite.newTypeReference)
		val suiteClassesAnnotation = annotatedClass.addAnnotation(SuiteClasses.newTypeReference.type)
		val compilationUnit = annotatedClass.compilationUnit
		suiteClassesAnnotation.setClassValue("value", Object.newTypeReference)
		val src = compilationUnit.filePath.sourceFolder
		val classes = <Type>newArrayList
		traverse(src, src, classes, "", context)
		suiteClassesAnnotation.setClassValue("value", classes.map[newTypeReference])
//		val testSuiteAnnotation = annotatedClass.getTestSuiteAnnotation(context)
	}
	
	def void traverse(Path source, Path path, List<Type> classes, String packageName, extension TransformationContext context) {
		for(child : path.getChildren) {
			if(child.file) {
				val qName = child.relativize(source).segments.join(".").replace("." + child.fileExtension, "")
				if(qName.endsWith("Test")) {
					val foundType = context.findTypeGlobally(qName)
					if(foundType !== null) {
						classes.add(foundType) 
					} 
				}
			} else {
				val newPackageName = packageName + "." child.lastSegment
				traverse(source, child, classes, newPackageName, context)
			}
		}
	}

//	def private getTestSuiteAnnotation(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
//		annotatedClass.findAnnotation(TestSuite.newTypeReference.type)
//	}

//	def private getTestSuiteAnnotationPropertyValue(MutableAnnotationReference nlsAnnotation,
//		extension TransformationContext context) {
//		val value = nlsAnnotation.getValue('includeSubPackages') as String
//		if (value.nullOrEmpty) {
//			nlsAnnotation.addError("@TestSuite requires non empty includeSubPackages property value.")
//		}
//		value
//	}
}
