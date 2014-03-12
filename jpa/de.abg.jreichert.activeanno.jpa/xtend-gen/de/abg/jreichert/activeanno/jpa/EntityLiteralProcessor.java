package de.abg.jreichert.activeanno.jpa;

import de.abg.jreichert.activeanno.common.AnnotationExtensions;
import de.abg.jreichert.activeanno.jpa.Entity;
import de.abg.jreichert.activeanno.jpa.EntityLiteral;
import java.util.ArrayList;
import javax.persistence.Id;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

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
    MutableClassDeclaration _xblockexpression = null;
    {
      final MutableClassDeclaration mutableClass = context.findClass(qualifiedName);
      Iterable<? extends MutableTypeParameterDeclaration> _typeParameters = mutableClass.getTypeParameters();
      int _size = IterableExtensions.size(_typeParameters);
      boolean _equals = (_size == 0);
      if (_equals) {
        mutableClass.addTypeParameter("T");
      }
      _xblockexpression = mutableClass;
    }
    return _xblockexpression;
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
    Iterable<? extends MutableTypeParameterDeclaration> _typeParameters = classType.getTypeParameters();
    final MutableTypeParameterDeclaration typeParameter = IterableExtensions.head(_typeParameters);
    TypeReference _newTypeReference = context.newTypeReference(PropertiesCollection.class);
    classType.setExtendedClass(_newTypeReference);
    this.addSerialVersionUIDField(classType, context);
    final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        TypeReference _owningClassTypeRef = EntityLiteralProcessor.this.owningClassTypeRef(typeParameter, context);
        it.setType(_owningClassTypeRef);
      }
    };
    classType.addField("owningClass", _function);
    final Procedure1<MutableConstructorDeclaration> _function_1 = new Procedure1<MutableConstructorDeclaration>() {
      public void apply(final MutableConstructorDeclaration it) {
        TypeReference _owningClassTypeRef = EntityLiteralProcessor.this.owningClassTypeRef(typeParameter, context);
        it.addParameter("owningClass", _owningClassTypeRef);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("super(null);");
            _builder.newLine();
            _builder.append("this.owningClass = owningClass;");
            _builder.newLine();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addConstructor(_function_1);
    final Procedure1<MutableConstructorDeclaration> _function_2 = new Procedure1<MutableConstructorDeclaration>() {
      public void apply(final MutableConstructorDeclaration it) {
        TypeReference _string = context.getString();
        it.addParameter("parentPath", _string);
        TypeReference _string_1 = context.getString();
        it.addParameter("additionalPath", _string_1);
        TypeReference _owningClassTypeRef = EntityLiteralProcessor.this.owningClassTypeRef(typeParameter, context);
        it.addParameter("owningClass", _owningClassTypeRef);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("super(parentPath, additionalPath);");
            _builder.newLine();
            _builder.append("this.owningClass = owningClass;");
            _builder.newLine();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addConstructor(_function_2);
    Iterable<? extends FieldDeclaration> _declaredFields = annotatedClass.getDeclaredFields();
    for (final FieldDeclaration field : _declaredFields) {
      boolean _or = false;
      boolean _isAnnotatedWithProperty = this.isAnnotatedWithProperty(field, context);
      if (_isAnnotatedWithProperty) {
        _or = true;
      } else {
        boolean _isAnnotatedWithId = this.isAnnotatedWithId(field, context);
        _or = _isAnnotatedWithId;
      }
      if (_or) {
        boolean _or_1 = false;
        TypeReference _type = field.getType();
        boolean _isAnnotatedWithEntity = this.isAnnotatedWithEntity(_type, context);
        if (_isAnnotatedWithEntity) {
          _or_1 = true;
        } else {
          TypeReference _type_1 = field.getType();
          boolean _isAnnotatedWithEntityLiteral = this.isAnnotatedWithEntityLiteral(_type_1, context);
          _or_1 = _isAnnotatedWithEntityLiteral;
        }
        if (_or_1) {
          TypeReference _type_2 = field.getType();
          Type _type_3 = _type_2.getType();
          this.addTypeLiteralImplMethod(classType, ((ClassDeclaration) _type_3), field, context, typeParameter);
        } else {
          this.addLiteralImplMethod(classType, field, context, typeParameter);
        }
      }
    }
  }
  
  private MutableFieldDeclaration addSerialVersionUIDField(final MutableClassDeclaration classType, @Extension final TransformationContext context) {
    final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        it.setStatic(true);
        it.setFinal(true);
        TypeReference _primitiveLong = context.getPrimitiveLong();
        it.setType(_primitiveLong);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            return "1L";
          }
        };
        it.setInitializer(_function);
      }
    };
    return classType.addField("serialVersionUID", _function);
  }
  
  private TypeReference owningClassTypeRef(final MutableTypeParameterDeclaration typeParameter, @Extension final TransformationContext context) {
    TypeReference _newTypeReference = context.newTypeReference(typeParameter);
    return context.newTypeReference(Class.class, _newTypeReference);
  }
  
  private void addLiteralMembers(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, @Extension final TransformationContext context, final String prefix) {
    Iterable<? extends MutableTypeParameterDeclaration> _typeParameters = classType.getTypeParameters();
    final MutableTypeParameterDeclaration typeParameter = IterableExtensions.head(_typeParameters);
    String _literalClassImplName = this.getLiteralClassImplName(annotatedClass);
    MutableClassDeclaration _findClassWithTypeParam = this.findClassWithTypeParam(_literalClassImplName, context);
    TypeReference _newTypeReference = context.newTypeReference(typeParameter);
    TypeReference _newTypeReference_1 = context.newTypeReference(_findClassWithTypeParam, _newTypeReference);
    classType.setExtendedClass(_newTypeReference_1);
    TypeReference _newTypeReference_2 = context.newTypeReference(typeParameter);
    TypeReference _newTypeReference_3 = context.newTypeReference(Property.class, _newTypeReference_2);
    ArrayList<TypeReference> _newArrayList = CollectionLiterals.<TypeReference>newArrayList(_newTypeReference_3);
    classType.setImplementedInterfaces(_newArrayList);
    this.addSerialVersionUIDField(classType, context);
    final Procedure1<MutableConstructorDeclaration> _function = new Procedure1<MutableConstructorDeclaration>() {
      public void apply(final MutableConstructorDeclaration it) {
        TypeReference _string = context.getString();
        it.addParameter("parentPath", _string);
        TypeReference _string_1 = context.getString();
        it.addParameter("additionalPath", _string_1);
        TypeReference _owningClassTypeRef = EntityLiteralProcessor.this.owningClassTypeRef(typeParameter, context);
        it.addParameter("owningClass", _owningClassTypeRef);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("super(parentPath, additionalPath, owningClass);");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addConstructor(_function);
  }
  
  private void addLiteralsMembers(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, final TransformationContext context) {
    this.addSharedInstanceField(classType, annotatedClass, context);
    final Procedure1<MutableConstructorDeclaration> _function = new Procedure1<MutableConstructorDeclaration>() {
      public void apply(final MutableConstructorDeclaration it) {
        it.setVisibility(Visibility.PRIVATE);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addConstructor(_function);
    Iterable<? extends FieldDeclaration> _declaredFields = annotatedClass.getDeclaredFields();
    for (final FieldDeclaration field : _declaredFields) {
      boolean _or = false;
      boolean _isAnnotatedWithProperty = this.isAnnotatedWithProperty(field, context);
      if (_isAnnotatedWithProperty) {
        _or = true;
      } else {
        boolean _isAnnotatedWithId = this.isAnnotatedWithId(field, context);
        _or = _isAnnotatedWithId;
      }
      if (_or) {
        boolean _or_1 = false;
        TypeReference _type = field.getType();
        boolean _isAnnotatedWithEntity = this.isAnnotatedWithEntity(_type, context);
        if (_isAnnotatedWithEntity) {
          _or_1 = true;
        } else {
          TypeReference _type_1 = field.getType();
          boolean _isAnnotatedWithEntityLiteral = this.isAnnotatedWithEntityLiteral(_type_1, context);
          _or_1 = _isAnnotatedWithEntityLiteral;
        }
        if (_or_1) {
          TypeReference _type_2 = field.getType();
          Type _type_3 = _type_2.getType();
          this.addTypeLiteralsMethod(classType, ((ClassDeclaration) _type_3), annotatedClass, field, context);
        } else {
          this.addLiteralsMethod(classType, annotatedClass, field, context);
        }
      }
    }
  }
  
  private void addSharedInstanceField(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        it.setVisibility(Visibility.PRIVATE);
        it.setStatic(true);
        it.setFinal(true);
        String _literalClassImplName = EntityLiteralProcessor.this.getLiteralClassImplName(annotatedClass);
        MutableClassDeclaration _findClassWithTypeParam = EntityLiteralProcessor.this.findClassWithTypeParam(_literalClassImplName, context);
        TypeReference _newTypeReference = context.newTypeReference(annotatedClass);
        TypeReference _newTypeReference_1 = context.newTypeReference(_findClassWithTypeParam, _newTypeReference);
        it.setType(_newTypeReference_1);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("new ");
            String _literalClassImplName = EntityLiteralProcessor.this.getLiteralClassImplName(annotatedClass);
            MutableClassDeclaration _findClass = context.findClass(_literalClassImplName);
            TypeReference _newTypeReference = context.newTypeReference(annotatedClass);
            TypeReference _newTypeReference_1 = context.newTypeReference(_findClass, _newTypeReference);
            String _javaCode = it.toJavaCode(_newTypeReference_1);
            _builder.append(_javaCode, "");
            _builder.append("(");
            TypeReference _newTypeReference_2 = context.newTypeReference(annotatedClass);
            String _javaCode_1 = it.toJavaCode(_newTypeReference_2);
            _builder.append(_javaCode_1, "");
            _builder.append(".class);");
            return _builder;
          }
        };
        it.setInitializer(_function);
      }
    };
    classType.addField("sharedInstance", _function);
  }
  
  private void addLiteralsMethod(final MutableClassDeclaration classType, final ClassDeclaration annotatedClass, final FieldDeclaration field, @Extension final TransformationContext context) {
    String _simpleName = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PUBLIC);
        it.setStatic(true);
        TypeReference _newTypeReference = context.newTypeReference(annotatedClass);
        TypeReference _newTypeReference_1 = context.newTypeReference(Property.class, _newTypeReference);
        it.setReturnType(_newTypeReference_1);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return sharedInstance.");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("();");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addMethod(_simpleName, _function);
  }
  
  private void addTypeLiteralsMethod(final MutableClassDeclaration outerClassType, final ClassDeclaration classType, final ClassDeclaration annotatedClass, final FieldDeclaration field, @Extension final TransformationContext context) {
    String _simpleName = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PUBLIC);
        it.setStatic(true);
        TypeReference _xblockexpression = null;
        {
          final TypeReference typeParameter = context.newTypeReference(annotatedClass);
          String _literalClassName = EntityLiteralProcessor.this.getLiteralClassName(classType);
          MutableClassDeclaration _findClassWithTypeParam = EntityLiteralProcessor.this.findClassWithTypeParam(_literalClassName, context);
          _xblockexpression = context.newTypeReference(_findClassWithTypeParam, typeParameter);
        }
        it.setReturnType(_xblockexpression);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return sharedInstance.");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("();");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    outerClassType.addMethod(_simpleName, _function);
  }
  
  private void addLiteralImplMethod(final MutableClassDeclaration classType, final FieldDeclaration field, @Extension final TransformationContext context, final MutableTypeParameterDeclaration typeParameter) {
    String _simpleName = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PUBLIC);
        TypeReference _newTypeReference = context.newTypeReference(typeParameter);
        TypeReference _newTypeReference_1 = context.newTypeReference(Property.class, _newTypeReference);
        it.setReturnType(_newTypeReference_1);
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return new ");
            TypeReference _newTypeReference = context.newTypeReference(typeParameter);
            TypeReference _newTypeReference_1 = context.newTypeReference(LeafProperty.class, _newTypeReference);
            String _javaCode = it.toJavaCode(_newTypeReference_1);
            _builder.append(_javaCode, "");
            _builder.append("(getParentPath(), \"");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("\", false, owningClass);");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    classType.addMethod(_simpleName, _function);
  }
  
  private void addTypeLiteralImplMethod(final MutableClassDeclaration outerClassType, final ClassDeclaration classType, final FieldDeclaration field, @Extension final TransformationContext context, final MutableTypeParameterDeclaration typeParameter) {
    String _simpleName = field.getSimpleName();
    final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PUBLIC);
        it.setReturnType(
          context.newTypeReference(EntityLiteralProcessor.this.findClassWithTypeParam(EntityLiteralProcessor.this.getLiteralClassName(classType), context), context.newTypeReference(typeParameter)));
        final CompilationStrategy _function = new CompilationStrategy() {
          public CharSequence compile(final CompilationStrategy.CompilationContext it) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("return new ");
            String _literalClassName = EntityLiteralProcessor.this.getLiteralClassName(classType);
            MutableClassDeclaration _findClassWithTypeParam = EntityLiteralProcessor.this.findClassWithTypeParam(_literalClassName, context);
            TypeReference _newTypeReference = context.newTypeReference(typeParameter);
            TypeReference _newTypeReference_1 = context.newTypeReference(_findClassWithTypeParam, _newTypeReference);
            String _javaCode = it.toJavaCode(_newTypeReference_1);
            _builder.append(_javaCode, "");
            _builder.append("(getParentPath(), \"");
            String _simpleName = field.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("\", owningClass);");
            return _builder;
          }
        };
        it.setBody(_function);
      }
    };
    outerClassType.addMethod(_simpleName, _function);
  }
  
  private boolean isAnnotatedWithProperty(final FieldDeclaration field, final TransformationContext context) {
    return this._annotationExtensions.isAnnotatedWithType(field, context, de.abg.jreichert.activeanno.jpa.Property.class);
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
