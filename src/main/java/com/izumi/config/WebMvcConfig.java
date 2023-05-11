package com.izumi.config;

import cn.hutool.core.collection.CollectionUtil;
import com.izumi.interceptor.AuthInterceptor;
import com.izumi.interceptor.LogInterceptor;
import com.izumi.log.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LogInterceptor logInterceptor;
    @Resource
    private AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> ignoreUrl = CollectionUtil.newArrayList(//swagger相关的
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v2/api-docs-ext",
                "/configuration/ui",
                "/configuration/security");
        registry.addInterceptor(authInterceptor).excludePathPatterns(ignoreUrl);
        registry.addInterceptor(logInterceptor).excludePathPatterns(ignoreUrl);
    }
    @Bean
    public FilterRegistrationBean logFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>(new LogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
