34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_eureka/src/main/java/com/tensquare/eureka/EurekaServer.java
package com.tensquare.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 李聪
 * @date 2020/2/19 9:45
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class,args);
    }
}
