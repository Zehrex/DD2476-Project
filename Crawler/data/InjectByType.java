60
https://raw.githubusercontent.com/Jeka1978/coronadesinfectorlifedemo/master/src/main/java/com/epam/InjectByType.java
package com.epam;/**
 * @author Evgeny Borisov
 */

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface InjectByType {
}
