1
https://raw.githubusercontent.com/iMine141/springcloud-test/master/cloud-provider-payment8002/src/main/java/com/atguigu/springcloud/PaymentMain8002.java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zzyy
 * @create 2020-02-17 21:13
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002
{
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
