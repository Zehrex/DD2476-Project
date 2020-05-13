2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/Swagger2.java
package cn.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.show}")
    private boolean swaggerShow;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * Api信息
     * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Site Swagger Restful API")
                .description("本地测试版，")
                .contact("chenVal")
                .version("5.0")
                .build();
    }
}
