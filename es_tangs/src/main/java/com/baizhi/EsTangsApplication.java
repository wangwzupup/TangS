package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories("com.baizhi.dao.es")
@MapperScan("com.baizhi.dao.basic")
@SpringBootApplication
public class EsTangsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsTangsApplication.class, args);
    }

}

