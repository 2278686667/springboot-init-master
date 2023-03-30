package com.bdu.paymentdemo.service.impl;

import com.bdu.paymentdemo.config.WxPayConfig;
import com.bdu.paymentdemo.entity.OrderInfo;
import com.bdu.paymentdemo.enums.wxpay.WxApiType;
import com.bdu.paymentdemo.enums.wxpay.WxNotifyType;
import com.bdu.paymentdemo.service.OrderInfoService;
import com.bdu.paymentdemo.service.WxPayService;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Resource
    WxPayConfig wxPayConfig;

    @Resource
    private CloseableHttpClient wxPayClient;

    @Resource
    private OrderInfoService orderInfoService;

    /**
     *创建订单，调用native支付接口
     * @param productId
     * @return code_url 和订单号
     * @throws IOException
     */
    @Override
    public Map<String, Object> nativePay(Long productId) throws IOException {
        log.info("生成订单");
        //生成订单
        OrderInfo orderInfo = orderInfoService.createOrderByProductId(productId);
        String code_url = orderInfo.getCodeUrl();
        if (orderInfo!=null&& !StringUtils.isEmpty(code_url)){
            log.info("订单已存在，二维码已保存");
            //返回二维码
            HashMap<String, Object> map = new HashMap<>();
            map.put("codeUrl", code_url);
            map.put("orderNo",orderInfo.getOrderNo());
            return map;
        }
        //todo 存入数据库

        log.info("调用api接口");
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getType()));

        // 请求body参数
        Gson gson=new Gson();
        HashMap paramsMap=new HashMap();
        paramsMap.put("appid",wxPayConfig.getAppid());
        paramsMap.put("mchid",wxPayConfig.getMchId());
        paramsMap.put("description",orderInfo.getTitle());
        paramsMap.put("out_trade_no",orderInfo.getOrderNo());
        paramsMap.put("notify_url",wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));

        Map amountMap=new HashMap();
        amountMap.put("total",orderInfo.getTotalFee());
        amountMap.put("currency","CNY");

        paramsMap.put("amount",amountMap);
        String jsonParams = gson.toJson(paramsMap);
        log.info("请求参数:"+jsonParams);

        StringEntity entity = new StringEntity(jsonParams,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);

        try {
            String bodyAsString = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                log.info("成功，返回结果="+bodyAsString);
            } else if (statusCode == 204) { //处理成功，无返回Body
                log.info("成功");
                System.out.println("success");
            } else {
                log.info("native下单失败，响应码="+statusCode+"返回结果"+bodyAsString);
                throw new IOException("request failed");
            }
            //响应结果
            HashMap<String,String> hashMap = gson.fromJson(bodyAsString, HashMap.class);
            //二维码
             code_url = hashMap.get("code_url");

            //保存二维码
            String orderNo = orderInfo.getOrderNo();
            orderInfoService.saveCodeUrl(orderNo,code_url);

            //返回二维码

            HashMap<String, Object> map = new HashMap<>();
            map.put("codeUrl",code_url);
            map.put("orderNo",orderInfo.getOrderNo());
            return map;
        } finally {
            response.close();
        }

    }

    @Override
    public void processOrder(Map<String, Object> bodyMap) {
        log.info("处理订单");
        String plainText=decryptFromResource(bodyMap);

    }

    /**
     * 对称解密
     * @param bodyMap
     * @return
     */
    private String decryptFromResource(Map<String, Object> bodyMap) {
        log.info("对称解密");
        //通知数据
        Map<String,String> resourceMap = (Map<String, String>) bodyMap.get("resource");
        //数据密文
        String ciphertext = resourceMap.get("ciphertext");
        //随机串
        String nonce = resourceMap.get("nonce");
        AesUtil aesUtil = new AesUtil(wxPayConfig.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        aesUtil.decryptToString()
        return null;
    }

}