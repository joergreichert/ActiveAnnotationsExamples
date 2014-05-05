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
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference

@Target(ElementType::TYPE)
@Active(typeof(TestSuiteProcessor))
annotation TestSuite {
	boolean includeSubPackages = false
	String postfixPattern = "Test"
}

class TestSuiteProcessor extends AbstractClassProcessor {

	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		val runWithAnnotation = annotatedClass.addAnnotation(RunWith.newTypeReference.type)
		runWithAnnotation.setClassValue("value", Suite.newTypeReference)
		val suiteClassesAnnotation = annotatedClass.addAnnotation(SuiteClasses.newTypeReference.type)
		val compilationUnit = annotatedClass.compilationUnit
		suiteClassesAnnotation.setClassValue("value", Object.newTypeReference)
		val src = compilationUnit.filePath.sourceFolder
		val testSuiteAnnotation = annotatedClass.annotations.filter[
			annotationTypeDeclaration.qualifiedName == TestSuite.newTypeReference.type.qualifiedName
		].head
		val value = testSuiteAnnotation.getValue('postfixPattern') as String
		val postfixPattern = if(value.nullOrEmpty) "Test" else value
		val classes = <Type>newArrayList
		traverse(testSuiteAnnotation, src, src, classes, "", postfixPattern, context)
		suiteClassesAnnotation.setClassValue("value", classes.map[newTypeReference])
	}
	
	def void traverse(MutableAnnotationReference testSuiteAnnotation, Path source, Path path, List<Type> classes, String packageName, String postfixPattern, extension TransformationContext context) {
		for(child : path.getChildren) {
			if(child.file) {
				val qName = child.relativize(source).segments.join(".").replace("." + child.fileExtension, "")
				if(qName.endsWith(postfixPattern)) {
					val foundType = context.findTypeGlobally(qName)
					if(foundType !== null) {
						classes.add(foundType) 
					} 
				}
			} else {
				val newPackageName = packageName + "." child.lastSegment
				traverse(testSuiteAnnotation, source, child, classes, newPackageName, postfixPattern, context)
			}
		}
	}
}
