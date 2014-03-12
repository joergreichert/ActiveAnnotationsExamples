package de.abg.jreichert.activeanno.common;

import de.abg.jreichert.activeanno.common.AnnotationSearch;

@SuppressWarnings("all")
public class AnnotationValueSearch<T extends Object> {
  private AnnotationSearch _annotationSearch;
  
  public AnnotationSearch getAnnotationSearch() {
    return this._annotationSearch;
  }
  
  public void setAnnotationSearch(final AnnotationSearch annotationSearch) {
    this._annotationSearch = annotationSearch;
  }
  
  private String _value;
  
  public String getValue() {
    return this._value;
  }
  
  public void setValue(final String value) {
    this._value = value;
  }
  
  private Class<T> _valueType;
  
  public Class<T> getValueType() {
    return this._valueType;
  }
  
  public void setValueType(final Class<T> valueType) {
    this._valueType = valueType;
  }
}
