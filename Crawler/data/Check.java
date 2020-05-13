2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/itcast/test/Check.java
package java2.itcast.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Check {
}
