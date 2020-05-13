7
https://raw.githubusercontent.com/chengxy-nds/delayqueue/master/src/main/java/com/chengxy/delayqueue/DelayqueueApplication.java
package com.chengxy.delayqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DelayqueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DelayqueueApplication.class, args);
	}

}
