package de.abg.jreichert.activeanno.testsuite.addition;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class Addition1Test {
  @Test
  public void sum1() {
    Assert.assertEquals(5, (2 + 3));
  }
  
  @Test
  public void sum2() {
    Assert.assertEquals(35, (12 + 23));
  }
}
