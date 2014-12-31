package de.abg.jreichert.activeanno.testsuite;

import com.google.common.base.Objects;
import de.abg.jreichert.activeanno.testsuite.TestSuite;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationUnit;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.file.Path;
import org.eclipse.xtend.lib.macro.services.AnnotationReferenceBuildContext;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@SuppressWarnings("all")
public class TestSuiteProcessor extends AbstractClassProcessor {
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    final Procedure1<AnnotationReferenceBuildContext> _function = new Procedure1<AnnotationReferenceBuildContext>() {
      public void apply(final AnnotationReferenceBuildContext it) {
        TypeReference _newTypeReference = context.newTypeReference(Suite.class);
        it.setClassValue("value", _newTypeReference);
      }
    };
    final AnnotationReference runWithAnnotation = context.newAnnotationReference(RunWith.class, _function);
    annotatedClass.addAnnotation(runWithAnnotation);
    final CompilationUnit compilationUnit = annotatedClass.getCompilationUnit();
    Path _filePath = compilationUnit.getFilePath();
    final Path src = context.getSourceFolder(_filePath);
    Iterable<? extends AnnotationReference> _annotations = annotatedClass.getAnnotations();
    final Function1<AnnotationReference, Boolean> _function_1 = new Function1<AnnotationReference, Boolean>() {
      public Boolean apply(final AnnotationReference it) {
        AnnotationTypeDeclaration _annotationTypeDeclaration = it.getAnnotationTypeDeclaration();
        String _qualifiedName = _annotationTypeDeclaration.getQualifiedName();
        TypeReference _newTypeReference = context.newTypeReference(TestSuite.class);
        Type _type = _newTypeReference.getType();
        String _qualifiedName_1 = _type.getQualifiedName();
        return Boolean.valueOf(Objects.equal(_qualifiedName, _qualifiedName_1));
      }
    };
    Iterable<? extends AnnotationReference> _filter = IterableExtensions.filter(_annotations, _function_1);
    final AnnotationReference testSuiteAnnotation = IterableExtensions.head(_filter);
    Object _value = testSuiteAnnotation.getValue("postfixPattern");
    final String value = ((String) _value);
    String _xifexpression = null;
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(value);
    if (_isNullOrEmpty) {
      _xifexpression = "Test";
    } else {
      _xifexpression = value;
    }
    final String postfixPattern = _xifexpression;
    final ArrayList<Type> classes = CollectionLiterals.<Type>newArrayList();
    this.traverse(testSuiteAnnotation, src, src, classes, "", postfixPattern, context);
    final Procedure1<AnnotationReferenceBuildContext> _function_2 = new Procedure1<AnnotationReferenceBuildContext>() {
      public void apply(final AnnotationReferenceBuildContext it) {
        final Function1<Type, TypeReference> _function = new Function1<Type, TypeReference>() {
          public TypeReference apply(final Type it) {
            return context.newTypeReference(it);
          }
        };
        List<TypeReference> _map = ListExtensions.<Type, TypeReference>map(classes, _function);
        it.setClassValue("value", ((TypeReference[])Conversions.unwrapArray(_map, TypeReference.class)));
      }
    };
    final AnnotationReference suiteAnnotation = context.newAnnotationReference(Suite.SuiteClasses.class, _function_2);
    annotatedClass.addAnnotation(suiteAnnotation);
  }
  
  public void traverse(final AnnotationReference testSuiteAnnotation, final Path source, final Path path, final List<Type> classes, final String packageName, final String postfixPattern, @Extension final TransformationContext context) {
    Iterable<? extends Path> _children = context.getChildren(path);
    for (final Path child : _children) {
      boolean _isFile = context.isFile(child);
      if (_isFile) {
        Path _relativize = child.relativize(source);
        List<String> _segments = _relativize.getSegments();
        String _join = IterableExtensions.join(_segments, ".");
        String _fileExtension = child.getFileExtension();
        String _plus = ("." + _fileExtension);
        final String qName = _join.replace(_plus, "");
        boolean _endsWith = qName.endsWith(postfixPattern);
        if (_endsWith) {
          final Type foundType = context.findTypeGlobally(qName);
          boolean _tripleNotEquals = (foundType != null);
          if (_tripleNotEquals) {
            classes.add(foundType);
          }
        }
      } else {
        final String newPackageName = (packageName + ".");
        child.getLastSegment();
        this.traverse(testSuiteAnnotation, source, child, classes, newPackageName, postfixPattern, context);
      }
    }
  }
}
