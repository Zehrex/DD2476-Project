2
https://raw.githubusercontent.com/gavin-yyj/vhr-/master/swagger/src/main/java/com/macro/mall/tiny/config/MyBatisConfig.java
package com.macro.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan("com.macro.mall.tiny.mbg.mapper")
public class MyBatisConfig {
}
