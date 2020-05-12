2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/config/ApplicationProperties.java
package com.gardle.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Airgnb.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}
