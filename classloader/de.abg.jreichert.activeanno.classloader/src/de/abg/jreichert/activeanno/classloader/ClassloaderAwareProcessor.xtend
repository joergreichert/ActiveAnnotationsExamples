package de.abg.jreichert.activeanno.classloader

import com.google.common.base.Optional
import de.abg.jreichert.activeanno.common.AnnotationExtensions
import de.abg.jreichert.activeanno.common.AnnotationSearch
import java.lang.annotation.ElementType
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.InterfaceDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.TypeReference

@Target(ElementType::FIELD)
@Active(typeof(ClassloaderAwareProcessor))
annotation ClassloaderAware {
   Class<?> expectedInterface
}

class ClassloaderAwareProcessor extends AbstractFieldProcessor {
   extension AnnotationExtensions = new AnnotationExtensions 

   override doTransform(MutableFieldDeclaration it, extension TransformationContext context) {
      if (type.isInferred) {
         addError('''Annotated field must declare explicitely its type!''')
         return
      }
      if (type.name !== string.name) {
         addError('''Annotated field must be of type string, but is «type.name»''')
         return
      }
      checkValidFieldValue(it, context)
   }
   
   private def checkValidFieldValue(MutableFieldDeclaration it, extension TransformationContext context) {
      val value = getFieldStringValue(context)
      if(value !== null) {
         switch (type : findTypeGlobally(value)) {
            case type == null:
               initializer.addError('''«value» cannot be found on classpath.''')
            case !(type instanceof ClassDeclaration):
               initializer.addError('''«value» is a «type.class.simpleName», but expected a class.''')
            default: {
               val classDecl = type as ClassDeclaration
               val expectedInterf = getExpectedInterfaceValueFromAnnotation(context)
               if (!classDecl.doesImplementInterface(expectedInterf)) {
                  initializer.addError(
                     '''The class «classDecl.simpleName» doesn't implement the interface «expectedInterf.simpleName».'''
                  )
               }
            }
         }
      }
   }
   
   private def getExpectedInterfaceValueFromAnnotation(MutableFieldDeclaration fieldDecl, extension TransformationContext context) {
      val annotation = findAnnotation(
         new AnnotationSearch => [
            it.type = fieldDecl
            it.context = context
            it.annotationClass = ClassloaderAware
         ]
      )
      annotation.getValue("expectedInterface") as TypeReference
   }

   private def getFieldStringValue(FieldDeclaration it, extension TransformationContext context) {
      val fieldValueOption = Optional.of(initializer?.toString)
      switch (fieldValueOption) {
         case !fieldValueOption.present:
            addError("There is no value assigned to this field.")
         case !(fieldValueOption.get.startsWith('"') && fieldValueOption.get.endsWith('"')):
            addError("Only directly assigned string values (= string literals) are supported.")
         default:
            fieldValueOption.get.substring(1, fieldValueOption.get.length - 1)
      }
   }

   private def boolean doesImplementInterface(ClassDeclaration it, TypeReference expectedInterf) {
      implementedInterfaces.exists[name.equals(expectedInterf.type.qualifiedName)] ||
         implementedInterfaces.exists[(type as InterfaceDeclaration).doesExtendInterface(expectedInterf)] ||
         (extendedClass !== null) && (extendedClass.type as ClassDeclaration).doesImplementInterface(expectedInterf)
   }

   private def boolean doesExtendInterface(InterfaceDeclaration it, TypeReference expectedInterf) {
      extendedInterfaces.exists[name.equals(expectedInterf.type.qualifiedName)]
   }
}
