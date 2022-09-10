package com.wyx.testspringboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.config
 * @Author: Origami
 * @Date: 2022/9/2 15:53
 */
@Configuration
@MapperScan({"com.wyx.testspringboot.mbg.mapper", "com.wyx.testspringboot.dao"})
public class MyBatisConfig {
}
