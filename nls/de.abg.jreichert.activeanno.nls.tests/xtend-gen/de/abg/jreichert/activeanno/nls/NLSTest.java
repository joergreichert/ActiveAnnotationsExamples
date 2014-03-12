package de.abg.jreichert.activeanno.nls;

import de.abg.jreichert.activeanno.nls.NLS;
import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("all")
public class NLSTest {
  @Extension
  private XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(NLS.class);
  
  @Ignore("have to handle message.properties in test")
  @Test
  public void testNLS() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@de.abg.jreichert.activeanno.nls.NLS(propertyFileName=\"messages\")");
    _builder.newLine();
    _builder.append("class MyClass {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    this.compilerTester.assertCompilesTo(_builder, _builder_1);
  }
}
