package com.zhby.service.mq;

import com.rabbitmq.client.Channel;
import com.zhby.database.dal.pc_orderbillDal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayOrderCustomer {

    @Autowired
    protected RabbitTemplate rabbitTemplate;
    @Autowired
    protected pc_orderbillDal dPcOrderbill;

    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "PayOrderDirectQueue"), exchange = @Exchange(value = "PayOrderDirectExchange"), key = "PayOrderDirectRouting"),
            concurrency =  "10"
    )
    public void process(String ordercode, Channel channel) throws Exception {
         dPcOrderbill.pushOrder(ordercode);
    }

}
