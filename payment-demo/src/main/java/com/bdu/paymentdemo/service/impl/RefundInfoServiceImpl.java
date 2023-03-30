package com.bdu.paymentdemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdu.paymentdemo.entity.RefundInfo;
import com.bdu.paymentdemo.mapper.RefundInfoMapper;
import com.bdu.paymentdemo.service.RefundInfoService;
import org.springframework.stereotype.Service;

@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {

}
