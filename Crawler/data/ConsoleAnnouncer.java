60
https://raw.githubusercontent.com/Jeka1978/coronadesinfectorlifedemo/master/src/main/java/com/epam/ConsoleAnnouncer.java
package com.epam;

/**
 * @author Evgeny Borisov
 */
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
