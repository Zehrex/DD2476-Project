47
https://raw.githubusercontent.com/lenve/oauth2-samples/master/oauth2-sso/auth-server/src/main/java/org/javaboy/authserver/AuthServerApplication.java
package org.javaboy.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
