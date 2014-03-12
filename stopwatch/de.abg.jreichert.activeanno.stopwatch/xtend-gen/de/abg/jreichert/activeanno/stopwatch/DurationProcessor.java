package de.abg.jreichert.activeanno.stopwatch;

import com.google.common.base.Objects;
import org.eclipse.xtend.lib.macro.AbstractMethodProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DurationProcessor extends AbstractMethodProcessor {
  @Extension
  private /* AnnotationExtensions */Object _annotationExtensions /* Skipped initializer because of errors */;
  
  public void doTransform(final MutableMethodDeclaration method, @Extension final TransformationContext context) {
    TypeReference _returnType = method.getReturnType();
    boolean _tripleEquals = (_returnType == null);
    if (_tripleEquals) {
      context.addError(method, "A method to be time measured have to explicitly declare its return type (or void if there should be none).");
    }
    final String wrappedMethodName = this.newMethodName(method);
    final MutableTypeDeclaration clazz = method.getDeclaringType();
    final Expression originalMethodBody = method.getBody();
    final Iterable<? extends MutableParameterDeclaration> params = method.getParameters();
    final CompilationStrategy _function = new CompilationStrategy() {
      public CharSequence compile(final CompilationStrategy.CompilationContext it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("long start = System.currentTimeMillis();");
        _builder.newLine();
        String _handleReturnTypeAssignment = DurationProcessor.this.handleReturnTypeAssignment(method, context);
        _builder.append(_handleReturnTypeAssignment, "");
        _builder.append(wrappedMethodName, "");
        _builder.append("(");
        final Function1<MutableParameterDeclaration,String> _function = new Function1<MutableParameterDeclaration,String>() {
          public String apply(final MutableParameterDeclaration it) {
            return it.getSimpleName();
          }
        };
        Iterable<String> _map = IterableExtensions.map(params, _function);
        String _join = IterableExtensions.join(_map, ", ");
        _builder.append(_join, "");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("logDuration(start, \"");
        String _simpleName = method.getSimpleName();
        _builder.append(_simpleName, "");
        _builder.append("(");
        final Function1<MutableParameterDeclaration,String> _function_1 = new Function1<MutableParameterDeclaration,String>() {
          public String apply(final MutableParameterDeclaration it) {
            String _simpleName = it.getSimpleName();
            String _plus = (_simpleName + " = \" + ");
            String _simpleName_1 = it.getSimpleName();
            String _plus_1 = (_plus + _simpleName_1);
            return (_plus_1 + " + \"");
          }
        };
        Iterable<String> _map_1 = IterableExtensions.map(params, _function_1);
        String _join_1 = IterableExtensions.join(_map_1, ", ");
        _builder.append(_join_1, "");
        _builder.append(")\");");
        _builder.newLineIfNotEmpty();
        String _handleReturnTypeReturnment = DurationProcessor.this.handleReturnTypeReturnment(method, context);
        _builder.append(_handleReturnTypeReturnment, "");
        _builder.newLineIfNotEmpty();
        return _builder;
      }
    };
    method.setBody(_function);
    final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PRIVATE);
        boolean _isFinal = method.isFinal();
        it.setFinal(_isFinal);
        boolean _isStatic = method.isStatic();
        it.setStatic(_isStatic);
        Iterable<? extends MutableParameterDeclaration> _parameters = method.getParameters();
        final Procedure1<MutableParameterDeclaration> _function = new Procedure1<MutableParameterDeclaration>() {
          public void apply(final MutableParameterDeclaration p) {
            String _simpleName = p.getSimpleName();
            TypeReference _type = p.getType();
            it.addParameter(_simpleName, _type);
          }
        };
        IterableExtensions.forEach(_parameters, _function);
        TypeReference _returnType = method.getReturnType();
        it.setReturnType(_returnType);
        Iterable<? extends TypeReference> _exceptions = method.getExceptions();
        it.setExceptions(((TypeReference[])Conversions.unwrapArray(_exceptions, TypeReference.class)));
        it.setBody(originalMethodBody);
      }
    };
    clazz.addMethod(wrappedMethodName, _function_1);
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = clazz.getDeclaredMethods();
    final Function1<MutableMethodDeclaration,Boolean> _function_2 = new Function1<MutableMethodDeclaration,Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        String _simpleName = it.getSimpleName();
        return Boolean.valueOf(Objects.equal(_simpleName, "logDuration"));
      }
    };
    Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function_2);
    boolean _isEmpty = IterableExtensions.isEmpty(_filter);
    if (_isEmpty) {
      final Procedure1<MutableMethodDeclaration> _function_3 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
          it.setStatic(true);
          TypeReference _primitiveLong = context.getPrimitiveLong();
          it.addParameter("start", _primitiveLong);
          TypeReference _string = context.getString();
          it.addParameter("message", _string);
          TypeReference _primitiveVoid = context.getPrimitiveVoid();
          it.setReturnType(_primitiveVoid);
          final CompilationStrategy _function = new CompilationStrategy() {
            public CharSequence compile(final CompilationStrategy.CompilationContext it) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("long end = System.currentTimeMillis();");
              _builder.newLine();
              String _fqnSimpleDateFormat = DurationProcessor.this.fqnSimpleDateFormat(it, context);
              _builder.append(_fqnSimpleDateFormat, "");
              _builder.append(" df = new ");
              String _fqnSimpleDateFormat_1 = DurationProcessor.this.fqnSimpleDateFormat(it, context);
              _builder.append(_fqnSimpleDateFormat_1, "");
              _builder.append("(\"mm:ss.SSS\");");
              _builder.newLineIfNotEmpty();
              String _fqnCalendar = DurationProcessor.this.fqnCalendar(it, context);
              _builder.append(_fqnCalendar, "");
              _builder.append(" cal = ");
              String _fqnCalendar_1 = DurationProcessor.this.fqnCalendar(it, context);
              _builder.append(_fqnCalendar_1, "");
              _builder.append(".getInstance();");
              _builder.newLineIfNotEmpty();
              _builder.append("cal.setTimeInMillis(end - start);");
              _builder.newLine();
              _builder.append("String timeStr = df.format(cal.getTime());");
              _builder.newLine();
              _builder.append("System.err.println(message + \": \" + timeStr + \" min\");");
              _builder.newLine();
              return _builder;
            }
          };
          it.setBody(_function);
        }
      };
      clazz.addMethod("logDuration", _function_3);
    }
  }
  
  private String fqnSimpleDateFormat(final CompilationStrategy.CompilationContext compilationContext, final TransformationContext transformationContext) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method fqnClass is undefined for the type DurationProcessor");
  }
  
  private String fqnCalendar(final CompilationStrategy.CompilationContext compilationContext, final TransformationContext transformationContext) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method fqnClass is undefined for the type DurationProcessor");
  }
  
  private String newMethodName(final MethodDeclaration it) {
    String _simpleName = it.getSimpleName();
    return (_simpleName + "__Measured");
  }
  
  private String handleReturnTypeAssignment(final MutableMethodDeclaration method, @Extension final TransformationContext context) {
    String _xifexpression = null;
    TypeReference _returnType = method.getReturnType();
    TypeReference _primitiveVoid = context.getPrimitiveVoid();
    boolean _equals = _returnType.equals(_primitiveVoid);
    if (_equals) {
      _xifexpression = "";
    } else {
      TypeReference _returnType_1 = method.getReturnType();
      String _simpleName = _returnType_1.getSimpleName();
      _xifexpression = (_simpleName + " result = ");
    }
    return _xifexpression;
  }
  
  private String handleReturnTypeReturnment(final MutableMethodDeclaration method, @Extension final TransformationContext context) {
    String _xifexpression = null;
    TypeReference _returnType = method.getReturnType();
    TypeReference _primitiveVoid = context.getPrimitiveVoid();
    boolean _equals = _returnType.equals(_primitiveVoid);
    if (_equals) {
      _xifexpression = "";
    } else {
      _xifexpression = "return result;";
    }
    return _xifexpression;
  }
}
