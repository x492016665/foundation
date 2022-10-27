package com.pf.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.classmate.TypeResolver;
import com.pf.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yk_xing
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig  {

    @Value("${server.port}")
    private Integer port;

    @Value("${system.prefix.namespace1}")
    private String namespace1;

    private String termsOfServiceUrl = "开发: http://[host]/ap-dev/api/</br>" +
            "测试: http://[host]/ap-uat/api/</br>" +
            "正式: http://[host]/ap/api/";

    @Autowired
    private TypeResolver typeResolver;

    /*@Bean(value = "all")
    public Docket all() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("所有接口文档")
                        .description("")
                        .termsOfServiceUrl(termsOfServiceUrl)
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1-all")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.pf.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }*/

    @Bean(value = "web")
    public Docket web() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("web端接口文档")
                        .termsOfServiceUrl(termsOfServiceUrl)
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2-web")
                .additionalModels(typeResolver.resolve(Result.class))
                .ignoredParameterTypes(IPage.class, HttpSession.class, HttpServletRequest.class, HttpServletResponse.class)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.pf.controller" + namespace1.replaceFirst("/", ".")))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean(value = "mini")
    public Docket mini() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("小程序接口文档")
                        .termsOfServiceUrl(termsOfServiceUrl)
                        .version("1.1")
                        .build())
                .additionalModels(typeResolver.resolve(Result.class))
                .ignoredParameterTypes(IPage.class, HttpSession.class, HttpServletRequest.class, HttpServletResponse.class)
                //分组名称
                .groupName("3-mini")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.pf.controller.mini"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
