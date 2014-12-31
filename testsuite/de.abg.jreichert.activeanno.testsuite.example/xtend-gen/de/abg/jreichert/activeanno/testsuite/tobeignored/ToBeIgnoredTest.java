package de.abg.jreichert.activeanno.testsuite.tobeignored;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Test;

@SuppressWarnings("all")
public class ToBeIgnoredTest {
  @Test
  public void test() {
    InputOutput.<String>println("To be ignored.");
  }
}
