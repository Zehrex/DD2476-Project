25
https://raw.githubusercontent.com/griabcrh/vue-activiti-demo/master/vue-activiti-service-demo/src/main/java/com/activiti/VueActivitiServiceDemoApplication.java
package com.activiti;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


/**
 * @author crh
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class VueActivitiServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueActivitiServiceDemoApplication.class, args);
	}

}
