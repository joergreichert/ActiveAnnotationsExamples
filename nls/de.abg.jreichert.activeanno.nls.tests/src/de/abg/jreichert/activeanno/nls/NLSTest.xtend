package de.abg.jreichert.activeanno.nls

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Ignore
import org.junit.Test

class NLSTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(NLS)

	@Ignore("have to handle message.properties in test")
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
