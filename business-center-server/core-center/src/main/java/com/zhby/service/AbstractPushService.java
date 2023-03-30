package com.zhby.service;

import com.alibaba.fastjson.JSONObject;
import com.zhby.utils.net.ZhDataConnectedUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractPushService {

    @Value("${interface.privatekey}")
    public String privatekey;
    @Value("${interface.publickey}")
    public String publickey;


    public JSONObject getReqData(String json)  {
        return this.getReqData(json, JSONObject.class);
    }

    public <T> T getReqData(String json, Class<T> cla) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String ZH_YW_CODE = request.getHeader("ZH-YW-CODE");
        return this.getReqData(json, request, response, cla, this.getThirdPublickey(ZH_YW_CODE));
    }

    public <T> T getReqDataFormUrl(String json, Class<T> cla) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return this.getReqData(json, request, response, cla, publickey);
    }

    public <T> T getReqData(String json, HttpServletRequest request, HttpServletResponse response, Class<T> cla, String targetPublicKey) {
        String data = ZhDataConnectedUtils.getReqData(json, request, response, privatekey, targetPublicKey);
        log.info("请求数据：" + data);
        T t = JSONObject.parseObject(data, cla);
        Set<ConstraintViolation<@Valid T>> validateSet = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(t, new Class[0]);
        if(!validateSet.isEmpty()) {
            throw new RuntimeException(validateSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
        }
        return t;
    }


    public String sendReqData(String json, String url, String ZH_YW_TX, String ZH_YW_CODE, String TARGET_CODE) {
        return ZhDataConnectedUtils.sendReqData(json, url, ZH_YW_TX, ZH_YW_CODE, privatekey, this.getThirdPublickey(TARGET_CODE));
    }

    public void setRespData(Object data) {
        log.info(JSONObject.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss"));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String ZH_YW_CODE = request.getHeader("ZH-YW-CODE");
        String ZH_YW_TX = request.getHeader("ZH-YW-TX");
        ZhDataConnectedUtils.setRespData(JSONObject.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss"), response, ZH_YW_TX, ZH_YW_CODE, privatekey, this.getThirdPublickey(ZH_YW_CODE));
    }

    public String getRespUrl(String url, String json) {
        return ZhDataConnectedUtils.getRespUrl(url, json, privatekey, publickey);
    }


    protected abstract String getThirdPublickey(String ZH_YW_CODE);


}
