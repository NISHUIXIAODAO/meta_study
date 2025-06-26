package com.may.meta_study.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.may.meta_study.mapper")
public class MybatisConfig {
    // MyBatis的分页可以使用PageHelper插件
    // PageHelper已通过starter自动配置，无需额外配置
}