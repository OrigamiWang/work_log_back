package com.wyx.testspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.config
 * @Author: Origami
 * @Date: 2022/9/2 18:53
 */


@Configuration
@EnableSwagger2
public class Swagger2Config {

   public static final String TAG_1 = "LogController";
   public static final String TAG_2 = "LoginController";
   public static final String TAG_3 = "PermissionController";
   public static final String TAG_4 = "SendMailController";
   public static final String TAG_5 = "VerificationCodeController";
   public static final String TAG_6 = "MinioController";


    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.wyx.testspringboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(TAG_1, "日志"))
                .tags(new Tag(TAG_2, "登录"))
                .tags(new Tag(TAG_3, "权限"))
                .tags(new Tag(TAG_4, "发邮件"))
                .tags(new Tag(TAG_5, "验证码"))
                .tags(new Tag(TAG_6, "文件上传"));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SwaggerUI演示")
                .description("mall-tiny")
                .contact("macro")
                .version("1.0")
                .build();
    }
}
