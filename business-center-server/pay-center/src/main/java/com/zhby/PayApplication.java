package com.zhby;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhby.config.Swagger2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"com.zhby"})
@ImportResource("classpath:appContext.xml")
public class PayApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PayApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
        System.out.println("----------支付中心启动成功---------");
    }

}
