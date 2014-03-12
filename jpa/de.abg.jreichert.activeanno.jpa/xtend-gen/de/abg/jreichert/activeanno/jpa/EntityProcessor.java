package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.jpa.EntityLiteralProcessor;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EntityProcessor extends AbstractClassProcessor {
  @Extension
  private /* AnnotationExtensions */Object _annotationExtensions /* Skipped initializer because of errors */;
  
  private EntityLiteralProcessor entityLiteralProcessor;
  
  public EntityProcessor() {
    EntityLiteralProcessor _entityLiteralProcessor = new EntityLiteralProcessor();
    this.entityLiteralProcessor = _entityLiteralProcessor;
  }
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    this.entityLiteralProcessor.doRegisterGlobals(annotatedClass, context);
  }
  
  public void doTransform(final MutableClassDeclaration clazz, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field parameters is undefined for the type EntityProcessor"
      + "\nThe method findAnnotationWithValue is undefined for the type EntityProcessor"
      + "\nAnnotationValueSearch cannot be resolved."
      + "\nThe method or field annotationSearch is undefined for the type EntityProcessor"
      + "\nAnnotationSearch cannot be resolved."
      + "\nThe method or field type is undefined for the type EntityProcessor"
      + "\nThe method or field context is undefined for the type EntityProcessor"
      + "\nThe method or field annotationClass is undefined for the type EntityProcessor"
      + "\nThe method or field value is undefined for the type EntityProcessor"
      + "\nThe method or field valueType is undefined for the type EntityProcessor"
      + "\nThe method or field annotations is undefined for the type EntityProcessor"
      + "\nThe method annotationTypeDeclaration is undefined for the type EntityProcessor"
      + "\nThe method addAnnotation is undefined for the type EntityProcessor"
      + "\nThe method or field type is undefined for the type EntityProcessor"
      + "\nThe method or field initializer is undefined for the type EntityProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Class, TypeReference[]) is not applicable for the arguments (Class<Entity>)"
      + "\nInvalid number of arguments. The method newTypeReference(Class, TypeReference[]) is not applicable for the arguments (Class<Id>)"
      + "\nInvalid number of arguments. The method newTypeReference(Class, TypeReference[]) is not applicable for the arguments (Class<Long>)"
      + "\nempty cannot be resolved"
      + "\n=> cannot be resolved"
      + "\n=> cannot be resolved"
      + "\nnullOrEmpty cannot be resolved"
      + "\n! cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nexists cannot be resolved"
      + "\nqualifiedName cannot be resolved"
      + "\n== cannot be resolved");
  }
}
