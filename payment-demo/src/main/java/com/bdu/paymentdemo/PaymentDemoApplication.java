package com.bdu.paymentdemo;

import com.bdu.paymentdemo.config.WxPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;

@SpringBootApplication
public class PaymentDemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(PaymentDemoApplication.class, args);
    }

}
