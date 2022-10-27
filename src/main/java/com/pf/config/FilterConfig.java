package com.pf.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.pf.config.filter.GlobalServletFilter;
import com.pf.config.filter.SessionFilter;
import com.pf.config.filter.SessionFilterContext;
import com.pf.config.filter.UserMDCServletFilter;
import com.pf.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author yk_xing
 */
@Configuration
public class FilterConfig {

    @Autowired
    private SystemConfig systemConfig;

    @Value("${system.prefix.namespace1}")
    private String namespace1;

    @Value("${system.prefix.namespace2}")
    private String namespace2;

    @Value("${system.prefix.namespace3}")
    private String namespace3;

    @Bean
    public FilterRegistrationBean globalFilterRegistration() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义过滤器
        registration.setFilter(new GlobalServletFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns(namespace1 + "/*");
        registration.addUrlPatterns(namespace2 + "/*");
        registration.addUrlPatterns(namespace3 + "/*");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 3);
        return registration;
    }

    @Bean
    public FilterRegistrationBean mdcInsertingFilterRegistration() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义过滤器
        registration.setFilter(new MDCInsertingServletFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns(namespace1 + "/*");
        registration.addUrlPatterns(namespace2 + "/*");
        registration.addUrlPatterns(namespace3 + "/*");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean mdcFilterRegistration(SessionService sessionService) {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义过滤器
        registration.setFilter(new MDCInsertingServletFilter());
        registration.setFilter(new UserMDCServletFilter(sessionService));
        // 设置过滤器的URL模式
        registration.addUrlPatterns(namespace1 + "/*");
        registration.addUrlPatterns(namespace2 + "/*");
        registration.addUrlPatterns(namespace3 + "/*");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean sessionFilterRegistration(SessionService sessionService) {
        SessionFilterContext context = new SessionFilterContext(sessionService);
        context.setSystemConfig(systemConfig);
        /*web*/
        //登录
        context.addIgnorePath(namespace1 + "/login/signedIn");
        //图片验证码
        context.addIgnorePath(namespace1 + "/login/imageCaptcha");

        /*mini*/
        //mini sessionId
        context.addIgnorePath(namespace2 + "/login/getId");
        context.addIgnorePath(namespace2 + "/login/signedIn");
        context.addIgnorePath(namespace2 + "/login/signUp");
        //文件预览
        context.addIgnorePath(namespace2 + "/cmsContent/file");
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加自定义过滤器
        registration.setFilter(new SessionFilter(context));
        // 设置过滤器的URL模式
        registration.addUrlPatterns(namespace1 + "/*");
        registration.addUrlPatterns(namespace2 + "/*");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }
}
