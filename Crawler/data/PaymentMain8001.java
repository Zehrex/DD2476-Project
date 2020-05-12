1
https://raw.githubusercontent.com/iMine141/springcloud-test/master/cloud-provider-paymeny8001/src/main/java/com/atguigu/springcloud/PaymentMain8001.java
package com.atguigu.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.atguigu.springcloud.dao")
@EnableEurekaClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class);
    }
}
