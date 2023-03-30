package com.bdu.paymentdemo.service;

import java.io.IOException;
import java.util.Map;

public interface WxPayService {
    Map<String, Object> nativePay(Long productId) throws IOException;

    void processOrder(Map<String, Object> bodyMap);
}
