package com.segroup.hospitalsite.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: Swagger2 API 文档自动化配置
 *
 * @author zheng
 * @date 2022-03-30
 */
@Configuration
@EnableSwagger2

public class Swagger2Config {

    @Value(value = "${swagger.enable}")
    private boolean swaggerEnable;

    /**
     * Description: 配置Docket
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author zheng
     * @date 2022-03-30
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径，控制类包
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //basePackage("com.segroup.hospitalsite.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Description: api 文档的详细信息函数
     *
     * @return springfox.documentation.service.ApiInfo
     * @author zheng
     * @date 2022-03-30
     */
    private ApiInfo apiInfo() {
        String title = "医院预约系统API接口文档";
        String version = "1.0";
        String description = "系统API描述";

        return new ApiInfoBuilder()
                // 页面标题
                .title(title)
                // 版本号
                .version(version)
                // 描述
                .description(description)
                .build();
    }
}
