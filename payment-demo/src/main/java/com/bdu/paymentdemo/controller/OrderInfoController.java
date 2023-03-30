package com.bdu.paymentdemo.controller;

import com.bdu.paymentdemo.entity.OrderInfo;
import com.bdu.paymentdemo.service.OrderInfoService;
import com.bdu.paymentdemo.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "商品订单管理")
@RestController
@RequestMapping("/api/order-info")
@CrossOrigin
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/list")
    public R list(){
        List<OrderInfo> list=orderInfoService.listOrderByCreateTimeDesc();
        return R.ok().data("list",list);
    }
}
