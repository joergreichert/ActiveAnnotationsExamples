package de.abg.jreichert.activeanno.testsuite.substraction

import org.junit.Assert
import org.junit.Test

class Substraction2Test {

	@Test
	def void subs21() {
		Assert.assertEquals(22, 99 - 77)
	}
}