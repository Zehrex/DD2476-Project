60
https://raw.githubusercontent.com/Jeka1978/coronadesinfectorlifedemo/master/src/main/java/com/epam/RecommendatorImpl.java
package com.epam;

/**
 * @author Evgeny Borisov
 */
@Singleton
public class RecommendatorImpl implements Recommendator {
    @InjectProperty("wisky")
    private String alcohol;

    public RecommendatorImpl() {
        System.out.println("recommendator was created");
    }

    @Override
    public void recommend() {
        System.out.println("to protect from covid-2019, drink "+alcohol);
    }
}
