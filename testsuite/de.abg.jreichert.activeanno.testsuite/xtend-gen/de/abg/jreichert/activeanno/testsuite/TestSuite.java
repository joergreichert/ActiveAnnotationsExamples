package de.abg.jreichert.activeanno.testsuite;

import de.abg.jreichert.activeanno.testsuite.TestSuiteProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.TYPE)
@Active(TestSuiteProcessor.class)
public @interface TestSuite {
  public boolean includeSubPackages() default false;
  public String postfixPattern() default "Test";
}
