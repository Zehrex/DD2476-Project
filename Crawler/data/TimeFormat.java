2
https://raw.githubusercontent.com/Virjid/Kartingjson/master/src/main/java/me/virjid/karting/json/annotation/TimeFormat.java
package me.virjid.karting.json.annotation;

import java.lang.annotation.*;

/**
 * @author Virjid
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeFormat {
    String value() default "";
}
