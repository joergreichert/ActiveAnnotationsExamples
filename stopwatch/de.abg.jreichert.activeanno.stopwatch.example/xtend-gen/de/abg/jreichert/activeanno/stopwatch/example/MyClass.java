package de.abg.jreichert.activeanno.stopwatch.example;

import de.abg.jreichert.activeanno.stopwatch.LogExecutionTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class MyClass {
  public static void main(final String[] args) {
    MyClass _myClass = new MyClass();
    _myClass.simple();
  }
  
  @LogExecutionTime
  public void simple() {
    long start = System.currentTimeMillis();
    simple__Measured();
    logDuration(start, "simple()");
  }
  
  private void simple__Measured() {
    InputOutput.<String>println("simple");
  }
  
  private static void logDuration(final long start, final String message) {
    long end = System.currentTimeMillis();
    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(end - start);
    String timeStr = df.format(cal.getTime());
    System.err.println(message + ": " + timeStr + " min");
  }
}
