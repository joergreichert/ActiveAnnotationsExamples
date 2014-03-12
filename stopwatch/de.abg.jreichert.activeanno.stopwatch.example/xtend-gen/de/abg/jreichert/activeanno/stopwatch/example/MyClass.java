package de.abg.jreichert.activeanno.stopwatch.example;

import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class MyClass {
  public static void main(final String[] args) {
    MyClass _myClass = new MyClass();
    _myClass.simple();
  }
  
  /* @LogExecutionTime
   */public void simple() {
    InputOutput.<String>println("simple");
  }
}
