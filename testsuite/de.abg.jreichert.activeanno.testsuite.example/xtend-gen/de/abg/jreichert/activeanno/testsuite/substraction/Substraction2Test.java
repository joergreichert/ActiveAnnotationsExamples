package de.abg.jreichert.activeanno.testsuite.substraction;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class Substraction2Test {
  @Test
  public void subs21() {
    Assert.assertEquals(22, (99 - 77));
  }
}
