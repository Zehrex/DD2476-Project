2
https://raw.githubusercontent.com/okhurley/oauth2/master/oauth2_eureka/src/main/java/com/okhurley/eureka/EurekaApplication.java
package com.okhurley.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class);
    }

}
