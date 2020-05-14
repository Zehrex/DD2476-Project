9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/launcher/src/main/java/engineering/everest/lhotse/config/JacksonConfig.java
package engineering.everest.lhotse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper domainObjectMapper() {
        return new ObjectMapper();
    }

}
