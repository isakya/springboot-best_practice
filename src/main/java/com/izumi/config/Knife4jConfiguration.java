package com.izumi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/***
 * 接口文档
 * 启动项目后，在浏览器中输入 http://localhost:8080/doc.html 即可打开文档
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("izumi-boot APIs")
                        .description("# izumi-boot APIs")
                        .termsOfServiceUrl("https://github.com/isakya")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("V1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.izumi.modules.sys.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
