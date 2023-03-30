package com.zhby.service.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.zhby.database.model.pc_orderbill;
import com.zhby.database.model.pc_ordertrade;
import com.zhby.database.model.pc_ordertrade_callback;
import com.zhby.database.model.t_client_payconfig;
import com.zhby.service.pay.assembly.PayCommonConstant;
import com.zhby.service.pay.assembly.ccb.*;
import com.zhby.utils.core.ZhDateUtils;
import com.zhby.utils.core.ZhQrCodeUtils;
import com.zhby.utils.core.ZhRMBUtils;
import com.zhby.utils.core.ZhStringUtils;
import com.zhby.utils.encoded.ZhEscapeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service("ccbqrPayService")
public class CcbQrPayService extends AbstractPayService {
    @Override
    public String createPayUrl(pc_ordertrade mPcOrderTrade, pc_orderbill mPcOrderbill, t_client_payconfig config) throws IOException, WriterException {
        CCB_Order_Trade ccb_order_trade = new CCB_Order_Trade();
        String ret;

        ccb_order_trade.setMERCHANTID(config.getMerchantid());
        ccb_order_trade.setPOSID(config.getPosid());
        ccb_order_trade.setPUB(config.getPub());
        ccb_order_trade.setBRANCHID(config.getBranchid());

        ccb_order_trade.setORDERID(mPcOrderTrade.getOrdercode());
        ccb_order_trade.setPAYMENT(mPcOrderbill.getPayamount().toString());
        String PUB32TR2 = ccb_order_trade.getPUB().substring(ccb_order_trade.getPUB().length() - 30);
        ccb_order_trade.setPUB32TR2(PUB32TR2);

        Map<String, Object> map = getPostMap(ccb_order_trade);
        String ret1 = HttpClientUtil.httpPost(ccb_order_trade.getBANKURL(), map);
        QrURL qrurl = (QrURL) JSONObject.parseObject(ret1, QrURL.class);
        JSONObject jsonObj = JSON.parseObject(ZhEscapeUtils.decode(HttpClientUtil.httpGet(qrurl.getPAYURL(), "UTF-8")));
        String QRURL = jsonObj.getString("QRURL");
        return ZhQrCodeUtils.getQrCodeBinary(QRURL);
    }

    @Override
    protected TreeMap<String, String> getParamFormReq(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        TreeMap<String, String> params = new TreeMap<String, String>();
        Map reqMap = request.getParameterMap();
        for (Object key : reqMap.keySet()) {
            String value = ((String[]) reqMap.get(key))[0];
            params.put(key.toString(), value);
        }
        return params;
    }


    @Override
    protected String getOrderPayStatus(HttpServletRequest request, TreeMap<String, String> params, t_client_payconfig mTClientPayconfig) throws Exception {
        String src = request.getQueryString();// 源串
        src = src.substring(0, src.indexOf("&SIGN"));
        src = new String(src.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String SIGN = request.getParameter("SIGN");// 签名字段
        // 商户通知验签
        RSASig rsa = new RSASig();
        rsa.setPublicKey(mTClientPayconfig.getPub());
        if (!rsa.verifySigature(SIGN, src)) {// 验签成功
            return PayCommonConstant.ORDER_PAY_STATUS_SIGN;
        }
        String SUCCESS = request.getParameter("SUCCESS");
        if ("Y".equals(SUCCESS)) {
            return PayCommonConstant.ORDER_PAY_STATUS_SUCCESS;
        }
        return PayCommonConstant.ORDER_PAY_STATUS_FAIL;
    }

    @Override
    protected pc_ordertrade_callback getCallbackInfo(HttpServletRequest request, TreeMap<String, String> params) throws Exception {
        pc_ordertrade_callback mPcOrdertradeCallback = new pc_ordertrade_callback();
        mPcOrdertradeCallback.setOrdercode(request.getParameter("ORDERID"));
        log.info(mPcOrdertradeCallback.getOrdercode() + "建行扫码回调--获取参数" + JSONObject.toJSONString(params));

        mPcOrdertradeCallback.setPosid(request.getParameter("POSID"));
        mPcOrdertradeCallback.setBranchid(request.getParameter("BRANCHID"));
        mPcOrdertradeCallback.setJylx(request.getParameter("ACC_TYPE"));
        mPcOrdertradeCallback.setTrxamt(request.getParameter("PAYMENT"));
        if (ZhStringUtils.notempty(params.get("ACCDATE"))) {
            mPcOrdertradeCallback.setTrxdate(ZhDateUtils.parseDate(params.get("ACCDATE"), "yyyyMMdd"));
        }
        mPcOrdertradeCallback.setSign(request.getParameter("SIGN"));
        mPcOrdertradeCallback.setIp(request.getParameter("CLIENTIP"));
        mPcOrdertradeCallback.setTrxstatus(request.getParameter("SUCCESS"));
        return  mPcOrdertradeCallback;
    }

    @Override
    protected void setReturnInfo(HttpServletResponse response, boolean isOkay) throws Exception {
    }


    private Map<String, Object> getPostMap(CCB_Order_Trade ccb_order_trade) {
        StringBuffer tmp = new StringBuffer(); // 验签字段
        tmp.append("MERCHANTID=");
        tmp.append(ccb_order_trade.getMERCHANTID());
        tmp.append("&POSID=");
        tmp.append(ccb_order_trade.getPOSID());
        tmp.append("&BRANCHID=");
        tmp.append(ccb_order_trade.getBRANCHID());
        tmp.append("&ORDERID=");
        tmp.append(ccb_order_trade.getORDERID());
        tmp.append("&PAYMENT=");
        tmp.append(ccb_order_trade.getPAYMENT());
        tmp.append("&CURCODE=");
        tmp.append(ccb_order_trade.getCURCODE());
        tmp.append("&TXCODE=");
        tmp.append(ccb_order_trade.getTXCODE());
        tmp.append("&REMARK1=");
        tmp.append(ccb_order_trade.getREMARK1());
        tmp.append("&REMARK2=");
        tmp.append(ccb_order_trade.getREMARK2());
        tmp.append("&RETURNTYPE=");
        tmp.append(ccb_order_trade.getRETURNTYPE());
        tmp.append("&TIMEOUT=");
        tmp.append(ccb_order_trade.getTIMEOUT());
        tmp.append("&PUB=");
        tmp.append(ccb_order_trade.getPUB32TR2());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("CCB_IBSVersion", "V6"); // 必输项
        map.put("MERCHANTID", ccb_order_trade.getMERCHANTID());
        map.put("BRANCHID", ccb_order_trade.getBRANCHID());
        map.put("POSID", ccb_order_trade.getPOSID());
        map.put("ORDERID", ccb_order_trade.getORDERID());
        map.put("PAYMENT", ccb_order_trade.getPAYMENT());
        map.put("CURCODE", ccb_order_trade.getCURCODE());
        map.put("TXCODE", ccb_order_trade.getTXCODE());
        map.put("REMARK1", ccb_order_trade.getREMARK1());
        map.put("REMARK2", ccb_order_trade.getREMARK2());
        map.put("RETURNTYPE", ccb_order_trade.getRETURNTYPE());
        map.put("TIMEOUT", ccb_order_trade.getTIMEOUT());
        map.put("MAC", MD5.md5Str(tmp.toString()));
        return map;
    }
}
