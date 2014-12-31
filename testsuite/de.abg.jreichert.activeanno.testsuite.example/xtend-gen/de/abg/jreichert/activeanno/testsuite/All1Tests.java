package de.abg.jreichert.activeanno.testsuite;

import de.abg.jreichert.activeanno.testsuite.TestSuite;
import de.abg.jreichert.activeanno.testsuite.addition.Addition1Test;
import de.abg.jreichert.activeanno.testsuite.substraction.Substraction1Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@TestSuite(postfixPattern = "1Test")
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = { Addition1Test.class, Substraction1Test.class })
@SuppressWarnings("all")
public class All1Tests {
}
