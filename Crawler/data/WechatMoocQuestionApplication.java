2
https://raw.githubusercontent.com/tianhan1998/WechatMoocQuestion/master/src/main/java/cn/th/WechatMoocQuestionApplication.java
package cn.th;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.th.mapper")
public class WechatMoocQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatMoocQuestionApplication.class, args);
    }

}
