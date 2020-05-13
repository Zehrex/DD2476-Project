2
https://raw.githubusercontent.com/ShaneKing/org.shaneking.spring.test/master/src/test/java/sktest/spring/test/SpringUnitApplication.java
package sktest.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringUnitApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringUnitApplication.class, args);
  }
}
