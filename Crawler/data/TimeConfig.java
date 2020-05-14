9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/launcher/src/main/java/engineering/everest/lhotse/config/TimeConfig.java
package engineering.everest.lhotse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

import static java.time.Clock.systemUTC;

@Configuration
public class TimeConfig {

    @Bean
    public Clock clock() {
        return systemUTC();
    }
}
