package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.common.AnnotationExtensions;
import de.abg.jreichert.activeanno.jpa.Entity;
import de.abg.jreichert.activeanno.jpa.EntityLiteral;
import de.abg.jreichert.activeanno.jpa.Property;
import javax.persistence.Id;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EntityLiteralProcessor extends AbstractClassProcessor {
  @Extension
  private AnnotationExtensions _annotationExtensions = new AnnotationExtensions();
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    String _literalsClassName = this.getLiteralsClassName(annotatedClass);
    context.registerClass(_literalsClassName);
    String _literalClassName = this.getLiteralClassName(annotatedClass);
    context.registerClass(_literalClassName);
    String _literalClassImplName = this.getLiteralClassImplName(annotatedClass);
    context.registerClass(_literalClassImplName);
  }
  
  public String getLiteralsClassName(final ClassDeclaration annotatedClass) {
    String _qualifiedName = annotatedClass.getQualifiedName();
    return (_qualifiedName + "Literals");
  }
  
  public String getLiteralClassName(final ClassDeclaration annotatedClass) {
    String _qualifiedName = annotatedClass.getQualifiedName();
    return (_qualifiedName + "Literal");
  }
  
  public String getLiteralClassImplName(final ClassDeclaration annotatedClass) {
    String _qualifiedName = annotatedClass.getQualifiedName();
    return (_qualifiedName + "LiteralImpl");
  }
  
  private MutableClassDeclaration findClassWithTypeParam(final String qualifiedName, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method addTypeParameter(String, TypeReference[]) is not applicable for the arguments (String)");
  }
  
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    String _literalClassImplName = this.getLiteralClassImplName(annotatedClass);
    final MutableClassDeclaration literalImplType = this.findClassWithTypeParam(_literalClassImplName, context);
    String _literalClassName = this.getLiteralClassName(annotatedClass);
    final MutableClassDeclaration literalType = this.findClassWithTypeParam(_literalClassName, context);
    String _literalsClassName = this.getLiteralsClassName(annotatedClass);
    final MutableClassDeclaration literalsType = context.findClass(_literalsClassName);
    this.addLiteralImplMembers(literalImplType, annotatedClass, context, "");
    this.addLiteralMembers(literalType, annotatedClass, context, "");
    this.addLiteralsMembers(literalsType, annotatedClass, context);
  }
  
  private void addLiteralImplMembers(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, @Extension final TransformationContext context, final String prefix) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field type is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Class, TypeReference[]) is not applicable for the arguments (Class<PropertiesCollection>)"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nisAnnotatedWithEntity cannot be resolved"
      + "\n|| cannot be resolved"
      + "\nisAnnotatedWithEntityLiteral cannot be resolved"
      + "\ntype cannot be resolved");
  }
  
  private MutableFieldDeclaration addSerialVersionUIDField(final MutableClassDeclaration classType, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field static is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field final is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field type is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field initializer is undefined for the type EntityLiteralProcessor");
  }
  
  private TypeReference owningClassTypeRef(final MutableTypeParameterDeclaration typeParameter, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (MutableTypeParameterDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addLiteralMembers(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, @Extension final TransformationContext context, final String prefix) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method addParameter is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(String, TypeReference[]) is not applicable for the arguments (String)"
      + "\nInvalid number of arguments. The method newTypeReference(String, TypeReference[]) is not applicable for the arguments (String)"
      + "\nType mismatch: cannot convert from String to MutableTypeParameterDeclaration"
      + "\nType mismatch: cannot convert from TypeReference to Object[]"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addLiteralsMembers(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nThe method type is undefined for the type EntityLiteralProcessor"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nType mismatch: cannot convert from Object to FieldDeclaration"
      + "\nisAnnotatedWithEntity cannot be resolved"
      + "\n|| cannot be resolved"
      + "\nisAnnotatedWithEntityLiteral cannot be resolved"
      + "\ntype cannot be resolved");
  }
  
  private void addSharedInstanceField(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field static is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field final is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field type is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field initializer is undefined for the type EntityLiteralProcessor"
      + "\nThe method toJavaCode is undefined for the type EntityLiteralProcessor"
      + "\nThe method toJavaCode is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (ClassDeclaration)"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (ClassDeclaration)"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (ClassDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addLiteralsMethod(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, final FieldDeclaration field, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field static is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field returnType is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (ClassDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addTypeLiteralsMethod(final MutableClassDeclaration outerClassType, final ClassDeclaration classType, final ClassDeclaration annotatedClass, final FieldDeclaration field, @Extension final TransformationContext context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field static is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field returnType is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (ClassDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addLiteralImplMethod(final MutableClassDeclaration classType, final FieldDeclaration field, @Extension final TransformationContext context, final MutableTypeParameterDeclaration typeParameter) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field returnType is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nThe method toJavaCode is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (MutableTypeParameterDeclaration)"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (MutableTypeParameterDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private void addTypeLiteralImplMethod(final MutableClassDeclaration outerClassType, final ClassDeclaration classType, final FieldDeclaration field, @Extension final TransformationContext context, final MutableTypeParameterDeclaration typeParameter) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field visibility is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field returnType is undefined for the type EntityLiteralProcessor"
      + "\nThe method or field body is undefined for the type EntityLiteralProcessor"
      + "\nThe method toJavaCode is undefined for the type EntityLiteralProcessor"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (MutableTypeParameterDeclaration)"
      + "\nInvalid number of arguments. The method newTypeReference(Type, TypeReference[]) is not applicable for the arguments (MutableTypeParameterDeclaration)"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]"
      + "\nType mismatch: cannot convert from TypeReference to TypeReference[]");
  }
  
  private boolean isAnnotatedWithProperty(final FieldDeclaration field, final TransformationContext context) {
    return this._annotationExtensions.isAnnotatedWithType(field, context, Property.class);
  }
  
  private boolean isAnnotatedWithId(final FieldDeclaration field, final TransformationContext context) {
    return this._annotationExtensions.isAnnotatedWithType(field, context, Id.class);
  }
  
  private boolean isAnnotatedWithEntity(final TypeReference typeRef, final TransformationContext context) {
    return this._annotationExtensions.isAnnotatedWithType(typeRef, context, Entity.class);
  }
  
  private boolean isAnnotatedWithEntityLiteral(final TypeReference typeRef, final TransformationContext context) {
    return this._annotationExtensions.isAnnotatedWithType(typeRef, context, EntityLiteral.class);
  }
}
