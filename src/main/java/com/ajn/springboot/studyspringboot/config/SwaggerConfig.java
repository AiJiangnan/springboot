package com.ajn.springboot.studyspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 艾江南
 * @date 2018/9/5
 */
@Configuration
@EnableSwagger2
@Profile("test")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.PATCH, responseMessages())
                .globalResponseMessage(RequestMethod.OPTIONS, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages())
                .globalResponseMessage(RequestMethod.HEAD, responseMessages())
                .globalResponseMessage(RequestMethod.TRACE, responseMessages())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("title")
                .build();
    }

    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("参数校验异常").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(409).message("业务逻辑异常").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        return responseMessageList;
    }
}
