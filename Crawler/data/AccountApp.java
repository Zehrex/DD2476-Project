11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/AccountApp.java
package com.hyf.mtcc.demo.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Descrtption AccountApp
 * @Author Elvis
 * @Date 2019/9/23
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableTransactionManagement
@MapperScan(basePackages = {"com.hyf.mtcc.demo.account.dao"})
public class AccountApp {

    public static void main(String[] args) {
        SpringApplication.run(AccountApp.class,args);
    }

}
