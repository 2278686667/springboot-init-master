package com.bdu.paymentdemo.controller;

import com.bdu.paymentdemo.service.WxPayService;
import com.bdu.paymentdemo.util.HttpUtils;
import com.bdu.paymentdemo.util.WechatPay2ValidatorForRequest;
import com.bdu.paymentdemo.vo.R;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/wx-pay")
@Api(tags = "微信支付API")
public class WxPayController {
    @Resource
    public WxPayService wxPayService;

    @Resource
    public Verifier verifier;

    @ApiOperation("调用统一下单接口，生成二维码")
    @RequestMapping("/native/{productId}")
    public R pay(@PathVariable Long productId) throws IOException {
        log.info("发起微信支付");
        Map<String,Object> map=wxPayService.nativePay(productId);
        return R.ok().setData(map);
    }

    @PostMapping("/native/notify")
    public String nativeNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson=new Gson();
        Map<String,String> map=new HashMap<>();//应答对象
        //处理通知参数
        String body = HttpUtils.readData(request);
        Map<String,Object> bodyMap = gson.fromJson(body, HashMap.class);
        String requestId = (String) bodyMap.get("id");
        log.info("支付通知的id==>{}", requestId);
        log.info("支付通知的完整数据==》{}",body);
        Object id = requestId;

        //todo : 订单处理
        wxPayService.processOrder(bodyMap);
        //todo : 签名的认证
        WechatPay2ValidatorForRequest wechatPay2ValidatorForRequest = new WechatPay2ValidatorForRequest(verifier, requestId,body);
        if (!wechatPay2ValidatorForRequest.validate(request)){
            log.error("通知验签失败");
            response.setStatus(500);
            map.put("code","ERROR");
            map.put("message","通知验签失败");
            return gson.toJson(map);
        }
        log.info("通知验签成功");
        //成功应答
        response.setStatus(200);
        map.put("code","SUCCESS");
        map.put("message","成功");
        return gson.toJson(map);

    }


}
