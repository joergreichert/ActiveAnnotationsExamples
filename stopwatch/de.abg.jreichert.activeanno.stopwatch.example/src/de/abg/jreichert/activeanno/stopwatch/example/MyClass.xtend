package de.abg.jreichert.activeanno.stopwatch.example

import de.abg.jreichert.activeanno.stopwatch.LogExecutionTime

class MyClass {

	def static void main(String[] args) {
		new MyClass().simple		
	}

	@LogExecutionTime
	def void simple() {
		println("simple");
	}
}