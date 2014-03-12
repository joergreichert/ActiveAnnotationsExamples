package de.abg.jreichert.activeanno.stopwatch.example

class MyClass {

	def static void main(String[] args) {
		new MyClass().simple		
	}

	@LogExecutionTime
	def void simple() {
		println("simple");
	}
}