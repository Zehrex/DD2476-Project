2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/validator/ValidLeasingData.java
package com.gardle.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LeasingDataValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLeasingData {
    String message() default "Invalid leasing data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
