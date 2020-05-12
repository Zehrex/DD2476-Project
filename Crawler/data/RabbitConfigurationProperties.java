2
https://raw.githubusercontent.com/Merthaskan/sample-consumer/master/src/main/java/com/trendyol/sampleconsumer/properties/RabbitConfigurationProperties.java
package com.trendyol.sampleconsumer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "rabbit")
public class RabbitConfigurationProperties {
    private RecoverProperty recover;
    private EventProperty event;
    private RetryPolicyProperty retryPolicy;

    @Data
    public static class RecoverProperty{
        private String delayedMessageQueuePostfix;
        private String delayMillisecond;
    }

    @Data
    public static class EventProperty{
        private String exchange;
        private String queue;
        private String delayQueue;
    }

    @Data
    public static class RetryPolicyProperty{
        private int maxAttempt;
        private long initialInterval;
        private double multiplier;
        private long maxInterval;
    }
}
