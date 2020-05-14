11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/OrderApp.java
package com.hyf.mtcc.demo.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hyf.mtcc.demo.order.inter"})
@EnableHystrix
@EnableTransactionManagement
@MapperScan(basePackages = {"com.hyf.mtcc.demo.order.dao"})
public class OrderApp {
    public static void main( String[] args ){
        SpringApplication.run(OrderApp.class,args);
    }
}
