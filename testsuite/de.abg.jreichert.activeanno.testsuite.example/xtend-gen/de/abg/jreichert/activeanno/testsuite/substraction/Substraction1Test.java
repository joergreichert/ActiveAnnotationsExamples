package de.abg.jreichert.activeanno.testsuite.substraction;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class Substraction1Test {
  @Test
  public void subs1() {
    Assert.assertEquals(2, (5 - 3));
  }
  
  @Test
  public void subs2() {
    Assert.assertEquals(12, (35 - 23));
  }
}
