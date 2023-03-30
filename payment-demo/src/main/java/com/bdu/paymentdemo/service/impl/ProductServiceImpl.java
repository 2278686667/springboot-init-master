package com.bdu.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdu.paymentdemo.entity.Product;
import com.bdu.paymentdemo.mapper.ProductMapper;
import com.bdu.paymentdemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
