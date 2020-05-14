2
https://raw.githubusercontent.com/ryandw11/ODS/master/src/main/java/me/ryandw11/ods/serializer/Serializable.java
package me.ryandw11.ods.serializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Serializable {}