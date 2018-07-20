package com.zt.study.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决跨域问题
 */
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") //影射地址  允许访问哪些路径
                .allowedMethods("*")  //请求类型过滤
                .allowedOrigins("*")  //来源地址过滤
                .allowedHeaders("*"); //请求头过滤
    }
}
