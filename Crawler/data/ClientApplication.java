34
https://raw.githubusercontent.com/lzj-github/registry/master/client/src/main/java/cn/lzj/nacos/client/ClientApplication.java
package cn.lzj.nacos.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
