package com.bdu.paymentdemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdu.paymentdemo.entity.PaymentInfo;
import com.bdu.paymentdemo.mapper.PaymentInfoMapper;
import com.bdu.paymentdemo.service.PaymentInfoService;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
