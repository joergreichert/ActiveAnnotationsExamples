package de.abg.jreichert.activeanno.testsuite.substraction

import org.junit.Assert
import org.junit.Test

class Substraction1Test {

	@Test
	def void subs1() {
		Assert.assertEquals(2, 5 - 3)
	}
	
	@Test
	def void subs2() {
		Assert.assertEquals(12, 35 - 23)
	}	
}