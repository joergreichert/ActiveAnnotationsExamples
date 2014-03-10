package de.abg.jreichert.activeanno.stopwatch.example

class MyClass {

	@de.abg.jreichert.activeanno.stopwatch.LogExecutionTime
	def void simple() {
		println("simple");
	}
}