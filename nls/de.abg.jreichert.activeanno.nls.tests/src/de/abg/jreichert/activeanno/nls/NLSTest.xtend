package de.abg.jreichert.activeanno.nls

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class NLSTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(de.abg.jreichert.activeanno.nls.NLS)

	@Test def void testNLS() {
		'''
			@de.abg.jreichert.activeanno.nls.NLS(propertyFileName="messages")
			class MyClass {
			}
		'''.assertCompilesTo(
			'''
			''')
	}
}
