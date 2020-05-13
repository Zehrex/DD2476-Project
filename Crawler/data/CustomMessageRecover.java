2
https://raw.githubusercontent.com/Merthaskan/sample-consumer/master/src/main/java/com/trendyol/sampleconsumer/recover/CustomMessageRecover.java
package com.trendyol.sampleconsumer.recover;

import com.trendyol.sampleconsumer.recover.strategy.RabbitStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMessageRecover implements MessageRecoverer {
    private final List<RabbitStrategy> rabbitStrategies;

    @Override
    public void recover(Message message, Throwable throwable) {
        for (RabbitStrategy rabbitStrategy: rabbitStrategies) {
            //Checks Strategy is suited for exception and message
            boolean isSuccess = rabbitStrategy.process(message,throwable);
            if (isSuccess){
                //Apply suitable strategy
                rabbitStrategy.recover(message,throwable);
                break;
            }
        }
    }
}
