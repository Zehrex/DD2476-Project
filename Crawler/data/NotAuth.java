6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/annotation/NotAuth.java
package com.github.taoroot.taoiot.security.annotation;

import java.lang.annotation.*;

/**
 * 免鉴权
 *
 * @author zhiyi
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotAuth {

}
