package com.bdu.paymentdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bdu.paymentdemo.entity.OrderInfo;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createOrderByProductId(Long productId);

    void saveCodeUrl(String orderNo,String codeUrl);

    List<OrderInfo> listOrderByCreateTimeDesc();
}
