package com.qn.qiniudemoapi.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * swagger配置
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    //@Bean
    public Docket docket(){
        Contact contact = new Contact("name", "url", "@email.com");
        ApiInfo apiInfo = new ApiInfo(
                "demo-title",
                "demo-desc",
                "1.0",
                "termOfServiceUrl",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .groupName("groupName")
                .enable(true)
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage() 指定要扫描的包
                //any()：扫描全部
                //none();不扫描
                //withClassAnnotation:扫描类上的注解
                //withMethodAnotation: 扫描方法上的注解
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qn.qiniudemoapi.controller"))
                //paths() 过滤什么路径
                .build();
    }

}