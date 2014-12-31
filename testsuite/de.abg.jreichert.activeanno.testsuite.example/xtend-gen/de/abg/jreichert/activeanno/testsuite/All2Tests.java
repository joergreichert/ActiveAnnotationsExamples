package de.abg.jreichert.activeanno.testsuite;

import de.abg.jreichert.activeanno.testsuite.TestSuite;
import de.abg.jreichert.activeanno.testsuite.substraction.Substraction2Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@TestSuite(postfixPattern = "2Test")
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = Substraction2Test.class)
@SuppressWarnings("all")
public class All2Tests {
}
