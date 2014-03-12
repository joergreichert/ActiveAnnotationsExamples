package de.abg.jreichert.activeanno.jpa;

import com.google.common.base.Objects;
import de.abg.jreichert.activeanno.common.AnnotationExtensions;
import de.abg.jreichert.activeanno.common.AnnotationSearch;
import de.abg.jreichert.activeanno.common.AnnotationValueSearch;
import de.abg.jreichert.activeanno.jpa.EntityLiteralProcessor;
import de.abg.jreichert.activeanno.jpa.PropertyProcessor;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EntityProcessor extends AbstractClassProcessor {
  @Extension
  private AnnotationExtensions _annotationExtensions = new AnnotationExtensions();
  
  private EntityLiteralProcessor entityLiteralProcessor;
  
  public EntityProcessor() {
    EntityLiteralProcessor _entityLiteralProcessor = new EntityLiteralProcessor();
    this.entityLiteralProcessor = _entityLiteralProcessor;
  }
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    this.entityLiteralProcessor.doRegisterGlobals(annotatedClass, context);
  }
  
  public void doTransform(final MutableClassDeclaration clazz, @Extension final TransformationContext context) {
    TypeReference _newTypeReference = context.newTypeReference(Entity.class);
    Type _type = _newTypeReference.getType();
    clazz.addAnnotation(_type);
    Iterable<? extends MutableConstructorDeclaration> _declaredConstructors = clazz.getDeclaredConstructors();
    final Function1<MutableConstructorDeclaration,Boolean> _function = new Function1<MutableConstructorDeclaration,Boolean>() {
      public Boolean apply(final MutableConstructorDeclaration it) {
        Iterable<? extends MutableParameterDeclaration> _parameters = it.getParameters();
        return Boolean.valueOf(IterableExtensions.isEmpty(_parameters));
      }
    };
    Iterable<? extends MutableConstructorDeclaration> _filter = IterableExtensions.filter(_declaredConstructors, _function);
    boolean _isEmpty = IterableExtensions.isEmpty(_filter);
    if (_isEmpty) {
      final Procedure1<MutableConstructorDeclaration> _function_1 = new Procedure1<MutableConstructorDeclaration>() {
        public void apply(final MutableConstructorDeclaration it) {
        }
      };
      clazz.addConstructor(_function_1);
    }
    AnnotationValueSearch<String> _annotationValueSearch = new AnnotationValueSearch<String>();
    final Procedure1<AnnotationValueSearch<String>> _function_2 = new Procedure1<AnnotationValueSearch<String>>() {
      public void apply(final AnnotationValueSearch<String> it) {
        AnnotationSearch _annotationSearch = new AnnotationSearch();
        final Procedure1<AnnotationSearch> _function = new Procedure1<AnnotationSearch>() {
          public void apply(final AnnotationSearch it) {
            it.setType(clazz);
            it.setContext(context);
            it.setAnnotationClass(de.abg.jreichert.activeanno.jpa.Entity.class);
          }
        };
        AnnotationSearch _doubleArrow = ObjectExtensions.<AnnotationSearch>operator_doubleArrow(_annotationSearch, _function);
        it.setAnnotationSearch(_doubleArrow);
        it.setValue("idGenerationType");
        it.setValueType(String.class);
      }
    };
    AnnotationValueSearch<String> _doubleArrow = ObjectExtensions.<AnnotationValueSearch<String>>operator_doubleArrow(_annotationValueSearch, _function_2);
    final String idGenerationType = this._annotationExtensions.<String>findAnnotationWithValue(_doubleArrow);
    boolean _and = false;
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(idGenerationType);
    boolean _not = (!_isNullOrEmpty);
    if (!_not) {
      _and = false;
    } else {
      Iterable<? extends MutableFieldDeclaration> _declaredFields = clazz.getDeclaredFields();
      final Function1<MutableFieldDeclaration,Boolean> _function_3 = new Function1<MutableFieldDeclaration,Boolean>() {
        public Boolean apply(final MutableFieldDeclaration it) {
          Iterable<? extends MutableAnnotationReference> _annotations = it.getAnnotations();
          final Function1<MutableAnnotationReference,Boolean> _function = new Function1<MutableAnnotationReference,Boolean>() {
            public Boolean apply(final MutableAnnotationReference it) {
              AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
              String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
              String _canonicalName = Id.class.getCanonicalName();
              return Boolean.valueOf(Objects.equal(_qualifiedName, _canonicalName));
            }
          };
          return Boolean.valueOf(IterableExtensions.exists(_annotations, _function));
        }
      };
      boolean _exists = IterableExtensions.exists(_declaredFields, _function_3);
      boolean _not_1 = (!_exists);
      _and = _not_1;
    }
    if (_and) {
      final Procedure1<MutableFieldDeclaration> _function_4 = new Procedure1<MutableFieldDeclaration>() {
        public void apply(final MutableFieldDeclaration it) {
          TypeReference _newTypeReference = context.newTypeReference(Id.class);
          Type _type = _newTypeReference.getType();
          it.addAnnotation(_type);
          TypeReference _newTypeReference_1 = context.newTypeReference(Long.class);
          it.setType(_newTypeReference_1);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationStrategy.CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("-1L");
              return _builder;
            }
          };
          it.setInitializer(_function);
        }
      };
      final MutableFieldDeclaration idField = clazz.addField("id", _function_4);
      PropertyProcessor _propertyProcessor = new PropertyProcessor();
      _propertyProcessor.doTransform(idField, context);
    }
    this.entityLiteralProcessor.doTransform(clazz, context);
  }
}
