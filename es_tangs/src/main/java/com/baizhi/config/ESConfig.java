package com.baizhi.config;

import com.baizhi.dao.es.CustomPoetryRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Bean
    public CustomPoetryRepositoryImpl creatCustomPoetryRepositoryImpl() {
        return new CustomPoetryRepositoryImpl();
    }
}