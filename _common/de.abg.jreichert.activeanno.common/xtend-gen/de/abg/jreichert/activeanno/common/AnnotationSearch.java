package de.abg.jreichert.activeanno.common;

import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTarget;

@SuppressWarnings("all")
public class AnnotationSearch {
  private AnnotationTarget _type;
  
  public AnnotationTarget getType() {
    return this._type;
  }
  
  public void setType(final AnnotationTarget type) {
    this._type = type;
  }
  
  private TransformationContext _context;
  
  public TransformationContext getContext() {
    return this._context;
  }
  
  public void setContext(final TransformationContext context) {
    this._context = context;
  }
  
  private Class<?> _annotationClass;
  
  public Class<?> getAnnotationClass() {
    return this._annotationClass;
  }
  
  public void setAnnotationClass(final Class<?> annotationClass) {
    this._annotationClass = annotationClass;
  }
}
