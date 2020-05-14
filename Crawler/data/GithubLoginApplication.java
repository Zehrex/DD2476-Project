47
https://raw.githubusercontent.com/lenve/oauth2-samples/master/github_login/src/main/java/org/javaboy/github_login/GithubLoginApplication.java
package org.javaboy.github_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubLoginApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
