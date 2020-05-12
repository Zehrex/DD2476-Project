6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/annotation/ValidateCode.java
package com.github.taoroot.taoiot.security.annotation;

import java.lang.annotation.*;

/**
 * 加验证码
 *
 * @author zhiyi
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateCode {
    /**
     * IMAGE, SMS, ALL
     */
    String value() default "ALL";
}
