2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/ServiceConfiguration.java
package com.upgrad.musichoster.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.upgrad.musichoster.service")
@EntityScan("com.upgrad.musichoster.service.entity")
public class ServiceConfiguration {
}
