12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-eureka/src/main/java/com/changwu/EurekaApp.java
package com.changwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Changwu
 * @Date: 2019/9/16 19:40
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp{
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class);
    }
}
