package com.springmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/18 20:02
 */

@Configuration
@ComponentScan(basePackages = "com.springmall")
@EnableAspectJAutoProxy
public class CustomAspect {
}
