package de.abg.jreichert.activeanno.common;

import com.google.common.base.Objects;
import de.abg.jreichert.activeanno.common.AnnotationSearch;
import de.abg.jreichert.activeanno.common.AnnotationValueSearch;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTarget;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class AnnotationExtensions {
  public String fqnClass(final CompilationStrategy.CompilationContext compilationContext, final TransformationContext transformationContext, final Class<?> clazz) {
    TypeReference _newTypeReference = transformationContext.newTypeReference(clazz);
    return compilationContext.toJavaCode(_newTypeReference);
  }
  
  public boolean isAnnotatedWithType(final TypeReference typeRef, final TransformationContext context, final Class<?> type) {
    boolean _xblockexpression = false;
    {
      Type _type = typeRef.getType();
      if ((!(_type instanceof AnnotationTarget))) {
        return false;
      }
      Type _type_1 = typeRef.getType();
      _xblockexpression = this.isAnnotatedWithType(((AnnotationTarget) _type_1), context, type);
    }
    return _xblockexpression;
  }
  
  public boolean isAnnotatedWithType(final AnnotationTarget annotationTarget, final TransformationContext context, final Class<?> type) {
    AnnotationSearch _annotationSearch = new AnnotationSearch();
    final Procedure1<AnnotationSearch> _function = new Procedure1<AnnotationSearch>() {
      public void apply(final AnnotationSearch it) {
        it.setType(annotationTarget);
        it.setContext(context);
        it.setAnnotationClass(type);
      }
    };
    AnnotationSearch _doubleArrow = ObjectExtensions.<AnnotationSearch>operator_doubleArrow(_annotationSearch, _function);
    AnnotationReference _findAnnotation = this.findAnnotation(_doubleArrow);
    return (_findAnnotation != null);
  }
  
  public <T extends Object> T findAnnotationWithValue(@Extension final AnnotationValueSearch<T> annotationValueSearch) {
    AnnotationSearch _annotationSearch = annotationValueSearch.getAnnotationSearch();
    AnnotationReference _findAnnotation = this.findAnnotation(_annotationSearch);
    Object _value = null;
    if (_findAnnotation!=null) {
      String _value_1 = annotationValueSearch.getValue();
      _value=_findAnnotation.getValue(_value_1);
    }
    return ((T) _value);
  }
  
  public AnnotationReference findAnnotation(@Extension final AnnotationSearch annotationSearch) {
    TransformationContext _context = annotationSearch.getContext();
    return this.findAnnotation(annotationSearch, _context);
  }
  
  private AnnotationReference findAnnotation(@Extension final AnnotationSearch annotationSearch, @Extension final TransformationContext context) {
    AnnotationTarget _type = annotationSearch.getType();
    Iterable<? extends AnnotationReference> _annotations = _type.getAnnotations();
    final Function1<AnnotationReference,Boolean> _function = new Function1<AnnotationReference,Boolean>() {
      public Boolean apply(final AnnotationReference it) {
        AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
        String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
        Class<?> _annotationClass = annotationSearch.getAnnotationClass();
        TypeReference _newTypeReference = context.newTypeReference(_annotationClass);
        Type _type = _newTypeReference.getType();
        String _qualifiedName_1 = _type.getQualifiedName();
        return Boolean.valueOf(Objects.equal(_qualifiedName, _qualifiedName_1));
      }
    };
    Iterable<? extends AnnotationReference> _filter = IterableExtensions.filter(_annotations, _function);
    return IterableExtensions.head(_filter);
  }
}
