package com.zhby.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue PayOrderDirectQueue() {
        return new Queue("PayOrderDirectQueue",true);
    }

    @Bean
    public DirectExchange PayOrderDirectExchange() {
        return new DirectExchange("PayOrderDirectExchange",true,false);
    }

    @Bean
    public Binding payOrderDirect() {
        return BindingBuilder.bind(PayOrderDirectQueue()).to(PayOrderDirectExchange()).with("PayOrderDirectRouting");
    }

    @Bean
    public Queue PayNoticeDelayedQueue() {
        return new Queue("PayNoticeDelayedQueue",true);
    }

    @Bean
    public CustomExchange PayNoticeDelayedExchange() {
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("PayNoticeDelayedExchange","x-delayed-message", true,false, args);
    }

    @Bean
    public Binding PayNoticeDelayed() {
        return BindingBuilder.bind(PayNoticeDelayedQueue()).to(PayNoticeDelayedExchange()).with("PayNoticeDelayedRouting").noargs();
    }




}