package com.springmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 20:37
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/wx/storage/fetch/**").addResourceLocations("file:C:/SpringMallImage/");
    }
}
