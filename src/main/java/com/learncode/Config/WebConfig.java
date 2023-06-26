package com.learncode.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/ckeditor/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/ckeditor/4.16.2/");
    	registry.addResourceHandler("/image/**")
        .addResourceLocations("classpath:/image/");
    }
}
