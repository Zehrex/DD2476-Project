9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/launcher/src/main/java/engineering/everest/lhotse/config/RestTemplateConfig.java
package engineering.everest.lhotse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

