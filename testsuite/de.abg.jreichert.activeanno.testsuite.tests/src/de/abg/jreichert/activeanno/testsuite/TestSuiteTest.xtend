package de.abg.jreichert.activeanno.testsuite

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class TestSuiteTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(TestSuite)

	@Test def void testTestSuite() {
		'''
			@de.abg.jreichert.activeanno.testsuite.TestSuite
			class AllTests {
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.activeanno.testsuite.TestSuite;
				import org.junit.runner.RunWith;
				import org.junit.runners.Suite;
				
				@TestSuite
				@RunWith(value = Suite.class)
				@Suite.SuiteClasses(value = {})
				@SuppressWarnings("all")
				public class AllTests {
				}
			''')
	}
}
