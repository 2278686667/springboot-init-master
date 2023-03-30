package com.zhby.service.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhby.database.model.pc_orderbill;
import com.zhby.database.model.pc_ordertrade;
import com.zhby.database.model.pc_ordertrade_callback;
import com.zhby.database.model.t_client_payconfig;
import com.zhby.service.pay.assembly.PayCommonConstant;
import com.zhby.service.pay.assembly.alipay.AlipayNotify;
import com.zhby.service.pay.assembly.alipay.Alipay_Order_Trade;
import com.zhby.service.pay.assembly.alipay.AlipaySubmit;
import com.zhby.utils.core.ZhDateUtils;
import com.zhby.utils.core.ZhStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service("aliqrPayService")
public class AliQrPayService extends AbstractPayService {

    @Override
    public String createPayUrl(pc_ordertrade mPcOrderTrade, pc_orderbill mPcOrderbill, t_client_payconfig mTClientPayconfig) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", Alipay_Order_Trade.service);
        sParaTemp.put("partner", mTClientPayconfig.getMerchantid());
        sParaTemp.put("seller_id", mTClientPayconfig.getPosid());
        sParaTemp.put("_input_charset", Alipay_Order_Trade.input_charset);
        sParaTemp.put("payment_type", Alipay_Order_Trade.payment_type);
        sParaTemp.put("notify_url", payoffnoticeurl + "/aliqr");
        sParaTemp.put("return_url", payoffredirecturl);
        sParaTemp.put("anti_phishing_key", Alipay_Order_Trade.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", Alipay_Order_Trade.exter_invoke_ip);
        sParaTemp.put("out_trade_no", mPcOrderTrade.getOrdercode());
        sParaTemp.put("subject", mPcOrderbill.getOrdersubject());
        sParaTemp.put("total_fee", mPcOrderbill.getPayamount().toString());
        sParaTemp.put("body", "");
        return AlipaySubmit.buildRequest(sParaTemp, "post", "确认", mTClientPayconfig.getPub());
    }

    @Override
    protected TreeMap<String, String> getParamFormReq(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        TreeMap<String, String> params = new TreeMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                String str = new String(values[i].getBytes("iso-8859-1"), "utf-8");
                valueStr = (i == values.length - 1) ? valueStr + str : valueStr + str + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    @Override
    protected pc_ordertrade_callback getCallbackInfo(HttpServletRequest request, TreeMap<String, String> params) throws Exception {
        pc_ordertrade_callback mPcOrdertradeCallback = new pc_ordertrade_callback();
        mPcOrdertradeCallback.setOrdercode(params.get("out_trade_no"));
        log.info(mPcOrdertradeCallback.getOrdercode() + "支付宝扫码回调--获取参数" + JSONObject.toJSONString(params));

        mPcOrdertradeCallback.setPosid(params.get("seller_id"));
        mPcOrdertradeCallback.setTrxamt(params.get("total_fee"));
        if (ZhStringUtils.notempty(params.get("gmt_payment"))) {
            mPcOrdertradeCallback.setTrxdate(ZhDateUtils.parseDate(params.get("gmt_payment"), "yyyy-MM-dd HH:mm:ss"));
        }
        mPcOrdertradeCallback.setTermno(params.get("trade_no"));
        mPcOrdertradeCallback.setTrxstatus(params.get("trade_status"));
        return mPcOrdertradeCallback;
    }

    public String getOrderPayStatus(HttpServletRequest request, TreeMap<String, String> params, t_client_payconfig mTClientPayconfig) {
        if (!AlipayNotify.verify(params, mTClientPayconfig.getPub())) {
            return PayCommonConstant.ORDER_PAY_STATUS_SIGN;
        }
        String tradeStatus = params.get("trade_status");
        if(!"1".equals(params.get("out_trade_no"))) {
            if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                return PayCommonConstant.ORDER_PAY_STATUS_SUCCESS;
            }
            return PayCommonConstant.ORDER_PAY_STATUS_FAIL;
        }
        return "";
    }

    @Override
    protected void setReturnInfo(HttpServletResponse response, boolean isOkay) throws Exception {
        response.resetBuffer();
        if(isOkay) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }

}
