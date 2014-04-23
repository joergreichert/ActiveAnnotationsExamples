package de.abg.jreichert.activeanno.testsuite.addition

import org.junit.Assert
import org.junit.Test

class Addition1Test {
	@Test
	def void sum1() {
		Assert.assertEquals(5, 2 + 3)
	}
	
	@Test
	def void sum2() {
		Assert.assertEquals(35, 12 + 23)
	}	
}