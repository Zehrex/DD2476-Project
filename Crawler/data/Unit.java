11
https://raw.githubusercontent.com/chris-albert/zio4j/master/src/main/java/io/lbert/Unit.java
package io.lbert;

public class Unit {

  private static final Unit Instance = new Unit();

  private Unit () { }

  public static Unit of() {
    return Instance;
  }
}
