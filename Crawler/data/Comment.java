3
https://raw.githubusercontent.com/Silthus/sLimits/master/src/main/java/net/silthus/slib/config/Comment.java
package net.silthus.slib.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {
    String value();
}
