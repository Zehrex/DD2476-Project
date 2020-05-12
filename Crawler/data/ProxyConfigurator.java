60
https://raw.githubusercontent.com/Jeka1978/coronadesinfectorlifedemo/master/src/main/java/com/epam/ProxyConfigurator.java
package com.epam;

/**
 * @author Evgeny Borisov
 */
public interface ProxyConfigurator {
    Object replaceWithProxyIfNeeded(Object t, Class implClass);
}
