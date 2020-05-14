11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/StockApp.java
package com.hyf.mtcc.demo.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableTransactionManagement
@MapperScan(basePackages = {"com.hyf.mtcc.demo.stock.dao"})
public class StockApp {

    public static void main(String[] args) {
        SpringApplication.run(StockApp.class, args);
    }

}
