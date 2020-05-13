2
https://raw.githubusercontent.com/bruinli28/stockmarket/master/cloud-eureka-service/src/main/java/com/iiht/stock/eureka/EurekaServerApplication.java
package com.iiht.stock.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	public static void main(String[] args)throws Exception{
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
