package com.izumi.config;

import com.izumi.interceptor.AuthInterceptor;
import com.izumi.interceptor.LogInterceptor;
import com.izumi.log.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LogInterceptor logInterceptor;
    @Resource
    private AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(logInterceptor);
    }
    @Bean
    public FilterRegistrationBean logFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>(new LogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
