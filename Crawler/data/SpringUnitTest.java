2
https://raw.githubusercontent.com/ShaneKing/org.shaneking.spring.test/master/src/test/java/sktest/spring/test/SpringUnitTest.java
package sktest.spring.test;

import org.junit.Test;
import org.shaneking.spring.test.SKSpringUnit;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringUnitApplication.class)
public class SpringUnitTest extends SKSpringUnit {
  @Test
  public void empty() {
    skPrint("empty");
  }
}
