2
https://raw.githubusercontent.com/okhurley/oauth2/master/oauth2_uaa/src/main/java/com/okhurley/uaa/UaaApplication.java
package com.okhurley.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.okhurley.uaa.dao"})
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run( UaaApplication.class);
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
