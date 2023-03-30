package com.bdu.paymentdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@MapperScan("com.bdu.paymentdemo.mapper")
@EnableTransactionManagement
public class MyBatisPlusConfig {
}
