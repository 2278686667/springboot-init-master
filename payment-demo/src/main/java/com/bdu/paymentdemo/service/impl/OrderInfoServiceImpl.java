package com.bdu.paymentdemo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdu.paymentdemo.entity.OrderInfo;
import com.bdu.paymentdemo.entity.Product;
import com.bdu.paymentdemo.enums.OrderStatus;
import com.bdu.paymentdemo.mapper.OrderInfoMapper;
import com.bdu.paymentdemo.mapper.ProductMapper;
import com.bdu.paymentdemo.service.OrderInfoService;
import com.bdu.paymentdemo.util.OrderNoUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Override
    public OrderInfo createOrderByProductId(Long productId) {

        //查找已存在但未支付大的订单

        OrderInfo orderInfo=this.getNoPayOrderByProductId(productId);
        if (orderInfo!=null){
            return orderInfo;
        }
        //获取商品信息
        Product product = productMapper.selectById(productId);

        //生成订单
         orderInfo=new OrderInfo();
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo()); //订单号
        orderInfo.setProductId(productId);
        orderInfo.setTitle(product.getTitle());
        orderInfo.setTotalFee(product.getPrice());//分
        int insert = orderInfoMapper.insert(orderInfo);
        return orderInfo;
    }

    @Override
    public void saveCodeUrl(String orderNo, String codeUrl) {
        QueryWrapper<OrderInfo> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCodeUrl(codeUrl);
        baseMapper.update(orderInfo,queryWrapper);
    }

    /**
     * 查询订单列表，并倒叙查询
     * @return
     */
    @Override
    public List<OrderInfo> listOrderByCreateTimeDesc() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }

    private OrderInfo getNoPayOrderByProductId(Long productId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        queryWrapper.eq("order_status",OrderStatus.NOTPAY.getType()); //未支付枚举
//        queryWrapper.eq("user_id",userId);
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        return orderInfo;
    }
}
